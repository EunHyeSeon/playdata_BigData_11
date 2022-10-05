package ott.view;

import java.util.ArrayList;

import ott.model.dto.Contents;
import ott.model.dto.Users;

public class EndView {

	/** 시작 화면 View **/
	public static void startView() {

		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");

		System.out.println("   _____    _______   __      ______  __   __\r\n"
				+ "  /  _  \\   |  ____| | |      |_   _| \\ \\ / /\r\n"
				+ "  | | | |   | |__    | |        | |    \\ V /\r\n"
				+ "  | | | |   |  __|   | |        | |     > <   \r\n"
				+ "  | |_| |   | |      | |____   _| |_   / . \\\r\n"
				+ "  \\_____/   |_|      |______| |_____| /_/ \\_\\");
		
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("");

		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("                     home ");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("    1. 회원가입 ");
		System.out.println("    2. 로그인 ");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.print("원하시는 절차를 선택해주세요.");

	}

	/** 로그인 성공 시 View **/
	public static void loginView(String name) {
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println(" " + name + " 고객님 환영합니다.");
	}

	/** contents 검색 기능 View **/
	public static void contentsSView(Contents contents) {
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println("                    검색결과 ");
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
		System.out.println(contents);
	}

	/** 추천 장르 서비스 컨텐츠 이름 View **/
	public static void contentsList(ArrayList<String> result) {
		for (int i = 0; i < result.size(); i++) {
			System.out.print(" " + result.get(i) + " ");
		}
		System.out.println(" ");
	}

	/** 추천 장르 서비스 컨텐츠 정보 View **/
	public static void contentsRView(Contents c) {
		System.out.println(c);
		System.out.println("⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯⎯");
	}

	/** 개인 정보 확인 View **/
	public static void printUser(Users user) {
		System.out.println(user);
	}

}
