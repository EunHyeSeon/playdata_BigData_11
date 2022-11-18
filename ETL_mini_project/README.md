
# ViewCoin


### ETL을 활용한 Flask web project
### What is ETL..?
![image](https://user-images.githubusercontent.com/100753335/201651728-ab5f76e1-016c-4294-9878-80a0ddf41ba7.png)

데이터를 추출(Extract), 가공(Transform), 반환(Load) 하는 것

### 프로젝트 기획 의도
#### 데이터를 가공하여 저장하는 pipeline을 생성하고, 가공한 데이터를 차트를 활용하여 웹 페이지에 나타낸다.

### DB
![image](https://user-images.githubusercontent.com/100753335/202641242-aed81cba-0443-4c6b-8f1a-2b630d427f80.png)


### DTO
-  __init__ : 생성자
- get : @Getter
- set : @Setter
- __str__ : ToString

### DAO
 1. db에 존재하는 coin 종류 보기
 2. 입력한 코인의 data를 line graph로 반환
 3. 조회한 coin의 최저값과 최고값 반환

### TDD 활용
<img src = "https://user-images.githubusercontent.com/100753335/202444921-ae1d1270-a653-4cd1-bdcb-ae431dcbc22e.png" width="50%" height="50%">


### 구글 차트 활용
<img src = "https://user-images.githubusercontent.com/100753335/202715993-dd22e8e5-e7db-4c6d-be10-b126d28c3a95.png" width="50%" height="50%">



<br/><br/><br/><br/>


Reference <br/>
https://medium.datadriveninvestor.com/understanding-extract-transform-and-load-etl-and-its-necessity-in-data-analytics-world-with-an-64346016153d
