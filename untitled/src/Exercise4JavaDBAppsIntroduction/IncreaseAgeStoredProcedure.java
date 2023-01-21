package Exercise4JavaDBAppsIntroduction;

import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
     /*   delimiter $$
        create procedure usp_get_older(in id int(5))
        begin
        update minions
        set minions.age = minions.age + 1
        where minions.id = id;
        end $$  */


        Scanner scanner = new Scanner(System.in);
        int minionId = Integer.parseInt(scanner.nextLine());

        Connection connection = Utils.getSqlConnection();

        CallableStatement clb = connection.prepareCall("call usp_get_older(?)");
        clb.setInt(1, minionId);
        clb.executeUpdate();

        PreparedStatement statement = connection.prepareStatement("select age from minions where minions.id = ?;");
        statement.setInt(1, minionId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        System.out.println(resultSet.getInt("age"));
    }
}
