package Exercise4JavaDBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinion {

    private final static String GET_TOWN_NAME = "select name from towns where towns.name = ?;";
    private final static String ADD_TOWN = "insert into towns (name) value (?);";

    private final static String GET_TOWN_ID_BY_NAME = "select id from towns where towns.name = ?;";

    private final static String INSERT_MINION = "insert into minions(name, age, town_id) values(?, ?, ?);";
    private final static  String GET_MINION_BY_NAME = "select id from minions where minions.name = ?;";
    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        Connection sqlConnection = Utils.getSqlConnection();

        String[] inputMinionData = scanner.nextLine().split(" ");
        String minionName = inputMinionData[1];
        int minionAge = Integer.parseInt(inputMinionData[2]);
        String minionTown = inputMinionData[3];

        String villainName = scanner.nextLine().split(" ")[1];

        ResultSet townName = getTownName(sqlConnection, minionTown);

        if(!townName.next()){
            addToDatabase(sqlConnection,ADD_TOWN, List.of( minionTown));
        }

        int townId = getIdByName(sqlConnection, minionTown, GET_TOWN_ID_BY_NAME);

        addMinion(sqlConnection, INSERT_MINION, minionName, minionAge, townId);
       // addMinion(sqlConnection, minionName, minionAge, townId);

        int minionId = getIdByName(sqlConnection, minionName, GET_MINION_BY_NAME);
        System.out.println(minionId);

    }

    private static  void addToDatabase(Connection sqlConnection, String sqlQuery, List<String> arguments  ) throws SQLException {
        PreparedStatement addQuery = sqlConnection.prepareStatement(sqlQuery);
        for (int i = 1; i <= arguments.size() - 1 ; i++) {
            addQuery.setString(i, arguments.get( i - 1));

        }
        addQuery.executeUpdate();
    }

    private static ResultSet getTownName(Connection sqlConnection, String minionTown) throws SQLException {
        PreparedStatement townQuery = sqlConnection.prepareStatement(GET_TOWN_NAME);
        townQuery.setString(1, minionTown);
        ResultSet townName = townQuery.executeQuery();
        return townName;
    }

    private static int getIdByName(Connection sqlConnection, String minionTown, String query) throws SQLException {
        PreparedStatement townIdQuery = sqlConnection.prepareStatement(query);
        townIdQuery.setString(1, minionTown);
        ResultSet townIdResultQuery = townIdQuery.executeQuery();
        townIdResultQuery.next();
        int townId = townIdResultQuery.getInt("id");
        return townId;
    }

    private static void addMinion(Connection sqlConnection,String Sql, String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement insertMinionQuery = sqlConnection.prepareStatement(Sql);
        insertMinionQuery.setString(1, minionName);
        insertMinionQuery.setInt(2, minionAge);
        insertMinionQuery.setInt(3, townId);
        insertMinionQuery.executeUpdate();
    }
}
