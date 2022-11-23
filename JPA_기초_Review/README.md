# JPA(Java Persistence API)

- 자바 ORM 기술에 대한 표준 명세로, JAVA에서 제공하는 API이다.
- 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스다.

***ORM*** 
- Object-relational mapping(객체 관계 매핑)
- 객체는 객체대로, 관계형 DB는 관계형 DB대로 설계

 때문에 자바 클래스와 DB테이블을 매핑한다.
 
***JPA 동작과정***
![image](https://user-images.githubusercontent.com/100753335/203504320-1ed5395e-cc6d-45bd-a7f2-3b456da07107.png)


<h3> 목표 : JPA 통해 DB와 WEB 연동하기 </h3>

기술 set : java(eclipse), log4j

DB에 존재하는 emp table을 CRUD하는 기능 구현

<h5>♟ EMP Table</h5> 

![image](https://user-images.githubusercontent.com/100753335/196572240-2fa56223-0e8c-45b2-be83-853e5b836f23.png)

!
db.properties, log4j.properties path 수정

