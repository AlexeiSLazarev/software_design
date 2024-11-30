import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Интерфейс хранилища
interface Storage {
    void save(String data) throws SQLException;
    List<String> getAll() throws SQLException;
}

// Реализация Storage с использованием базы данных
class DatabaseStorage implements Storage {
    private final Connection connection;

    public DatabaseStorage(Connection connection) throws SQLException {
        this.connection = connection;
        initializeDatabase();
    }

    // Инициализация таблицы в базе данных
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

    // Сохранение строки в базу данных
    @Override
    public void save(String data) throws SQLException {
        String insertSQL = "INSERT INTO storage (data) VALUES (?);";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, data);
            pstmt.executeUpdate();
        }
    }

    // Извлечение всех строк из базы данных
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

// Главный класс программы
public class StorageDemo {
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/your_database"; // Замените на ваш URL базы данных
        String username = "your_username"; // Замените на ваше имя пользователя
        String password = "your_password"; // Замените на ваш пароль

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Storage storage = new DatabaseStorage(connection);

            // Сохранение данных
            storage.save("Hello, World!");
            storage.save("This is a test.");
            storage.save("Java JDBC Example.");

            // Извлечение данных
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