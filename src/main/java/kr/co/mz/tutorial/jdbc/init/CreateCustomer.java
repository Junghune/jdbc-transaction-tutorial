package kr.co.mz.tutorial.jdbc.init;

import java.io.IOException;
import java.sql.SQLException;
import kr.co.mz.tutorial.jdbc.db.HikariPoolFactory;
import kr.co.mz.tutorial.jdbc.model.Customer;

public class CreateCustomer {

    private static final String QUERY = """
        insert into customer(customer_id,password,name,address) 
        values(?,?,?,?)""";

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("등록된 유저 수 : " +
            createCustomer(
                new Customer("megaLim", "passwor&^%", "KimSeokLim", "Busan in Korea")
            )
        );
    }

    public static int createCustomer(Customer customer) throws SQLException, IOException {
        var dataSource = HikariPoolFactory.createHikariDataSource();
        try (var connection = dataSource.getConnection();
            var preparedStatement = connection.prepareStatement(QUERY)) {
            preparedStatement.setString(1, "megaLim");
            preparedStatement.setString(2, "passwor&^%");
            preparedStatement.setString(3, "KimSeokLim");
            preparedStatement.setString(4, "Busan in Korea");
            return preparedStatement.executeUpdate();
        }
    }

}
