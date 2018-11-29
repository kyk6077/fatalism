package com.fatalis.product;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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


	public ActionFoward selectList(HttpServletRequest request, HttpServletResponse response) {
		ActionFoward actionFoward = new ActionFoward();
		actionFoward.setCheck(true);
		try {
			Search search = new Search();
			//		search.getKind();
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
			}

		}else {
			actionFoward.setCheck(true);
			actionFoward.setPath("../WEB-INF/view/product/productWrite.jsp");
		}


		return actionFoward;
	}

}
