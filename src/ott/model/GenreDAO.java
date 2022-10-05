package ott.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import ott.model.dto.Genre;
import ott.model.util.OttUtil;

public class GenreDAO {

	/** genre 검색 - select **/
	public static Genre selectAllGenre() throws Exception {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Genre genre = null;
		
		try {
			conn = OttUtil.getConnection();
			pstmt = conn.prepareStatement("select * from genre");
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				genre = new Genre(rset.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OttUtil.close(conn, pstmt, rset);
		}
		return genre;
	}
}
