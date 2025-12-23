---
comments: true
---

# Use Config

Allay provides a flexible configuration file handler that supports multiple formats. This tutorial will show you how
to use the `Config` class to manage configuration files in your plugins.

## Supported Formats

The Config class supports the following formats:

| Format     | File Extensions          | Description                                 |
|------------|--------------------------|---------------------------------------------|
| YAML       | `.yml`, `.yaml`          | Human-readable format, supports nested data |
| JSON       | `.json`, `.js`           | Widely used data interchange format         |
| Properties | `.properties`, `.cnf`    | Simple key-value pairs                      |
| Enum       | `.txt`, `.list`, `.enum` | Simple list of values (one per line)        |

## Creating a Config

### Basic Usage

```java linenums="1"
import org.allaymc.api.utils.config.Config;

public class MyPlugin extends Plugin {
    private Config config;

    @Override
    public void onEnable() {
        // Create a config file in the plugin's data folder
        File configFile = new File(getPluginContainer().dataFolder().toFile(), "config.yml");
        config = new Config(configFile, Config.YAML);
    }
}
```

### With Default Values

You can provide default values that will be used if the config file doesn't exist:

```java linenums="1"
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.config.ConfigSection;

ConfigSection defaults = new ConfigSection();
defaults.put("debug", false);
defaults.put("maxPlayers", 100);
defaults.put("welcomeMessage", "Welcome to the server!");

Config config = new Config(configFile, Config.YAML, defaults);
```

### Auto-Detect Format

If you want the Config class to automatically detect the format based on file extension:

```java linenums="1"
// Automatically detects format from file extension
Config config = new Config(configFile, Config.DETECT);
```

## Reading Values

### Basic Getters

```java linenums="1"
// Get values with type-specific methods
String name = config.getString("server.name");
int port = config.getInt("server.port");
double ratio = config.getDouble("settings.ratio");
boolean debug = config.getBoolean("settings.debug");
long timestamp = config.getLong("lastUpdate");

// Get with default value (returned if key doesn't exist)
String motd = config.getString("motd", "Default MOTD");
int maxPlayers = config.getInt("maxPlayers", 20);
boolean enabled = config.getBoolean("enabled", true);
```

### Nested Keys (Dot Notation)

Config supports dot notation to access nested values:

```yaml title="config.yml"
database:
  host: localhost
  port: 3306
  credentials:
    username: admin
    password: secret
```

```java linenums="1"
String host = config.getString("database.host");           // "localhost"
int port = config.getInt("database.port");                 // 3306
String user = config.getString("database.credentials.username"); // "admin"
```

### Getting Lists

```java linenums="1"
// Get typed lists
List<String> worlds = config.getStringList("worlds");
List<Integer> ports = config.getIntegerList("ports");
List<Double> ratios = config.getDoubleList("ratios");
List<Boolean> flags = config.getBooleanList("flags");

// Get a generic list
List<?> items = config.getList("items");
```

### Getting Sections

```java linenums="1"
// Get a section as ConfigSection
ConfigSection dbSection = config.getSection("database");
String host = dbSection.getString("host");
int port = dbSection.getInt("port");

// Get all sections at root level
ConfigSection allSections = config.getSections();

// Get all sections under a specific path
ConfigSection subSections = config.getSections("servers");
```

## Writing Values

### Setting Values

```java linenums="1"
// Set simple values
config.set("server.name", "My Server");
config.set("server.port", 19132);
config.set("settings.debug", true);
config.set("settings.ratio", 1.5);

// Set lists
config.set("admins", Arrays.asList("Steve", "Alex", "Notch"));
config.set("ports", Arrays.asList(19132, 19133, 19134));

// Set nested values (automatically creates parent sections)
config.set("database.credentials.username", "newuser");
```

### Saving the Config

```java linenums="1"
// Save synchronously
config.save();

// Save to a different file
config.save(new File("backup-config.yml"));
```

## Checking and Removing Keys

```java linenums="1"
// Check if a key exists
if (config.exists("database.host")) {
    // Key exists
}

// Check with case-insensitive matching
if (config.exists("DATABASE.HOST", true)) {
    // Key exists (ignoring case)
}

// Remove a key
config.remove("oldSetting");

// Get all keys
Set<String> keys = config.getKeys();           // Include nested keys
Set<String> topKeys = config.getKeys(false);   // Only top-level keys
```

## Type Checking

```java linenums="1"
// Check value types before getting
if (config.isString("name")) {
    String name = config.getString("name");
}

if (config.isInt("count")) {
    int count = config.getInt("count");
}

if (config.isList("items")) {
    List<?> items = config.getList("items");
}

if (config.isSection("database")) {
    ConfigSection db = config.getSection("database");
}
```

## Reloading Config

To reload the config from disk (useful when the file might have been modified externally):

```java linenums="1"
config.reload();
```

## Complete Example

Here's a complete example of using Config in a plugin:

**config.yml:**
```yaml
# Server settings
server:
  name: "My Awesome Server"
  port: 19132
  maxPlayers: 100

# Feature toggles
features:
  pvp: true
  hunger: true
  fall-damage: false

# Spawn location
spawn:
  world: "world"
  x: 0.0
  y: 64.0
  z: 0.0

# Banned items
bannedItems:
  - "minecraft:tnt"
  - "minecraft:bedrock"
  - "minecraft:command_block"

# Database configuration
database:
  type: "sqlite"
  file: "data.db"
```

**MyPlugin.java:**
```java linenums="1"
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.config.ConfigSection;

import java.io.File;
import java.util.List;

public class MyPlugin extends Plugin {
    private Config config;

    @Override
    public void onEnable() {
        // Create config with defaults
        loadConfig();

        // Read configuration
        String serverName = config.getString("server.name");
        int maxPlayers = config.getInt("server.maxPlayers");
        boolean pvpEnabled = config.getBoolean("features.pvp");

        getPluginLogger().info("Server: {} (max {} players, PvP: {})",
                serverName, maxPlayers, pvpEnabled);

        // Read spawn location
        String spawnWorld = config.getString("spawn.world");
        double spawnX = config.getDouble("spawn.x");
        double spawnY = config.getDouble("spawn.y");
        double spawnZ = config.getDouble("spawn.z");

        // Read banned items list
        List<String> bannedItems = config.getStringList("bannedItems");
        getPluginLogger().info("Banned items: {}", bannedItems);

        // Read database section
        ConfigSection dbConfig = config.getSection("database");
        String dbType = dbConfig.getString("type");
        String dbFile = dbConfig.getString("file");
    }

    private void loadConfig() {
        File configFile = new File(getPluginContainer().dataFolder().toFile(), "config.yml");

        // Set up default values
        ConfigSection defaults = new ConfigSection();
        defaults.set("server.name", "Default Server");
        defaults.set("server.port", 19132);
        defaults.set("server.maxPlayers", 20);
        defaults.set("features.pvp", true);
        defaults.set("features.hunger", true);
        defaults.set("features.fall-damage", true);

        config = new Config(configFile, Config.YAML, defaults);
    }

    @Override
    public void onDisable() {
        // Save any changes before plugin is disabled
        if (config != null) {
            config.save();
        }
    }

    // Example: Update config at runtime
    public void setMaxPlayers(int max) {
        config.set("server.maxPlayers", max);
        config.save();
    }

    // Example: Add a banned item
    public void banItem(String itemId) {
        List<String> banned = config.getStringList("bannedItems");
        if (!banned.contains(itemId)) {
            banned.add(itemId);
            config.set("bannedItems", banned);
            config.save();
        }
    }
}
```

## Format-Specific Notes

### YAML Format

YAML is the recommended format for most use cases due to its readability and support for nested structures.

```yaml
# Comments are supported
key: value
nested:
  child: value
list:
  - item1
  - item2
```

### JSON Format

JSON is useful when interoperating with other systems or APIs.

```json
{
  "key": "value",
  "nested": {
    "child": "value"
  },
  "list": ["item1", "item2"]
}
```

### Properties Format

Properties format only supports flat key-value pairs. Values are stored as strings.

```properties
# Comments start with #
key=value
another.key=another value
```

Boolean values in Properties format are automatically parsed:

| Value                | Parsed As |
|----------------------|-----------|
| `on`, `true`, `yes`  | `true`    |
| `off`, `false`, `no` | `false`   |

### Enum Format

Enum format is a simple list of values, one per line. Each line becomes a key with value `true`.

```text
value1
value2
value3
```

```java linenums="1"
Config enumConfig = new Config(file, Config.ENUM);
boolean hasValue1 = enumConfig.exists("value1"); // true
```

## Best Practices

1. **Always provide defaults**: Use `ConfigSection` with default values to ensure your plugin works
   even if the config file is missing or incomplete.

2. **Save on important changes**: Save the config after making important changes to prevent data loss.

3. **Use YAML for complex configs**: YAML supports comments and nested structures, making it easier
   for users to understand and modify.

4. **Validate config values**: Always validate user-provided config values before using them.

5. **Use dot notation wisely**: Dot notation makes nested access easy, but remember that very deep
   nesting can make configs hard to maintain.

6. **Document your config**: Add comments to your default config file to help users understand
   each option.
