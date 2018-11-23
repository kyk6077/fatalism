package com.fatalism.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fatalism.util.DBConnector;

public class UploadDAO {
	
	
	//pnum은 올릴때 product num 받아서 올림
	public int insert(UploadDTO uploadDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into upload values(upload_seq.nextval,pnum_test_seq.nextval,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
//		st.setInt(1,uploadDTO.getPnum());
		st.setInt(1, uploadDTO.getStep());
		st.setString(2, uploadDTO.getFname());
		st.setString(3, uploadDTO.getOname());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	
	public UploadDTO selectOne(int pnum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from upload where step=1 and pnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,pnum);
		ResultSet rs = st.executeQuery();
		UploadDTO uploadDTO = null;
		if(rs.next()) {
			uploadDTO = new UploadDTO();
			uploadDTO.setNum(rs.getInt("num"));
			uploadDTO.setPnum(rs.getInt("pnum"));
			uploadDTO.setStep(rs.getInt("step"));
			uploadDTO.setFname(rs.getString("fname"));
			uploadDTO.setOname(rs.getString("oname"));
		}
			
		DBConnector.disConnect(con, st);
		return uploadDTO;
	}
	
	
}
