package model.dao;

import java.sql.SQLException;
import java.util.List;

import exception.MessageException;
import exception.NotExistException;
import model.dto.EmpDTO;

public class EmpService {
	
	//notExist
	public static void notExistEmp(int empno) throws NotExistException, SQLException{
		EmpDTO empDTO = EmpDAO.getEmployee(empno);
		if(empDTO == null){
			throw new NotExistException("검색하신 사원이 없습니다. 사원번호를 다시 확인해주세요.");
		}
	}
	
	//사원 추가
	public static boolean addEmp(EmpDTO empDTO) throws MessageException{
		boolean result = false;
		
		try {
			result = EmpDAO.addEmp(empDTO);
		} catch (SQLException s) {
			throw new MessageException("해당 번호의 사원이 이미 존재합니다.");
		}
		return result;
	}
	
	//삭제
	public static boolean deleteEmp(int empno) throws SQLException, NotExistException{
		notExistEmp(empno);
		boolean result = EmpDAO.deleteEmp(empno);
		if(!result) {
			throw new NotExistException("사원 정보가 삭제되지 않았습니다.");
		}
		return result;
	}
	
	//정보 수정
	public static boolean updateEmpJob(int empno, String job) throws SQLException, NotExistException{
		notExistEmp(empno);
		boolean result = EmpDAO.updateEmpJop(empno, job);
		if(!result) {
			throw new NotExistException("사원 정보 갱신 실패");
		}
		return result;
	}
	
	//사원 검색
	public static EmpDTO getEmployee(int empno) throws SQLException, NotExistException{
		EmpDTO empDTO = EmpDAO.getEmployee(empno);
		if(empDTO == null){
			throw new NotExistException("검색하신 사원이 존재하지 않습니다.");
		}
		return empDTO;
	}
	
	//모든 사원 반환
	public static List<EmpDTO> getEmpAll() throws SQLException, NotExistException{
		List<EmpDTO> list = EmpDAO.getAllEmp();
		if(list.size() == 0) {
			throw new NotExistException("모든 사원의 정보가 존재하지 않습니다.");
		}
		return list;
	}
	

}
