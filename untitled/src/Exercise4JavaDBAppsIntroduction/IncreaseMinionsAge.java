package Exercise4JavaDBAppsIntroduction;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class IncreaseMinionsAge {

    private final static String UPDATE_MINION = "update  minions set minions.name = LOWER(minions.name), minions.age = minions.age + 1 where minions.id = ?";
    private final static String SELECT_MINION = "select name, age from minions;";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        int[] ids = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Connection connection = Utils.getSqlConnection();
        PreparedStatement update = connection.prepareStatement(UPDATE_MINION);
        PreparedStatement select = connection.prepareStatement(SELECT_MINION);

        Consumer<Integer> con = a -> {
            try {
                update.setInt(1, a);
                update.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }};
        Arrays.stream(ids).forEach(con::accept);

        ResultSet resultSet = select.executeQuery();
        while (resultSet.next()){
            System.out.printf("%s %d%n", resultSet.getString("name"), resultSet.getInt("age"));
        }


    }
}
