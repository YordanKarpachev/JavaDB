package Exercise4JavaDBAppsIntroduction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static Exercise4JavaDBAppsIntroduction.Constants.*;

enum SqlConnection {
    ;

    static Connection getSqlConnection() throws SQLException {
        final Properties properties = new Properties();
        properties.setProperty(USER_KEY, USER_VALUE);
        properties.setProperty(PASSWORD_KEY, PASSWORD_VALUE);

        return DriverManager.getConnection(URL_DATABASE);
    }
}
