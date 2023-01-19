package Exercise4JavaDBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing {

    private final static String COUNT_CHANGED = "%d town names were affected.%n";
    private static final String UPDATE_QUERY = "update towns set name = UPPER(name) where towns.country = ?;";
    private static final String NO_TOWN_AFFECTED = "No town names were affected.";

    private static final String GET_TOWN = "select name from towns where towns.country = ?;";
    public static void main(String[] args) throws SQLException {

        final String country = new Scanner(System.in).nextLine();

        Connection connection = Utils.getSqlConnection();
        PreparedStatement update = connection.prepareStatement(UPDATE_QUERY);
        update.setString(1, country);

        int countAffectedTown = update.executeUpdate();

        if(countAffectedTown == 0){
            System.out.println(NO_TOWN_AFFECTED);
        return;
        }
        System.out.printf(COUNT_CHANGED, countAffectedTown);

        PreparedStatement getTown = connection.prepareStatement(GET_TOWN);
        getTown.setString(1, country);
        ResultSet resultSet = getTown.executeQuery();

        List<String> updatedTowns = new ArrayList<>();
         while (resultSet.next()){
           updatedTowns.add(resultSet.getString("name"));
         }

        System.out.println(updatedTowns);

    }
}
