package ott.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import ott.controller.OttController;
import ott.model.dto.Contents;
import ott.model.dto.Users;
import ott.view.EndView;
import ott.view.FailView;
import ott.view.SuccessView;

public class OttService {

	static Users connUser = null; // 로그인된 계정 정보를 저장하는 전역 변수
	static Logger logger = Logger.getLogger("OFLIXprogram");
	static Scanner sc = new Scanner(System.in);

	/** 로그인 메소드 **/
	public static boolean login() throws Exception {

		try {

			String inputId = null;
			String inputPw = null;

			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("                     로그인 ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.print("| id | ");
			inputId = sc.next();
			System.out.print("| pw | ");
			inputPw = sc.next();

			ArrayList<Users> usersList = UsersDAO.selectAllUsers();

			for (Users u : usersList) {
				if (u.getId().equals(inputId)) {
					if (u.getPassword().equals(inputPw)) {
						connUser = u;
						EndView.loginView(u.getName());
						logger.debug(u.getName() + "회원님이 로그인하셨습니다.");
						return false;
					} else {
						FailView.printMsg("비밀번호를 다시 확인해주세요.");
						
						return true;
					}
				}
			}

			FailView.printMsg("존재하지 않는 계정입니다. 다시 시도 바랍니다.");

		} catch (Exception e) {
			e.printStackTrace();
			FailView.printMsg("로그인 실패하였습니다. 다시 시도 바랍니다.");
		}
		return true;

	}

	/** 로그인 제어 메소드 **/
	public static void userLogin() {
		boolean o = false;
		do {
			try {
				o = login();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} while (o);

		/** 선호 장르 추천 컨텐츠 목록 **/
		try {
			returnContentsList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		/** 카테고리 기능 선택 **/
		try {
			OttController.selectCategory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 로그아웃 **/
	public static void logout() throws Exception {

		try {
			logger.debug(connUser.getName() + "회원님이 로그아웃하셨습니다.");
			connUser = null;

			boolean o;
			do {
				o = OttService.login();
			} while (o);

			OttController.selectCategory();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/** 카테고리 선택 - 개별 기능 **/
	public static String selectCategory() throws Exception {
		try {
			String category = null;
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("                   ott 기능 ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("    1. 검색 ");
			System.out.println("    2. 추천 컨텐츠 상세 정보 ");
			System.out.println("    3. 로그아웃 ");
			System.out.println("    4. 개인 정보 ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.print("원하시는 기능을 선택해주세요 ");
			category = sc.next();

			return category;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 검색 기능 **/
	public static boolean selectContents() throws Exception {
		try {

			String inputTitle = null;
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("             원하시는 컨텐츠를 검색하세요. ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.print("| 제목 | ");
			inputTitle = sc.next();

			ArrayList<Contents> contentsList = ContentsDAO.selectAllContents();

			for (Contents c : contentsList) {
				if (c.getContents_name().equals(inputTitle)) {
					System.out.println(c.getContents_name());
					EndView.contentsSView(c);
					logger.debug(c.getContents_name() + "검색 하셨습니다.");
					return false;
				}
			}

			FailView.printMsg("해당 컨텐츠가 없습니다.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/** 로그인 시, 선호 장르 추천 컨텐츠 제공 기능 (컨텐츠 이름) **/
	public static void returnContentsList() throws Exception {
		ArrayList<String> result = null;
		try {
			result = ContentsDAO.returnContentsList(connUser.getGenre_name());
			if (result != null) {
				System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
				System.out.println("             선호하시는 장르의 추천 컨텐츠    ");
				System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
				EndView.contentsList(result);
				System.out.println("");
				System.out.println("  ** 컨텐츠의 상세 정보를 원하시면 '추천 컨텐츠 상세 정보' 기능을 이용하세요.");
			} else {
				FailView.printMsg("선호하는 장르가 없습니다.");
			}
		} catch (Exception s) {
			s.printStackTrace();
			FailView.printMsg("목록을 불러오지 못했습니다.");
		}
	}

	/** 추천 컨텐츠 상세 정보 기능 **/
	public static boolean recommendContents() throws Exception {
		try {

			// Users connUser = OttService.saveLogin(connUser);
			ArrayList<Users> usersList = UsersDAO.selectAllUsers();
			ArrayList<Contents> contentsList = ContentsDAO.selectAllContents();

			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("                추천 컨텐츠 상세 정보 ");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

			for (Users u : usersList) {
				if (u.getGenre_name() != null && u.getName().equals(connUser.getName())) {
					for (Contents c : contentsList) {
						if (c.getGenre_name().equals(u.getGenre_name())) {
							EndView.contentsRView(c);
						}
					}
					return false;
				}
			}
			FailView.printMsg("고객님의 장르 정보가 기입 되어있지 않습니다. 회원 정보를 확인해 주세요. "); // 다른 멘트로 할까요 ?~?,,,

		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/** 개인 정보 조회 **/
	public static void userContents() {

		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("		개인 정보");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

		try {
			EndView.printUser(connUser);
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("      1. 전 화면으로 돌아가기  ");
			System.out.println("      2. 회원탈퇴");
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			int n = sc.nextInt();
			if (n == 2) {
				userDelete();
			} else if (n == 1) {
				OttController.selectCategory();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 회원탈퇴 **/
	public static void userDelete() {
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("                비밀번호를 입력해 주세요          ");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		String pw = sc.next();

		if (pw.equals(connUser.getPassword())) {
			try {
				if (UsersDAO.userDelete(connUser) == true) {
					SuccessView.printMsg("탈퇴 성공하셨습니다. 그 동안 이용해주셔서 감사합니다");
					logger.debug(connUser.getName() + "님이 탈퇴하셨습니다. ");
				} else {
					FailView.printMsg("탈퇴 실패하셨습니다. 다시 시도해 주세요");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			FailView.printMsg("password 가 틀립니다.");
			userDelete();
		}
	}

}
