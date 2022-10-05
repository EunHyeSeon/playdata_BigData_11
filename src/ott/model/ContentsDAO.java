package ott.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ott.model.dto.Contents;
import ott.model.util.OttUtil;

public class ContentsDAO {
	
	/** 모든 contents 의 전체 정보 검색 - select **/
	public static ArrayList<Contents> selectAllContents() throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList <Contents> contentsList = null;
		
		try {
			conn = OttUtil.getConnection();
			pstmt = conn.prepareStatement("select * from contents");
			rset = pstmt.executeQuery();
			
			contentsList = new ArrayList<Contents>();
			
			while(rset.next()) {
				contentsList.add(new Contents(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4), rset.getString(5)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OttUtil.close(conn, pstmt, rset);
		}
		return contentsList;
	}
	
	
	/** 선호 장르의 컨텐츠 제목들 반환 - select **/
		public static ArrayList<String> returnContentsList(String gen) throws Exception {
			ArrayList<String> conList = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			try {
				con = OttUtil.getConnection();
				pstmt = con.prepareStatement("select * from contents where genre_name = ?");
				pstmt.setString(1, gen);
				rset = pstmt.executeQuery();
				
				conList = new ArrayList<String>();
				while(rset.next()) {
					conList.add(rset.getString("contents_name"));
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}finally {
				OttUtil.close(con, pstmt, rset);
			}
			return conList;
		}

}
