package Exercise4JavaDBAppsIntroduction;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "123456");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", properties);


        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());


        PreparedStatement resultSet1 = connection.prepareStatement("select v.name from minions_villains " +
                "         join villains v on v.id = minions_villains.villain_id " +
                "where villain_id = ?;");
        resultSet1.setInt(1, n);
        ResultSet res = resultSet1.executeQuery();

        if (!res.next()) {
            System.out.println("No villain with ID 10 exists in the database.");
            connection.close();
            return;
        }

        System.out.println("Villian: " + res.getString("name"));

        PreparedStatement statement = connection.prepareStatement("select  m.name, m.age from minions_villains " +
                "join minions m on m.id = minions_villains.minion_id " +
                "                      where villain_id = ?");
        statement.setInt(1, n);
        ResultSet resultSet = statement.executeQuery();

        for (int i = 1; resultSet.next(); i++) {

            System.out.println(i + ". " + resultSet.getString("name ") + resultSet.getInt("age"));
        }
    }
}
