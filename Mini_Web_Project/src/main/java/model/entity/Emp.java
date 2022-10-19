package model.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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

@Entity
public class Emp {
	@Id
	@Column
	private int empno;
	
	@Column(length=20)
	private String ename;
	
	
	@Column(length=20)
	private String job;
	
	@Column
	private Long mgr;
	
	@Column
	private Date hiredate;
	
	@Column
	private Long sal;
	
	@Column
	private Long comm;
	
	@Column
	private int deptno;
	

}
