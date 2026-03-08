package org.allaymc.api.utils.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * A flexible configuration file handler supporting multiple formats.
 * <p>
 * Supported formats:
 * <ul>
 *   <li>YAML (.yml, .yaml)</li>
 *   <li>JSON (.json, .js)</li>
 *   <li>Properties (.properties, .cnf)</li>
 *   <li>Enum/List (.txt, .list, .enum)</li>
 * </ul>
 *
 * @author MagicDroidX (Nukkit) | daoge_cmd
 */
@Slf4j
public class Config {

    // Type constants (preserved for backward compatibility)
    /**
     * Auto-detect format by file extension.
     */
    public static final int DETECT = -1;
    /**
     * Properties format (.properties).
     */
    public static final int PROPERTIES = 0;
    /**
     * Alias for PROPERTIES.
     */
    public static final int CNF = PROPERTIES;
    /**
     * JSON format (.json, .js).
     */
    public static final int JSON = 1;
    /**
     * YAML format (.yml, .yaml).
     */
    public static final int YAML = 2;
    /**
     * Enum/List format (.txt, .list, .enum).
     */
    public static final int ENUM = 5;
    /**
     * Alias for ENUM.
     */
    public static final int ENUMERATION = ENUM;

    /**
     * Maps file extensions to format types.
     */
    public static final Map<String, Integer> format = new TreeMap<>();

    private static final Pattern PROPERTIES_PATTERN = Pattern.compile("[a-zA-Z0-9\\-_.]*=.+");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    static {
        format.put("properties", PROPERTIES);
        format.put("cnf", PROPERTIES);
        format.put("json", JSON);
        format.put("js", JSON);
        format.put("yml", YAML);
        format.put("yaml", YAML);
        format.put("txt", ENUM);
        format.put("list", ENUM);
        format.put("enum", ENUM);
    }

    private ConfigSection config = new ConfigSection();
    private File file;
    @Getter
    private boolean correct;
    private int type = DETECT;

    /**
     * Creates a new Config instance with YAML format.
     */
    public Config() {
        this(YAML);
    }

    /**
     * Creates a new Config instance with the specified format type.
     *
     * @param type the config format type (YAML, JSON, PROPERTIES, or ENUM)
     */
    public Config(int type) {
        this.type = type;
        this.correct = true;
        this.config = new ConfigSection();
    }

    /**
     * Creates a new Config instance from a file path with auto-detection.
     *
     * @param file the file path
     */
    public Config(String file) {
        this(file, DETECT);
    }

    /**
     * Creates a new Config instance from a file with auto-detection.
     *
     * @param file the file
     */
    public Config(File file) {
        this(file.toString(), DETECT);
    }

    /**
     * Creates a new Config instance from a file path with specified type.
     *
     * @param file the file path
     * @param type the config format type
     */
    public Config(String file, int type) {
        this(file, type, new ConfigSection());
    }

    /**
     * Creates a new Config instance from a file with specified type.
     *
     * @param file the file
     * @param type the config format type
     */
    public Config(File file, int type) {
        this(file.toString(), type, new ConfigSection());
    }

    /**
     * Creates a new Config instance with default values.
     *
     * @param file       the file path
     * @param type       the config format type
     * @param defaultMap default values to use if file doesn't exist
     */
    public Config(String file, int type, ConfigSection defaultMap) {
        this.load(file, type, defaultMap);
    }

    /**
     * Creates a new Config instance with default values.
     *
     * @param file       the file
     * @param type       the config format type
     * @param defaultMap default values to use if file doesn't exist
     */
    public Config(File file, int type, ConfigSection defaultMap) {
        this.load(file.toString(), type, defaultMap);
    }

    /**
     * Reloads the config from the file.
     *
     * @throws IllegalStateException if no file is associated with this config
     */
    public void reload() {
        this.config.clear();
        this.correct = false;
        if (this.file == null) {
            throw new IllegalStateException("Failed to reload Config. File object is undefined.");
        }
        this.load(this.file.toString(), this.type);
    }

    /**
     * Loads the config from a file with auto-detection.
     *
     * @param file the file path
     * @return true if loaded successfully
     */
    public boolean load(String file) {
        return this.load(file, DETECT);
    }

    /**
     * Loads the config from a file with specified type.
     *
     * @param file the file path
     * @param type the config format type
     * @return true if loaded successfully
     */
    public boolean load(String file, int type) {
        return this.load(file, type, new ConfigSection());
    }

    /**
     * Loads the config from a file with specified type and default values.
     *
     * @param file       the file path
     * @param type       the config format type
     * @param defaultMap default values to apply
     * @return true if loaded successfully
     */
    public boolean load(String file, int type, ConfigSection defaultMap) {
        this.correct = true;
        this.type = type;
        this.file = new File(file);

        if (!this.file.exists()) {
            return createNewFile(defaultMap);
        }

        return loadExistingFile(defaultMap);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private boolean createNewFile(ConfigSection defaultMap) {
        try {
            File parent = this.file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            this.file.createNewFile();
        } catch (IOException e) {
            log.error("Could not create Config {}", this.file, e);
        }
        this.config = defaultMap;
        this.save();
        return true;
    }

    private boolean loadExistingFile(ConfigSection defaultMap) {
        if (this.type == DETECT) {
            this.type = detectType();
            if (this.type == DETECT) {
                this.correct = false;
                return false;
            }
        }

        String content = "";
        try {
            content = Files.readString(this.file.toPath());
        } catch (IOException e) {
            log.error("An error occurred while loading the file {}", this.file, e);
        }

        this.parseContent(content);
        if (!this.correct) {
            return false;
        }

        if (this.setDefault(defaultMap) > 0) {
            this.save();
        }
        return true;
    }

    private int detectType() {
        String fileName = this.file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex != -1 && dotIndex != 0) {
            String extension = fileName.substring(dotIndex + 1).toLowerCase(Locale.ROOT);
            return format.getOrDefault(extension, DETECT);
        }
        return DETECT;
    }

    /**
     * Loads the config from an input stream.
     *
     * @param inputStream the input stream
     * @return true if loaded successfully
     */
    public boolean load(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        if (this.correct) {
            try {
                String content = Utils.readString(inputStream);
                this.parseContent(content);
            } catch (IOException e) {
                log.error("An error occurred while loading a config from an input stream", e);
                return false;
            }
        }
        return correct;
    }

    /**
     * Loads the config from an input stream as JSON with a custom Gson instance.
     *
     * @param inputStream the input stream
     * @param gson        the Gson instance to use
     * @return true if loaded successfully
     */
    public boolean loadAsJson(InputStream inputStream, Gson gson) {
        if (inputStream == null) {
            return false;
        }
        if (this.correct) {
            try {
                String content = Utils.readString(inputStream);
                this.parseContentAsJson(content, gson);
            } catch (IOException e) {
                log.error("An error occurred while loading a config from an input stream", e);
                return false;
            }
        }
        return correct;
    }

    /**
     * Saves the config to a file.
     *
     * @param file  the file to save to
     * @param async whether to save asynchronously
     * @return true if save was initiated successfully
     */
    public boolean save(File file, boolean async) {
        this.file = file;
        return save(async);
    }

    /**
     * Saves the config to a file synchronously.
     *
     * @param file the file to save to
     * @return true if saved successfully
     */
    public boolean save(File file) {
        this.file = file;
        return save();
    }

    /**
     * Saves the config as JSON with a custom Gson instance.
     *
     * @param file  the file to save to
     * @param async whether to save asynchronously
     * @param gson  the Gson instance to use
     * @return true if save was initiated successfully
     */
    public boolean saveAsJson(@NotNull File file, boolean async, @NotNull Gson gson) {
        this.file = file;
        return saveAsJson(async, gson);
    }

    /**
     * Saves the config synchronously.
     *
     * @return true if saved successfully
     */
    public boolean save() {
        return this.save(false);
    }

    /**
     * Saves the config as JSON with a custom Gson instance.
     *
     * @param async whether to save asynchronously
     * @param gson  the Gson instance to use
     * @return true if save was initiated successfully
     */
    public boolean saveAsJson(boolean async, @NotNull Gson gson) {
        if (!this.correct) {
            return false;
        }
        writeToFile(async, gson.toJson(this.config) + "\n");
        return true;
    }

    /**
     * Saves the config.
     *
     * @param async whether to save asynchronously
     * @return true if save was initiated successfully
     */
    public boolean save(Boolean async) {
        if (this.file == null) {
            throw new IllegalStateException("Failed to save Config. File object is undefined.");
        }
        if (!this.correct) {
            return false;
        }

        String content = switch (this.type) {
            case PROPERTIES -> writeProperties();
            case JSON -> GSON.toJson(this.config);
            case YAML -> createYaml().dump(this.config);
            case ENUM -> writeEnum();
            default -> "";
        };

        writeToFile(async, content);
        return true;
    }

    private void writeToFile(boolean async, String content) {
        if (async) {
            Server.getInstance().getVirtualThreadPool().submit(() -> writeContentToFile(content));
        } else {
            writeContentToFile(content);
        }
    }

    private void writeContentToFile(String content) {
        try {
            Files.writeString(this.file.toPath(), content);
        } catch (IOException e) {
            log.error("Failed to save the config file {}", file, e);
            throw new ConfigException(e);
        }
    }

    // ==================== Getter/Setter Methods ====================

    /**
     * Sets a value for the specified key.
     *
     * @param key   the key (supports dot notation for nested keys)
     * @param value the value to set
     */
    public void set(final String key, Object value) {
        this.config.set(key, value);
    }

    /**
     * Gets a value by key.
     *
     * @param key the key
     * @return the value, or {@code null}  if not found
     */
    public Object get(String key) {
        return this.get(key, null);
    }

    /**
     * Gets a value by key with a default value.
     *
     * @param key          the key
     * @param defaultValue the default value if key is not found
     * @param <T>          the value type
     * @return the value, or defaultValue if not found or config is incorrect
     */
    public <T> T get(String key, T defaultValue) {
        return this.correct ? this.config.get(key, defaultValue) : defaultValue;
    }

    /**
     * Gets a section by key.
     *
     * @param key the key
     * @return the config section, or empty section if not found
     */
    @NotNull
    public ConfigSection getSection(String key) {
        return this.correct ? this.config.getSection(key) : new ConfigSection();
    }

    /**
     * Checks if the key points to a section.
     *
     * @param key the key
     * @return true if the value is a ConfigSection
     */
    public boolean isSection(String key) {
        return config.isSection(key);
    }

    /**
     * Gets all sections under the specified key.
     *
     * @param key the key
     * @return a ConfigSection containing only section entries
     */
    @NotNull
    public ConfigSection getSections(String key) {
        return this.correct ? this.config.getSections(key) : new ConfigSection();
    }

    /**
     * Gets all sections at the root level.
     *
     * @return a ConfigSection containing only section entries
     */
    @NotNull
    public ConfigSection getSections() {
        return this.correct ? this.config.getSections() : new ConfigSection();
    }

    /**
     * Gets an integer value.
     *
     * @param key the key
     * @return the integer value, or 0 if not found
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    /**
     * Gets an integer value with a default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the integer value, or defaultValue if not found
     */
    public int getInt(String key, int defaultValue) {
        return this.correct ? this.config.getInt(key, defaultValue) : defaultValue;
    }

    /**
     * Checks if the value is an integer.
     *
     * @param key the key
     * @return true if the value is an Integer
     */
    public boolean isInt(String key) {
        return config.isInt(key);
    }

    /**
     * Gets a long value.
     *
     * @param key the key
     * @return the long value, or 0 if not found
     */
    public long getLong(String key) {
        return this.getLong(key, 0);
    }

    /**
     * Gets a long value with a default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the long value, or defaultValue if not found
     */
    public long getLong(String key, long defaultValue) {
        return this.correct ? this.config.getLong(key, defaultValue) : defaultValue;
    }

    /**
     * Checks if the value is a long.
     *
     * @param key the key
     * @return true if the value is a Long
     */
    public boolean isLong(String key) {
        return config.isLong(key);
    }

    /**
     * Gets a double value.
     *
     * @param key the key
     * @return the double value, or 0 if not found
     */
    public double getDouble(String key) {
        return this.getDouble(key, 0);
    }

    /**
     * Gets a double value with a default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the double value, or defaultValue if not found
     */
    public double getDouble(String key, double defaultValue) {
        return this.correct ? this.config.getDouble(key, defaultValue) : defaultValue;
    }

    /**
     * Checks if the value is a double.
     *
     * @param key the key
     * @return true if the value is a Double
     */
    public boolean isDouble(String key) {
        return config.isDouble(key);
    }

    /**
     * Gets a string value.
     *
     * @param key the key
     * @return the string value, or empty string if not found
     */
    @NotNull
    public String getString(String key) {
        return this.getString(key, "");
    }

    /**
     * Gets a string value with a default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the string value, or defaultValue if not found
     */
    public String getString(String key, String defaultValue) {
        return this.correct ? this.config.getString(key, defaultValue) : defaultValue;
    }

    /**
     * Checks if the value is a string.
     *
     * @param key the key
     * @return true if the value is a String
     */
    public boolean isString(String key) {
        return config.isString(key);
    }

    /**
     * Gets a boolean value.
     *
     * @param key the key
     * @return the boolean value, or false if not found
     */
    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    /**
     * Gets a boolean value with a default.
     *
     * @param key          the key
     * @param defaultValue the default value
     * @return the boolean value, or defaultValue if not found
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.correct ? this.config.getBoolean(key, defaultValue) : defaultValue;
    }

    /**
     * Checks if the value is a boolean.
     *
     * @param key the key
     * @return true if the value is a Boolean
     */
    public boolean isBoolean(String key) {
        return config.isBoolean(key);
    }

    /**
     * Gets a list value.
     *
     * @param key the key
     * @return the list, or {@code null} if not found
     */
    public List<?> getList(String key) {
        return this.getList(key, null);
    }

    /**
     * Gets a list value with a default.
     *
     * @param key         the key
     * @param defaultList the default list
     * @return the list, or defaultList if not found
     */
    public List<?> getList(String key, List<?> defaultList) {
        return this.correct ? this.config.getList(key, defaultList) : defaultList;
    }

    /**
     * Checks if the value is a list.
     *
     * @param key the key
     * @return true if the value is a List
     */
    public boolean isList(String key) {
        return config.isList(key);
    }

    /**
     * Gets a list of strings.
     *
     * @param key the key
     * @return the string list
     */
    @NotNull
    public List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    /**
     * Gets a list of integers.
     *
     * @param key the key
     * @return the integer list
     */
    @NotNull
    public List<Integer> getIntegerList(String key) {
        return config.getIntegerList(key);
    }

    /**
     * Gets a list of booleans.
     *
     * @param key the key
     * @return the boolean list
     */
    @NotNull
    public List<Boolean> getBooleanList(String key) {
        return config.getBooleanList(key);
    }

    /**
     * Gets a list of doubles.
     *
     * @param key the key
     * @return the double list
     */
    @NotNull
    public List<Double> getDoubleList(String key) {
        return config.getDoubleList(key);
    }

    /**
     * Gets a list of floats.
     *
     * @param key the key
     * @return the float list
     */
    @NotNull
    public List<Float> getFloatList(String key) {
        return config.getFloatList(key);
    }

    /**
     * Gets a list of longs.
     *
     * @param key the key
     * @return the long list
     */
    @NotNull
    public List<Long> getLongList(String key) {
        return config.getLongList(key);
    }

    /**
     * Gets a list of bytes.
     *
     * @param key the key
     * @return the byte list
     */
    @NotNull
    public List<Byte> getByteList(String key) {
        return config.getByteList(key);
    }

    /**
     * Gets a list of characters.
     *
     * @param key the key
     * @return the character list
     */
    @NotNull
    public List<Character> getCharacterList(String key) {
        return config.getCharacterList(key);
    }

    /**
     * Gets a list of shorts.
     *
     * @param key the key
     * @return the short list
     */
    @NotNull
    public List<Short> getShortList(String key) {
        return config.getShortList(key);
    }

    /**
     * Gets a list of maps.
     *
     * @param key the key
     * @return the map list
     */
    @NotNull
    public List<Map<?, ?>> getMapList(String key) {
        return config.getMapList(key);
    }

    // ==================== Utility Methods ====================

    /**
     * Checks if a key exists.
     *
     * @param key the key
     * @return true if the key exists
     */
    public boolean exists(String key) {
        return config.exists(key);
    }

    /**
     * Checks if a key exists with optional case-insensitive matching.
     *
     * @param key        the key
     * @param ignoreCase whether to ignore case
     * @return true if the key exists
     */
    public boolean exists(String key, boolean ignoreCase) {
        return config.exists(key, ignoreCase);
    }

    /**
     * Removes a key from the config.
     *
     * @param key the key to remove
     */
    public void remove(String key) {
        config.remove(key);
    }

    /**
     * Gets all config entries as a map.
     *
     * @return a map of all entries
     */
    @NotNull
    public Map<String, Object> getAll() {
        return this.config.getAllMap();
    }

    /**
     * Replaces all config entries with the given map.
     *
     * @param map the new entries
     */
    public void setAll(LinkedHashMap<String, Object> map) {
        this.config = new ConfigSection(map);
    }

    /**
     * Replaces all config entries with the given section.
     *
     * @param section the new section
     */
    public void setAll(ConfigSection section) {
        this.config = section;
    }

    /**
     * Gets the root config section.
     *
     * @return the root section
     */
    @NotNull
    public ConfigSection getRootSection() {
        return config;
    }

    /**
     * Sets default values from a map.
     *
     * @param map the default values
     * @return the number of values added
     */
    public int setDefault(LinkedHashMap<String, Object> map) {
        return setDefault(new ConfigSection(map));
    }

    /**
     * Sets default values from a config section.
     *
     * @param map the default values
     * @return the number of values added
     */
    public int setDefault(ConfigSection map) {
        int size = this.config.size();
        this.fillDefaults(map, this.config);
        return this.config.size() - size;
    }

    private void fillDefaults(ConfigSection defaultMap, ConfigSection data) {
        for (String key : defaultMap.keySet()) {
            if (!data.containsKey(key)) {
                data.put(key, defaultMap.get(key));
            }
        }
    }

    /**
     * Gets all keys.
     *
     * @return a set of all keys (including nested keys)
     */
    @NotNull
    public Set<String> getKeys() {
        return this.correct ? config.getKeys() : new HashSet<>();
    }

    /**
     * Gets keys with optional child key inclusion.
     *
     * @param child whether to include nested keys
     * @return a set of keys
     */
    @NotNull
    public Set<String> getKeys(boolean child) {
        return this.correct ? config.getKeys(child) : new HashSet<>();
    }

    // ==================== Parsing Methods ====================

    private void parseContent(String content) {
        try {
            switch (this.type) {
                case PROPERTIES -> parseProperties(content);
                case JSON -> this.config = new ConfigSection(
                        GSON.fromJson(content, new TypeToken<LinkedHashMap<String, Object>>() {
                        }.getType())
                );
                case YAML -> this.config = new ConfigSection(
                        createYaml().loadAs(content, LinkedHashMap.class)
                );
                case ENUM -> parseList(content);
                default -> this.correct = false;
            }
        } catch (RuntimeException e) {
            log.warn("Failed to parse the config file {}", file, e);
            throw new ConfigException(e);
        }
    }

    private void parseContentAsJson(String content, Gson gson) {
        try {
            this.config = new ConfigSection(
                    gson.fromJson(content, new TypeToken<LinkedHashMap<String, Object>>() {
                    }.getType())
            );
        } catch (RuntimeException e) {
            log.warn("Failed to parse the config file {}", file, e);
            throw new ConfigException(e);
        }
    }

    private void parseList(String content) {
        // Normalize line endings
        content = content.replace("\r\n", "\n").replace("\r", "\n");
        for (String v : content.split("\n")) {
            if (!v.trim().isEmpty()) {
                config.put(v, true);
            }
        }
    }

    private void parseProperties(String content) {
        // Normalize line endings to handle both Unix and Windows formats
        content = content.replace("\r\n", "\n").replace("\r", "\n");

        for (String line : content.split("\n")) {
            line = line.trim();

            // Skip empty lines and comments
            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            if (!PROPERTIES_PATTERN.matcher(line).matches()) {
                continue;
            }

            int splitIndex = line.indexOf('=');
            if (splitIndex == -1) {
                continue;
            }

            String key = line.substring(0, splitIndex);
            String value = line.substring(splitIndex + 1);
            String valueLower = value.toLowerCase(Locale.ROOT);

            if (this.config.containsKey(key)) {
                log.debug("[Config] Repeated property {} on file {}", key, this.file);
            }

            Object parsedValue = switch (valueLower) {
                case "on", "true", "yes" -> true;
                case "off", "false", "no" -> false;
                default -> value;
            };

            this.config.put(key, parsedValue);
        }
    }

    // ==================== Writing Methods ====================

    private String writeProperties() {
        StringBuilder content = new StringBuilder();
        content.append("#Properties Config file\r\n");
        content.append("#").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\r\n");

        for (Map.Entry<String, Object> entry : this.config.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Boolean) {
                value = (Boolean) value ? "on" : "off";
            }
            content.append(entry.getKey()).append("=").append(value).append("\r\n");
        }

        return content.toString();
    }

    private String writeEnum() {
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Object> entry : this.config.entrySet()) {
            content.append(entry.getKey()).append("\r\n");
        }
        return content.toString();
    }

    private static Yaml createYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(options);
    }
}
