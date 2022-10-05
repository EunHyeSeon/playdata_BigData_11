package ott.view;


import ott.controller.OttController;


public class StartView {

	public static void main(String[] args) throws Exception {

		/** 시작 화면 **/
		EndView.startView();

		/** 서비스 시작 **/
		OttController.home();

	}

}
