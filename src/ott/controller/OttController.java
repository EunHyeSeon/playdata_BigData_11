package ott.controller;

import java.util.Scanner;

import ott.model.OttService;
import ott.model.UsersDAO;

public class OttController {
	
	static Scanner sc = new Scanner(System.in);

	/** home 메소드 **/
	public static void home() throws Exception {
		int i = sc.nextInt();
		if (i == 1) {
			/** 회원가입 **/
			UsersDAO.addUser2();
			OttService.userLogin();
		} else if (i == 2) {
			OttService.userLogin();
		}
	}

	/** 기능 카테고리 선택 메소드 **/
	public static void selectCategory() throws Exception {
		String category;

		category = OttService.selectCategory();

		switch (category) {
		case "1":
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			// System.out.println("검색 기능을 선택하였습니다.");
			OttService.selectContents();
			selectCategory();
			break;
		case "2":
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			// System.out.println("선호 장르 추천 서비스을 선택하였습니다.");
			OttService.recommendContents();
			selectCategory();

			break;
		case "3":
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("로그아웃 되었습니다.");
			OttService.logout();

			break;
		case "4":
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("개인정보를 안내드리겠습니다.");
			OttService.userContents();
			break;
		default:
			System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
			System.out.println("카테고리를 다시 선택해 주세요.");
			selectCategory();
		}

	}

}
