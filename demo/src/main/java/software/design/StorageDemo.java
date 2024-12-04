package software.design;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


interface Storage {
    void save(String data) throws SQLException;
    List<String> getAll() throws SQLException;
}

class DatabaseStorage implements Storage {
    private final Connection connection;

    public DatabaseStorage(Connection connection) throws SQLException {
        this.connection = connection;
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS storage (
                id SERIAL PRIMARY KEY,
                data TEXT NOT NULL
            );
            """;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createTableSQL);
        }
    }

    @Override
    public void save(String data) throws SQLException {
        String insertSQL = "INSERT INTO storage (data) VALUES (?);";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, data);
            pstmt.executeUpdate();
        }
    }

    @Override
    public List<String> getAll() throws SQLException {
        String selectSQL = "SELECT data FROM storage;";
        List<String> result = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                result.add(rs.getString("data"));
            }
        }
        return result;
    }
}


public class StorageDemo {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/your_database"; 
        String username = "username"; 
        String password = "password"; 

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Storage storage = new DatabaseStorage(connection);

            storage.save("Hello, World!");
            storage.save("This is a test.");
            storage.save("Java JDBC Example.");

            List<String> allData = storage.getAll();
            System.out.println("Сохраненные строки:");
            for (String data : allData) {
                System.out.println(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}