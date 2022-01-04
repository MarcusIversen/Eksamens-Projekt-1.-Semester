package dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnector {

    private SQLServerDataSource dataSource;

    /**
     * Database connector constructoren, her skriver vi alle vores login data til databasen.
     */
    public DatabaseConnector() {
        dataSource = new SQLServerDataSource();
        dataSource.setServerName("10.176.111.31");
        dataSource.setDatabaseName("PrivateMovieCollection2022");
        dataSource.setUser("CSe21A_5");
        dataSource.setPassword("CSe21A_5");
        dataSource.setPortNumber(1433);
    }

    /**
     * Her bruges vores database login data til at connecte, ved brug af getConnection();
     * @return
     * @throws SQLServerException
     */
    public Connection getConnection() throws SQLServerException {
        return dataSource.getConnection();
    }

    public static void main(String[] args) throws SQLException {
        DatabaseConnector databaseConnector = new DatabaseConnector();

        try(Connection connection = databaseConnector.getConnection()){
            System.out.println("is it open?  :  " + !connection.isClosed());
        }
    }
}
