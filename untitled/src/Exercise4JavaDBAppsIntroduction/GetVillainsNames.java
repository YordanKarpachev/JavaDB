package Exercise4JavaDBAppsIntroduction;

import java.sql.*;
import java.util.Properties;


public class GetVillainsNames {

    public static void main(String[] args) throws SQLException {


      Connection sqlConnection = Utils.getSqlConnection();
        PreparedStatement statement = sqlConnection.prepareStatement(
                "select  v.name , count( distinct mv.minion_id) minions_count from " +
                        "villains v join minions_villains mv on v.id = mv.villain_id " +
                        "group by  mv.villain_id " +
                        "having minions_count > 15 " +
                        "order by minions_count;");

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){

            String name = resultSet.getString("name");
            int minions_count = resultSet.getInt("minions_count");
            System.out.println(name + " " + minions_count
            );

        }


    }
}

