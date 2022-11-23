package model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@ToString
public class EmpDTO {
	
	private int empno;
	
	private String ename;
	
	private String job;
	
	private Long mgr;
	
	private Date hiredate;
	
	private Long sal;
	
	private Long comm;
	
	private int deptno;
	
	
}
