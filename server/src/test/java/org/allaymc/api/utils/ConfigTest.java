package org.allaymc.api.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.allaymc.api.utils.config.Config;
import org.allaymc.api.utils.config.ConfigException;
import org.allaymc.api.utils.config.ConfigSection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
class ConfigTest {

    @TempDir
    Path tempDir;

    private File yamlFile;
    private File jsonFile;
    private File propertiesFile;
    private File enumFile;

    @BeforeEach
    void setUp() {
        yamlFile = tempDir.resolve("test.yml").toFile();
        jsonFile = tempDir.resolve("test.json").toFile();
        propertiesFile = tempDir.resolve("test.properties").toFile();
        enumFile = tempDir.resolve("test.txt").toFile();
    }

    // ==================== Helper Methods ====================

    private Config createYamlConfig() {
        return new Config(Config.YAML);
    }

    private InputStream toInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    // ==================== Constructor Tests ====================

    @Test
    void testDefaultConstructor() {
        Config config = new Config();
        assertTrue(config.isCorrect());
        assertNotNull(config.getRootSection());
        assertTrue(config.getAll().isEmpty());
    }

    @ParameterizedTest
    @ValueSource(ints = {Config.YAML, Config.JSON, Config.PROPERTIES, Config.ENUM})
    void testConstructorWithType(int type) {
        Config config = new Config(type);
        assertTrue(config.isCorrect());
    }

    @Test
    void testConstructorWithFilePath() {
        Config config = new Config(yamlFile.toString());
        assertTrue(config.isCorrect());
        assertTrue(yamlFile.exists());
    }

    @Test
    void testConstructorWithFile() {
        Config config = new Config(jsonFile);
        assertTrue(config.isCorrect());
        assertTrue(jsonFile.exists());
    }

    @Test
    void testConstructorWithDefaultMap() {
        ConfigSection defaultMap = new ConfigSection();
        defaultMap.put("key1", "value1");
        defaultMap.put("key2", 123);

        Config config = new Config(yamlFile.toString(), Config.YAML, defaultMap);
        assertTrue(config.isCorrect());
        assertEquals("value1", config.getString("key1"));
        assertEquals(123, config.getInt("key2"));
    }

    // ==================== Type Detection Tests ====================

    @ParameterizedTest
    @CsvSource({"test.yml, true", "test.json, true", "test.unknown, false"})
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void testDetectFormat(String fileName, boolean expectedCorrect) throws IOException {
        File file = tempDir.resolve(fileName).toFile();
        if (fileName.endsWith(".json")) {
            Files.writeString(file.toPath(), "{}");
        } else {
            file.createNewFile();
        }
        Config config = new Config(file.toString(), Config.DETECT);
        assertEquals(expectedCorrect, config.isCorrect());
    }

    @Test
    void testDetectPropertiesFormat() {
        Config config = new Config(propertiesFile.toString(), Config.DETECT);
        assertTrue(config.isCorrect());
    }

    @Test
    void testDetectEnumFormat() {
        Config config = new Config(enumFile.toString(), Config.DETECT);
        assertTrue(config.isCorrect());
    }

    // ==================== YAML Format Tests ====================

    @Test
    void testYamlLoadAndSave() throws IOException {
        String yamlContent = """
                name: TestConfig
                version: 1
                enabled: true
                ratio: 3.14
                nested:
                  key1: value1
                list:
                  - item1
                  - item2
                """;
        Files.writeString(yamlFile.toPath(), yamlContent);

        Config config = new Config(yamlFile.toString(), Config.YAML);
        assertTrue(config.isCorrect());
        assertEquals("TestConfig", config.getString("name"));
        assertEquals(1, config.getInt("version"));
        assertTrue(config.getBoolean("enabled"));
        assertEquals(3.14, config.getDouble("ratio"), 0.001);
        assertEquals("value1", config.getString("nested.key1"));

        List<?> list = config.getList("list");
        assertNotNull(list);
        assertEquals(2, list.size());
    }

    @Test
    void testYamlSaveAndReload() {
        Config config = new Config(yamlFile.toString(), Config.YAML);
        config.set("name", "SaveTest");
        config.set("count", 100);
        config.save();

        Config loaded = new Config(yamlFile.toString(), Config.YAML);
        assertEquals("SaveTest", loaded.getString("name"));
        assertEquals(100, loaded.getInt("count"));
    }

    // ==================== JSON Format Tests ====================

    @Test
    void testJsonLoadAndSave() throws IOException {
        String jsonContent = """
                {"name": "JsonTest", "version": 2, "nested": {"innerKey": "innerValue"}}
                """;
        Files.writeString(jsonFile.toPath(), jsonContent);

        Config config = new Config(jsonFile.toString(), Config.JSON);
        assertTrue(config.isCorrect());
        assertEquals("JsonTest", config.getString("name"));
        assertEquals(2, config.getInt("version"));
        assertEquals("innerValue", config.getString("nested.innerKey"));
    }

    @Test
    void testJsonSaveAndReload() {
        Config config = new Config(jsonFile.toString(), Config.JSON);
        config.set("title", "JsonSaveTest");
        config.set("priority", 5);
        config.save();

        Config loaded = new Config(jsonFile.toString(), Config.JSON);
        assertEquals("JsonSaveTest", loaded.getString("title"));
        assertEquals(5, loaded.getInt("priority"));
    }

    // ==================== Properties Format Tests ====================

    @Test
    void testPropertiesLoadBooleanVariants() throws IOException {
        String content = "enabled=on\ndisabled=off\nflag=true\nnoflag=false\nyesFlag=yes\nnoFlag=no\n";
        Files.writeString(propertiesFile.toPath(), content);

        Config config = new Config(propertiesFile.toString(), Config.PROPERTIES);
        assertTrue(config.getBoolean("enabled"));
        assertFalse(config.getBoolean("disabled"));
        assertTrue(config.getBoolean("flag"));
        assertFalse(config.getBoolean("noflag"));
        assertTrue(config.getBoolean("yesFlag"));
        assertFalse(config.getBoolean("noFlag"));
    }

    @Test
    void testPropertiesSave() throws IOException {
        Config config = new Config(propertiesFile.toString(), Config.PROPERTIES);
        config.set("key", "value");
        config.set("boolTrue", true);
        config.set("boolFalse", false);
        config.save();

        String content = Files.readString(propertiesFile.toPath());
        assertTrue(content.contains("key=value"));
        assertTrue(content.contains("boolTrue=on"));
        assertTrue(content.contains("boolFalse=off"));
    }

    // ==================== Enum Format Tests ====================

    @Test
    void testEnumLoadAndSave() throws IOException {
        Files.writeString(enumFile.toPath(), "item1\nitem2\nitem3\n");

        Config config = new Config(enumFile.toString(), Config.ENUM);
        assertTrue(config.exists("item1"));
        assertTrue(config.exists("item2"));
        assertTrue(config.exists("item3"));

        config.set("newItem", true);
        config.save();

        Config loaded = new Config(enumFile.toString(), Config.ENUM);
        assertTrue(loaded.exists("newItem"));
    }

    // ==================== InputStream Tests ====================

    @Test
    void testLoadFromInputStream() {
        Config config = createYamlConfig();
        assertTrue(config.load(toInputStream("key: value\ncount: 10")));
        assertEquals("value", config.getString("key"));
        assertEquals(10, config.getInt("count"));
    }

    @Test
    void testLoadFromNullInputStream() {
        assertFalse(createYamlConfig().load((InputStream) null));
    }

    @Test
    void testLoadAsJsonFromInputStream() {
        Config config = new Config(Config.JSON);
        Gson gson = new GsonBuilder().create();
        assertTrue(config.loadAsJson(toInputStream("{\"name\": \"test\", \"value\": 123}"), gson));
        assertEquals("test", config.getString("name"));
        assertEquals(123, config.getInt("value"));
    }

    // ==================== Getter Tests ====================

    @Test
    void testGetWithDefault() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertEquals("value", config.get("key", "default"));
        assertEquals("default", config.get("nonexistent", "default"));
    }

    @Test
    void testGetInt() {
        Config config = createYamlConfig();
        config.set("count", 42);
        assertEquals(42, config.getInt("count"));
        assertEquals(0, config.getInt("nonexistent"));
        assertEquals(100, config.getInt("nonexistent", 100));
    }

    @Test
    void testGetLong() {
        Config config = createYamlConfig();
        config.set("bigNumber", 9999999999L);
        assertEquals(9999999999L, config.getLong("bigNumber"));
        assertEquals(0L, config.getLong("nonexistent"));
        assertEquals(100L, config.getLong("nonexistent", 100L));
    }

    @Test
    void testGetDouble() {
        Config config = createYamlConfig();
        config.set("ratio", 3.14159);
        assertEquals(3.14159, config.getDouble("ratio"), 0.00001);
        assertEquals(0.0, config.getDouble("nonexistent"), 0.00001);
        assertEquals(1.5, config.getDouble("nonexistent", 1.5), 0.00001);
    }

    @Test
    void testGetString() {
        Config config = createYamlConfig();
        config.set("name", "TestName");
        assertEquals("TestName", config.getString("name"));
        assertEquals("", config.getString("nonexistent"));
        assertEquals("default", config.getString("nonexistent", "default"));
    }

    @Test
    void testGetBoolean() {
        Config config = createYamlConfig();
        config.set("enabled", true);
        config.set("disabled", false);
        assertTrue(config.getBoolean("enabled"));
        assertFalse(config.getBoolean("disabled"));
        assertFalse(config.getBoolean("nonexistent"));
        assertTrue(config.getBoolean("nonexistent", true));
    }

    @Test
    void testTypeChecks() {
        Config config = createYamlConfig();
        config.set("intVal", 42);
        config.set("longVal", 9999999999L);
        config.set("doubleVal", 3.14);
        config.set("stringVal", "hello");
        config.set("boolVal", true);
        config.set("listVal", Arrays.asList(1, 2, 3));

        assertTrue(config.isInt("intVal"));
        assertTrue(config.isLong("longVal"));
        assertTrue(config.isDouble("doubleVal"));
        assertTrue(config.isString("stringVal"));
        assertTrue(config.isBoolean("boolVal"));
        assertTrue(config.isList("listVal"));

        assertFalse(config.isInt("stringVal"));
        assertFalse(config.isString("intVal"));
    }

    // ==================== Section Tests ====================

    @Test
    void testGetSection() {
        Config config = createYamlConfig();
        config.set("section.innerKey", "innerValue");

        ConfigSection section = config.getSection("section");
        assertNotNull(section);
        assertEquals("innerValue", section.get("innerKey"));

        assertTrue(config.getSection("nonexistent").isEmpty());
    }

    @Test
    void testIsSection() {
        Config config = createYamlConfig();
        config.set("section.key", "value");
        config.set("notSection", "string");

        assertTrue(config.isSection("section"));
        assertFalse(config.isSection("notSection"));
    }

    @Test
    void testGetSections() {
        Config config = createYamlConfig();
        config.set("section1.key", "value1");
        config.set("section2.key", "value2");
        config.set("notSection", "string");

        ConfigSection sections = config.getSections();
        assertEquals(2, sections.size());
        assertTrue(sections.containsKey("section1"));
        assertTrue(sections.containsKey("section2"));
    }

    // ==================== List Tests ====================

    @Test
    void testGetList() {
        Config config = createYamlConfig();
        config.set("list", Arrays.asList("a", "b", "c"));

        List<?> list = config.getList("list");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertNull(config.getList("nonexistent"));
        assertEquals(List.of("default"), config.getList("nonexistent", List.of("default")));
    }

    @Test
    void testGetTypedLists() {
        Config config = createYamlConfig();
        config.set("strings", Arrays.asList("a", "b", "c"));
        config.set("integers", Arrays.asList(1, 2, 3));
        config.set("booleans", Arrays.asList(true, false, true));
        config.set("doubles", Arrays.asList(1.1, 2.2, 3.3));
        config.set("floats", Arrays.asList(1.1f, 2.2f));
        config.set("longs", Arrays.asList(1L, 2L));
        config.set("bytes", Arrays.asList((byte) 1, (byte) 2));
        config.set("chars", Arrays.asList('a', 'b'));
        config.set("shorts", Arrays.asList((short) 1, (short) 2));

        assertEquals(3, config.getStringList("strings").size());
        assertEquals(3, config.getIntegerList("integers").size());
        assertEquals(3, config.getBooleanList("booleans").size());
        assertEquals(3, config.getDoubleList("doubles").size());
        assertEquals(2, config.getFloatList("floats").size());
        assertEquals(2, config.getLongList("longs").size());
        assertEquals(2, config.getByteList("bytes").size());
        assertEquals(2, config.getCharacterList("chars").size());
        assertEquals(2, config.getShortList("shorts").size());

        assertEquals("a", config.getStringList("strings").getFirst());
        assertEquals(1, config.getIntegerList("integers").getFirst());
        assertTrue(config.getBooleanList("booleans").getFirst());
    }

    @Test
    void testGetMapList() {
        Config config = createYamlConfig();
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key1", "value1");
        config.set("maps", List.of(map1));

        assertEquals(1, config.getMapList("maps").size());
    }

    // ==================== Exists and Remove Tests ====================

    @Test
    void testExistsAndRemove() {
        Config config = createYamlConfig();
        config.set("key", "value");
        config.set("MyKey", "value");

        assertTrue(config.exists("key"));
        assertFalse(config.exists("nonexistent"));
        assertTrue(config.exists("mykey", true));
        assertFalse(config.exists("mykey", false));

        config.remove("key");
        assertFalse(config.exists("key"));
    }

    // ==================== GetAll/SetAll Tests ====================

    @Test
    void testGetAllAndSetAll() {
        Config config = createYamlConfig();
        config.set("key1", "value1");
        config.set("key2", "value2");

        Map<String, Object> all = config.getAll();
        assertEquals(2, all.size());

        LinkedHashMap<String, Object> newMap = new LinkedHashMap<>();
        newMap.put("newKey", "newValue");
        config.setAll(newMap);
        assertEquals("newValue", config.getString("newKey"));
        assertFalse(config.exists("key1"));
    }

    @Test
    void testGetRootSection() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertNotNull(config.getRootSection());
        assertEquals("value", config.getRootSection().get("key"));
    }

    // ==================== Keys Tests ====================

    @Test
    void testGetKeys() {
        Config config = createYamlConfig();
        config.set("key1", "value1");
        config.set("nested.inner", "value");

        Set<String> keysWithChild = config.getKeys(true);
        assertTrue(keysWithChild.contains("nested.inner"));

        Set<String> keysWithoutChild = config.getKeys(false);
        assertFalse(keysWithoutChild.contains("nested.inner"));
        assertTrue(keysWithoutChild.contains("nested"));
    }

    // ==================== SetDefault Tests ====================

    @Test
    void testSetDefault() {
        Config config = createYamlConfig();
        config.set("existing", "value");

        ConfigSection defaults = new ConfigSection();
        defaults.put("existing", "ignored");
        defaults.put("added", "addedValue");

        int added = config.setDefault(defaults);
        assertEquals(1, added);
        assertEquals("value", config.getString("existing"));
        assertEquals("addedValue", config.getString("added"));
    }

    // ==================== Reload Tests ====================

    @Test
    void testReload() throws IOException {
        Config config = new Config(yamlFile.toString(), Config.YAML);
        config.set("key", "original");
        config.save();

        Files.writeString(yamlFile.toPath(), "key: newValue\n");
        config.reload();
        assertEquals("newValue", config.getString("key"));
    }

    @Test
    void testReloadWithoutFile() {
        assertThrows(IllegalStateException.class, () -> createYamlConfig().reload());
    }

    // ==================== Save Tests ====================

    @Test
    void testSaveToFile() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertTrue(config.save(yamlFile));
        assertTrue(yamlFile.exists());
        assertEquals("value", new Config(yamlFile, Config.YAML).getString("key"));
    }

    @Test
    void testSaveWithoutFile() {
        assertThrows(IllegalStateException.class, () -> createYamlConfig().save());
    }

    @Test
    void testSaveAsJson() {
        Config config = new Config(Config.JSON);
        config.set("key", "value");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        assertTrue(config.saveAsJson(jsonFile, false, gson));
        assertEquals("value", new Config(jsonFile, Config.JSON).getString("key"));
    }

    // ==================== Nested Key Tests ====================

    @Test
    void testNestedKeys() {
        Config config = createYamlConfig();
        config.set("level1.level2.level3", "deepValue");

        assertEquals("deepValue", config.getString("level1.level2.level3"));
        assertTrue(config.isSection("level1"));
        assertTrue(config.isSection("level1.level2"));

        ConfigSection section = config.getSection("level1.level2");
        assertEquals("deepValue", section.getString("level3"));
    }

    // ==================== Error Handling Tests ====================

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void testIncorrectConfigReturnsDefaults() throws IOException {
        File unknownFile = tempDir.resolve("test.xyz").toFile();
        unknownFile.createNewFile();
        Config config = new Config(unknownFile.toString(), Config.DETECT);
        assertFalse(config.isCorrect());

        assertEquals("default", config.get("key", "default"));
        assertEquals(0, config.getInt("key"));
        assertEquals(100, config.getInt("key", 100));
        assertEquals("", config.getString("key"));
        assertFalse(config.getBoolean("key"));
        assertTrue(config.getKeys().isEmpty());
    }

    @Test
    void testInvalidJsonThrowsException() throws IOException {
        Files.writeString(jsonFile.toPath(), "invalid json {{{");
        assertThrows(ConfigException.class, () -> new Config(jsonFile.toString(), Config.JSON));
    }

    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void testLoadEmptyFiles() throws IOException {
        yamlFile.createNewFile();
        Files.writeString(jsonFile.toPath(), "{}");

        assertTrue(new Config(yamlFile.toString(), Config.YAML).getAll().isEmpty());
        assertTrue(new Config(jsonFile.toString(), Config.JSON).getAll().isEmpty());
    }

    // ==================== Constants Tests ====================

    @Test
    void testFormatMapAndConstants() {
        assertEquals(Config.PROPERTIES, Config.format.get("properties"));
        assertEquals(Config.JSON, Config.format.get("json"));
        assertEquals(Config.YAML, Config.format.get("yml"));
        assertEquals(Config.ENUM, Config.format.get("txt"));

        assertEquals(-1, Config.DETECT);
        assertEquals(0, Config.PROPERTIES);
        assertEquals(Config.PROPERTIES, Config.CNF);
        assertEquals(1, Config.JSON);
        assertEquals(2, Config.YAML);
        assertEquals(5, Config.ENUM);
        assertEquals(Config.ENUM, Config.ENUMERATION);
    }
}
