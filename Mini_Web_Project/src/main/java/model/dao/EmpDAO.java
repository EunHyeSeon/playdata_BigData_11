package model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.modelmapper.ModelMapper;

import model.dto.EmpDTO;
import model.entity.Emp;
import util.DBUtil;

public class EmpDAO {
	private static EmpDAO instance = new EmpDAO();
	
	public EmpDAO() {}
//	private EmpDAO() {}
	
	public static EmpDAO getInstance() {
		return instance;
	}
	
	private static ModelMapper mapper = new ModelMapper();
	
	//emp 추가
	public static boolean addEmp(EmpDTO empDTO) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Emp emp = mapper.map(empDTO, Emp.class);
			em.persist(emp);
			tx.commit();
			result= true;
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	//모든 emp 반환
	public static List<EmpDTO> getAllEmp() throws SQLException {
		EntityManager em = DBUtil.getEntityManager();
		List<EmpDTO> list = null;
		try {
			List<Emp> emps = em.createQuery("select e from Emp e", Emp.class).getResultList();
			list = emps.stream().map(p -> mapper.map(p, EmpDTO.class)).collect(Collectors.toList());
		} finally {
			em.close();
			em = null;
		}
		return list;	
	}
	
	//empno로 emp 검색
	public static EmpDTO getEmployee(int empno) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EmpDTO empDTO = null;
		
		try {
			Emp emp = em.find(Emp.class, empno);
			empDTO = mapper.map(emp, EmpDTO.class);	
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;			
		}
		return empDTO;
	}
	
	//empno로 job 수정
	public static boolean updateEmpJop(int empno, String job) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Emp emp = em.find(Emp.class, empno);
			emp.setJob(job);
			tx.commit();
			result= true;
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	
	
	//emp 삭제
	public static boolean deleteEmp(int empno) throws SQLException{
		EntityManager em = DBUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean result = false;
		tx.begin();
		
		try {
			Emp emp = em.find(Emp.class, empno);
			em.remove(emp);
			tx.commit();
			result= true;
		} finally {
			em.close();
			em = null;
		}
		return result;
	}
	

}
