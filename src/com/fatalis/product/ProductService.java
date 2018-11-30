package com.fatalis.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fatalis.cart.CartInfoDTO;
import com.fatalism.page.MakePager;
import com.fatalism.page.Pager;
import com.fatalism.page.Search;
import com.fatalism.upload.UploadDAO;
import com.fatalism.upload.UploadDTO;
import com.iu.action.ActionFoward;
import com.iu.member.MemberDTO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductService {

	private ProductDAO productDAO;
	private UploadDAO uploadDAO;
	public ProductService() {
		productDAO = new ProductDAO();
		uploadDAO = new UploadDAO();
	}
	
	public ActionFoward productOrder(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		ProductDTO productDTO = null;
		int pnum=2;
		String id = null;
		
		try {
		MemberDTO memberDTO = (MemberDTO)request.getSession().getAttribute("member");
		id=memberDTO.getId();
		}catch (Exception e) {
			//비회원처리
			id="qqq";
			// TODO: handle exception
		}
		try {
			productDTO=productDAO.selectOne(pnum);
			productDTO.setBodysize(request.getParameter("size"));
			request.setAttribute("productDTO", productDTO);
			
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/product/product_order.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionFoward;
	}


	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		try {
			Search search = new Search();
			int curPage = 1;
			try {curPage = Integer.parseInt(request.getParameter("curPage"));
			}catch (Exception e) {}
			
			try {search.setKind(request.getParameter("type"));} 
			catch (Exception e) {
				search.setKind("FATALISM");}
			
			
			MakePager makePager = new MakePager(curPage, 12, search);
			int count = productDAO.getCount(search.getKind());
			Pager pager = makePager.MakePage(count);
			List<ProductDTO> ar = productDAO.selectList(makePager.MakeRow());
			List<UploadDTO> ar2 = new ArrayList<>();
			for (ProductDTO productDTO : ar) {
				ar2.add(uploadDAO.selectOne(productDTO.getPnum()));
			}
			request.setAttribute("kind",search.getKind());
			request.setAttribute("product_list", ar);
			request.setAttribute("upload_list", ar2);
			request.setAttribute("pager",pager);
			actionFoward.setPath("../WEB-INF/view/product/productList.jsp");
		} catch (Exception e) {
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
			request.setAttribute("path", "../index.jsp");
			request.setAttribute("message", "list Fali");
		}

		return actionFoward;
	}

	public ActionFoward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();

		actionFoward.setCheck(true);
		actionFoward.setPath("../WEB-INF/view/product/productDelete.jsp");

		return actionFoward;
	}
	
	public ActionFoward selectOne(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		try {
			int pnum = Integer.parseInt(request.getParameter("pnum"));
			ProductDTO productDTO = productDAO.selectOne(pnum);
			UploadDAO uploadDAO = new UploadDAO();
			List<UploadDTO> ar = uploadDAO.selectInfo(pnum);
			UploadDTO mainUpload = new UploadDTO();
			mainUpload.setNum(ar.get(0).getNum());
			mainUpload.setPnum(ar.get(0).getPnum());
			mainUpload.setStep(ar.get(0).getStep());
			mainUpload.setFname(ar.get(0).getFname());
			mainUpload.setOname(ar.get(0).getOname());
			ar.remove(0);
			request.setAttribute("mainUpload",mainUpload);
			request.setAttribute("uploadList",ar);
			request.setAttribute("productDTO",productDTO);
			
			actionFoward.setPath("../WEB-INF/view/product/productSelect.jsp");
		} catch (Exception e) {
			request.setAttribute("message","select fail");
			request.setAttribute("path","./productList.do");
			actionFoward.setPath("../WEB-INF/view/common/result.jsp");
		}
		return actionFoward;
	}

	public ActionFoward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		String method = request.getMethod();

		if(method.equals("POST")) {

			int maxSize = 1024*1024*10;
			String path = request.getServletContext().getRealPath("upload");
			File file = new File(path);
			if(!file.exists()) {
				file.mkdirs();
			}
			try {
				MultipartRequest multi = new MultipartRequest(request, path, maxSize, "UTF-8",new DefaultFileRenamePolicy());
				ProductDTO productDTO = new ProductDTO();
				productDTO.setName(multi.getParameter("name"));
				productDTO.setPrice(Integer.parseInt(multi.getParameter("price")));
				productDTO.setType(multi.getParameter("type"));
				productDTO.setDescription(multi.getParameter("description"));
				productDTO.setPnum(productDAO.getNum());
				
				int count = Integer.parseInt(multi.getParameter("scount"));
				productDTO.setCount(count);
				productDTO.setBodysize("S");
				int s_result = productDAO.insert(productDTO);
				
				count = Integer.parseInt(multi.getParameter("rcount"));
				productDTO.setCount(count);
				productDTO.setBodysize("R");
				int r_result = productDAO.insert(productDTO);
				
				count = Integer.parseInt(multi.getParameter("lcount"));
				productDTO.setCount(count);
				productDTO.setBodysize("L");
				int l_result = productDAO.insert(productDTO);

				if(s_result>0||r_result>0||l_result>0) {
					UploadDTO uploadDTO = new UploadDTO();

					Enumeration<Object> e = multi.getFileNames();
					int step_count = 0;
					while(e.hasMoreElements()) {
						String s = (String)e.nextElement();
						if(multi.getFilesystemName(s)!=null) {
							uploadDTO.setOname(multi.getOriginalFileName(s));
							uploadDTO.setFname(multi.getFilesystemName(s));
							uploadDTO.setStep(step_count);
							uploadDTO.setPnum(productDTO.getPnum());
							uploadDAO.productInsert(uploadDTO);
							step_count++;
						}
			
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
				
				actionFoward.setCheck(true);
				actionFoward.setPath("../WEB-INF/view/product/productWrite.jsp");
			}

		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/product/productWrite.jsp");
		}


		return actionFoward;
	}

}
