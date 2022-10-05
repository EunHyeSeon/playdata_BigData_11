package ott.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import ott.model.dto.Users;
import ott.model.util.OttUtil;

public class UsersDAO {

	static Scanner sc = new Scanner(System.in);

	/** 회원 가입 - insert **/
	public static boolean addUser2() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("                    회원 가입 ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

			con = OttUtil.getConnection();
			pstmt = con.prepareStatement("insert into users values(?, ?, ?, ?)");
			System.out.print("|   아이디 입력  | ");
			String id = sc.next();
			pstmt.setString(1, id);
			System.out.print("|   이름 입력   | ");
			String name = sc.next();
			pstmt.setString(2, name);
			System.out.print("|  비밀번호 입력  | ");
			String pwd = sc.next();
			pstmt.setString(3, pwd);
			System.out.print("|   선호 장르    | ");
			String gen = sc.next();
			pstmt.setString(4, gen);
			int result = pstmt.executeUpdate();

			System.out.println(" 회원 가입에 성공하셨습니다.");
			System.out.println(" " + name + " 님 환영합니다. ");
			if (result == 1) {
				return true;
			}
		} finally {
			OttUtil.close(con, pstmt);
		}
		return false;
	}

	/** 모든 user 의 전체 정보 검색 - select **/
	public static ArrayList<Users> selectAllUsers() throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<Users> usersList = null;

		try {
			conn = OttUtil.getConnection();
			pstmt = conn.prepareStatement("select * from users");
			rset = pstmt.executeQuery();

			usersList = new ArrayList<Users>();

			while (rset.next()) {
				usersList.add(new Users(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			OttUtil.close(conn, pstmt, rset);
		}
		return usersList;
	}

	/** 사용자 개인정보 조회 - select **/
	public static void usersData(Users userData) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Users> userDatas = null;

		try {
			con = OttUtil.getConnection();
			pstmt = con.prepareStatement("select * from users where id=?");
			pstmt.setString(1, userData.getId());
			rset = pstmt.executeQuery();
			userDatas = new ArrayList<Users>();

			while (rset.next()) {
				System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
				userDatas.add(new Users(rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			OttUtil.close(con, pstmt, rset);
		}
	}

	/** 회원탈퇴 - delete **/
	public static boolean userDelete(Users usersData) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = OttUtil.getConnection();
			pstmt = con.prepareStatement("delete from users where id=?");
			pstmt.setString(1, usersData.getId());
			int i = pstmt.executeUpdate();

			if (i == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			OttUtil.close(con, pstmt);
		}
		return false;
	}

}
