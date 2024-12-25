import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GameWorldConfigManager {
    private Properties properties;

    public GameWorldConfigManager(String propertiesFilePath) {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Error loading configuration file: " + e.getMessage());
        }
    }

    public String getStringProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public int getIntProperty(String key, int defaultValue) {
        try {
            return Integer.parseInt(properties.getProperty(key, String.valueOf(defaultValue)));
        } catch (NumberFormatException e) {
            System.err.println("Invalid integer for key: " + key);
            return defaultValue;
        }
    }

    public static void main(String[] args) {
        GameWorldConfigManager configManager = new GameWorldConfigManager("gameworld.properties");

        String worldType = configManager.getStringProperty("world.type", "default");
        int worldWidth = configManager.getIntProperty("world.width", 100);
        int worldHeight = configManager.getIntProperty("world.height", 100);
        int maxPlayers = configManager.getIntProperty("world.maxPlayers", 10);

        System.out.println("World Type: " + worldType);
        System.out.println("World Width: " + worldWidth);
        System.out.println("World Height: " + worldHeight);
        System.out.println("Max Players: " + maxPlayers);

        GameWorld gameWorld = new GameWorld(worldType, worldWidth, worldHeight, maxPlayers);
        gameWorld.initialize();
    }
}

class GameWorld {
    private String type;
    private int width;
    private int height;
    private int maxPlayers;

    public GameWorld(String type, int width, int height, int maxPlayers) {
        this.type = type;
        this.width = width;
        this.height = height;
        this.maxPlayers = maxPlayers;
    }

    public void initialize() {
        System.out.println("Initializing game world...");
        System.out.println("Type: " + type);
        System.out.println("Dimensions: " + width + "x" + height);
        System.out.println("Max Players: " + maxPlayers);
        // Логика создания мира
    }
}
