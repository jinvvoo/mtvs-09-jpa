/*
*       JDBC
* */
package com.ohgiraffers.section01.problem;


import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProblemsOfUsingDirectSQLTests {

    // DB접속을 위한 커넥션 생성
    private Connection con;

    @BeforeEach
        // 단위테스트 동작하기 전에 매번 수행되는 것.
    void setConnection() throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/menu";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        Class.forName(driver);      //driver를 동적으로

        con = DriverManager.getConnection(url, user, password);
        con.setAutoCommit(false);

    }

    @AfterEach
    void closeConnection() throws SQLException {
        con.rollback();
        con.close();
    }

    /* 필기.
     *   JDBC API를 이용해 직접 SQL을 다룰 떄 발생할 수 있는 문제점
     *   1. 데이터 변환, SQL 작성, JDBC API 코드 등을 중복 작성(개발 시간 증가, 유지보수성 약화)
     *   2. SQL에 의존적인 개발
     *   3. 패러다임 불일치 문제 (상속, 연관관계, 객체 그래프 탐색)
     *   4. 동일성 보장 문제 (서로 주솟값이 다름, 경우에따라 동일성보장되어야함. ex 1번메뉴와1번메뉴가 같은지 비교하는상황.
     * */

    /* 목차. 1. 데이터 변환, SQL 작성, JDBC API 코드 중복 작성 */
    @DisplayName("직접 SQL을 작성하여 메뉴를 조회할 때 발생하는 문제 확인")
    @Test
    void testDirectSelectSql() throws SQLException {

        // given
        String query = "SELECT MENU_CODE, MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS FROM TBL_MENU";

        // when
        Statement stmt = con.createStatement();
        ResultSet rset = stmt.executeQuery(query);

        List<Menu> menuList = new ArrayList<>();
        while (rset.next()) {
            Menu menu = new Menu();
            menu.setMenuCode(rset.getInt("MENU_CODE"));
            menu.setMenuName(rset.getString("MENU_NAME"));
            menu.setMenuPrice(rset.getInt("MENU_PRICE"));
            menu.setCategoryCode(rset.getInt("CATEGORY_CODE"));
            menu.setOrderableStatus(rset.getString("ORDERABLE_STATUS"));

            menuList.add(menu);

            // 이런 변환 작업을 개발자가 직접 해야하는 불편함
        }

        // then
        Assertions.assertNotNull(menuList);
        menuList.forEach(System.out::println);


        rset.close();
        stmt.close();


    }

    @DisplayName("직접 SQL을 작성하여 신규 메뉴를 추가할 시 발생하는 문제 확인")
    @Test
    void testDirectInsertSQL() throws SQLException {

        // given
        Menu menu = new Menu();
        menu.setMenuName("멸치알쉐이크");
        menu.setMenuPrice(10000);
        menu.setCategoryCode(9);
        menu.setOrderableStatus("Y");

        String query = "INSERT INTO TBL_MENU(MENU_NAME, MENU_PRICE, CATEGORY_CODE, ORDERABLE_STATUS) VALUES(?,  ?, ?, ?)";


        //when
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, menu.getMenuName());
        pstmt.setInt(2, menu.getMenuPrice());
        pstmt.setInt(3, menu.getCategoryCode());
        pstmt.setString(4, menu.getOrderableStatus());

        int result = pstmt.executeUpdate();

        //then
        Assertions.assertEquals(1, result);

        pstmt.close();
    }

    /* 설명.
    *   List.add(menu);
    *   List.get(i);
    * */

    /* 목차. 2. SQL에 의존적인 개발을 하게 된다.*/

}
