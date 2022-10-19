package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.NotExistException;
import model.dao.EmpDAO;
import model.dao.EmpService;
import model.dto.EmpDTO;

@WebServlet("/emp")
public class EmpController extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");

		String command = request.getParameter("command");

		try {
			switch (command) {
				case "empAll":
					getEmpAll(request, response);
					break; 
				case "employee":
					getEmployee(request, response);
					break;
				case "empInsert":
					addEmp(request, response);
					break;
				case "empUpdateReq":
					empUpdateReq(request, response);
					break;
				case "empUpdate":
					empUpdate(request, response);
					break;
				case "empDelete":
					empDelete(request, response);
					break;
			}

		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace(); //순서
			request.getRequestDispatcher("showError.jsp").forward(request, response);
			
		}
	}

	private void empDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			String empno1 = request.getParameter("empno");
			if (empno1 != null && empno1.length() != 0) {
				int empno = Integer.parseInt(empno1);
				if (EmpService.deleteEmp(empno)) {
					request.setAttribute("getEmpAll", EmpService.getEmpAll());
					url = "empList.jsp";
				} else {
					request.setAttribute("errorMsg", "다시 시도 해주세요");
				}
			} else {
				request.setAttribute("errorMsg", "입력이 되지 않았습니다.");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", "사원의 정보가 있습니다");
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void empUpdate(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, NotExistException, ServletException, IOException {
		String url = "showError.jsp";
		try {
			int empno = Integer.parseInt(request.getParameter("empno"));
			String job = request.getParameter("job");
			EmpService.updateEmpJob(empno, job);
			request.setAttribute("employee", EmpService.getEmployee(empno));
			url = "empDetail.jsp";
		} catch (SQLException s) {
			request.setAttribute("errormsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	private void empUpdateReq(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("employee", EmpService.getEmployee(Integer.parseInt(request.getParameter("empno"))));
			url = "empUpdate.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 사원 추가
	private void addEmp(HttpServletRequest request, HttpServletResponse response) throws ParseException, ServletException, IOException {
		String url = "showError.jsp";

		int empno = Integer.parseInt(request.getParameter("empno"));
		String name = request.getParameter("ename");
		String job = request.getParameter("job");
		Long mgr = Long.parseLong(request.getParameter("mgr"));
		java.sql.Date hiredate = java.sql.Date.valueOf(request.getParameter("hiredate"));
		Long sal = Long.parseLong(request.getParameter("sal"));
		Long comm = Long.parseLong(request.getParameter("comm"));
		int deptno = Integer.parseInt(request.getParameter("deptno"));

		EmpDTO empDTO = new EmpDTO(empno, name, job, mgr, hiredate, sal, comm, deptno);

		try {
			boolean result = EmpDAO.addEmp(empDTO);
			if (result) {
				request.setAttribute("employee", empDTO);
				request.setAttribute("successMsg", "사원 등록 완료");
				url = "empDetail.jsp";
			} else {
				request.setAttribute("errorMsg", "다시 시도하세요");
			}
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 사원 검색
	private void getEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "showError.jsp";
		try {
			request.setAttribute("employee", EmpService.getEmployee(Integer.parseInt(request.getParameter("empno"))));
			url = "empDetail.jsp";
		} catch (Exception s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	// 모든 사원 반환
	public void getEmpAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NotExistException {
		String url = "showError.jsp";
		try {
			request.setAttribute("getEmpAll", EmpService.getEmpAll());
			url = "empList.jsp";
		} catch (SQLException s) {
			request.setAttribute("errorMsg", s.getMessage());
			s.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
