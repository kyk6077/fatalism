package com.fatalis.cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalis.product.ProductDTO;
import com.iu.member.MemberDTO;
import com.iu.util.DBConnector;

public class CartDAO {
	
	//전체삭제
	public int allDelete(String id) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete cart where mem_id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	
	//선택 삭제 
	public int selectDelete(int num) throws Exception{
		Connection con = DBConnector.getConnect();
		String sql = "delete cart where num=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		int result = st.executeUpdate();
		DBConnector.disConnect(st, con);
		return result;
	}
	
	
	//장바구니폼에 넣기
	
	public List<CartInfoDTO> cartSelect(String id) throws Exception{
		Connection con =DBConnector.getConnect();
		String sql="select p.name,bodysize,price, a.* from "
				+ "product p, cart a where p.num = a.pnum and mem_id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		List<CartInfoDTO> ar = new ArrayList<>();
		while(rs.next()) {
			CartInfoDTO cartInfo = new CartInfoDTO();
			cartInfo.setName(rs.getString("name"));
			cartInfo.setBodysize(rs.getString("bodysize"));
			cartInfo.setPrice(rs.getInt("price"));
			cartInfo.setNum(rs.getInt("num"));
			ar.add(cartInfo);
		}
		return ar;
	}
	

}
