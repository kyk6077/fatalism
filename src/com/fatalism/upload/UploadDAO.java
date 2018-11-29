package com.fatalism.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fatalism.util.DBConnector;

public class UploadDAO {
	
	
	//pnum은 올릴때 product num 받아서 올림
	public int insert(UploadDTO uploadDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into upload values(upload_seq.nextval,1,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
//		st.setInt(1,uploadDTO.getPnum());
		st.setInt(1, uploadDTO.getStep());
		st.setString(2, uploadDTO.getFname());
		st.setString(3, uploadDTO.getOname());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public int productInsert(UploadDTO uploadDTO) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="insert into upload values(upload_seq.nextval,?,?,?,?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uploadDTO.getPnum());
		st.setInt(2, uploadDTO.getStep());
		st.setString(3, uploadDTO.getFname());
		st.setString(4, uploadDTO.getOname());
		int result = st.executeUpdate();
		
		DBConnector.disConnect(con, st);
		return result;
	}
	
	public UploadDTO selectOne(int pnum) throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from upload where step=0 and pnum=?";
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
			
		DBConnector.disConnect(con, st, rs);
		return uploadDTO;
	}
	
	public List<UploadDTO> selectList() throws Exception {
		Connection con = DBConnector.getConnect();
		String sql="select * from upload";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();
		List<UploadDTO> ar = new ArrayList<>();
		if(rs.next()) {
			UploadDTO uploadDTO = new UploadDTO();
			uploadDTO = new UploadDTO();
			uploadDTO.setNum(rs.getInt("num"));
			uploadDTO.setPnum(rs.getInt("pnum"));
			uploadDTO.setStep(rs.getInt("step"));
			uploadDTO.setFname(rs.getString("fname"));
			uploadDTO.setOname(rs.getString("oname"));
			ar.add(uploadDTO);
		}

		DBConnector.disConnect(con, st, rs);
		return ar;
	}
	
	
}
