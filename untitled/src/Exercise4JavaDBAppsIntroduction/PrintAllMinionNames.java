package Exercise4JavaDBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSqlConnection();

        PreparedStatement statement = connection.prepareStatement("select name from minions");
        ResultSet resultSet = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString("name"));
        }

        while (!names.isEmpty()) {
            System.out.println(names.remove(0));
            System.out.println(names.remove(names.size() - 1));
        }
    }
}
