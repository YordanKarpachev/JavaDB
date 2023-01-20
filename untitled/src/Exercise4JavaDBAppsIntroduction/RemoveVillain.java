package Exercise4JavaDBAppsIntroduction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain {

    private static final String GET_VILLAINS_NAME = "select name from villains v where v.id = ?;";
    private static final String NO_SUCH_VILLAIN = "No such villain was found";
    private static final String DEL_FROM_MV = "delete from minions_villains where villain_id = ?;";

    private static final String DEL_FROM_V = "delete from villains where villains.id = ?;";

    private static final String COUNT_MINIONS = "select count(*) from minions_villains where villain_id = ?;";

    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSqlConnection();

        Scanner scanner = new Scanner(System.in);
        int idVillain = Integer.parseInt(scanner.nextLine());

        PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAME);
        statement.setInt(1, idVillain);
        ResultSet resultSet = statement.executeQuery();

        if (!resultSet.next()) {
            System.out.println(NO_SUCH_VILLAIN);
            return;
        }


        String villainsName = resultSet.getString("name");
        connection.setAutoCommit(false);
        PreparedStatement deleteFromMV = connection.prepareStatement(DEL_FROM_MV);
        deleteFromMV.setInt(1, idVillain);
        PreparedStatement delFromV = connection.prepareStatement(DEL_FROM_V);
        delFromV.setInt(1, idVillain);
        try {
            int i = deleteFromMV.executeUpdate();
            delFromV.executeUpdate();
            connection.commit();
            System.out.println(villainsName + " was deleted");
            System.out.println(i + " minions released");
        } catch (SQLException e) {
            connection.rollback();
        }
        ;
    }
}
