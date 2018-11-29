package com.fatalis.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.page.RowNumber;
import com.iu.member.MemberDTO;
import com.iu.util.DBConnector;

public class ProductDAO {
	
	
	public int insert(ProductDTO productDTO) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "insert into product values(PNUM_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,productDTO.getName());
		st.setInt(2,productDTO.getPrice());
		st.setString(3,productDTO.getType());
		st.setString(4,productDTO.getBodysize());
		st.setInt(5,productDTO.getCount());
		st.setString(6,productDTO.getDescription());
		st.setInt(7,productDTO.getPnum());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(st, con);
		return result;
	}
	
	public ProductDTO selectOne(int pnum) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "select * from product where pnum=? and bodysize='S'";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, pnum);
		ResultSet rs = st.executeQuery();
		ProductDTO productDTO = new ProductDTO();
		if(rs.next()) {
			productDTO.setNum(rs.getInt("num"));
			productDTO.setName(rs.getString("name"));
			productDTO.setPrice(rs.getInt("price"));
			productDTO.setType(rs.getString("type"));
			productDTO.setDescription(rs.getString("description"));
			productDTO.setPnum(rs.getInt("pnum"));
		}
		
		DBConnector.disConnect(rs, st, con);
		return productDTO;
	}
	
	public List<ProductDTO> selectList(RowNumber rowNumber) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "SELECT * FROM ( "
				+ "SELECT rownum R, P.* from( "
				+ "SELECT * FROM PRODUCT WHERE BODYSIZE='S' and type=? "
				+ "order by pnum desc) p) "
				+ "where R between ? and ?";
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1,rowNumber.getSearch().getKind());
		st.setInt(2, rowNumber.getStartRow());
		st.setInt(3, rowNumber.getLastRow());
		ResultSet rs = st.executeQuery();
		List<ProductDTO> ar = new ArrayList<>();
		while(rs.next()) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setNum(rs.getInt("num"));
			productDTO.setName(rs.getString("name"));
			productDTO.setPrice(rs.getInt("price"));
			productDTO.setType(rs.getString("type"));
			productDTO.setPnum(rs.getInt("pnum"));
			productDTO.setDescription(rs.getString("description"));
			ar.add(productDTO);
		}
		
		DBConnector.disConnect(rs, st, con);
		return ar;
	}
	
	public int getCount(String kind) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select COUNT(num) from product where bodysize='S' and type=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, kind);		
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
	
		DBConnector.disConnect(rs, st, con);
		return num;
	}
	
	public int getNum() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select P_IMG_SEQ.NEXTVAL from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		rs.next();
		int num = rs.getInt(1);
		DBConnector.disConnect(rs, st, con);
		return num;
	}
	
}
