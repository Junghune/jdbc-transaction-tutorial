package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;

public class CreateCustomer {

    private static final String QUERY = """
        insert into customer(customer_id,password,name,address) 
        values(?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        var connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
        preparedStatement.setString(1, "megaLim");
        preparedStatement.setString(2, "passwor&^%");
        preparedStatement.setString(3, "KimSeokLim");
        preparedStatement.setString(4, "Busan in Korea");
        int result = preparedStatement.executeUpdate();
        System.out.println("쿼리 성공한 행수 : " + result);
    }

}
