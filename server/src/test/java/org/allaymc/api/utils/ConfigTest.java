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
 * {@code ConfigTest} verifies configuration formats, defaults, typed access, nested sections, and
 * file persistence through isolated temporary paths.
 *
 * @author daoge_cmd
 */
class ConfigTest {

    /**
     * {@code tempDir} is the JUnit-managed root for each test's configuration files. Test paths
     * remain inside this temporary directory rather than using the server's normal configuration
     * files.
     */
    @TempDir
    Path tempDir;

    // yamlFile, jsonFile, propertiesFile, and enumFile select different format paths beneath
    // tempDir. setUp assigns them before each test so tests begin with isolated filenames.
    /**
     * YAML test file path.
     */
    private File yamlFile;
    /**
     * JSON test file path.
     */
    private File jsonFile;
    /**
     * Properties test file path.
     */
    private File propertiesFile;
    /**
     * Line-list test file path.
     */
    private File enumFile;

    /**
     * Assigns per-format file paths beneath the current temporary test directory.
     */
    @BeforeEach
    void setUp() {
        yamlFile = tempDir.resolve("test.yml").toFile();
        jsonFile = tempDir.resolve("test.json").toFile();
        propertiesFile = tempDir.resolve("test.properties").toFile();
        enumFile = tempDir.resolve("test.txt").toFile();
    }

    // ==================== Helper Methods ====================

    /**
     * Creates an unbound YAML configuration for in-memory accessor test cases.
     */
    private Config createYamlConfig() {
        return new Config(Config.YAML);
    }

    /**
     * Encodes fixture text as a UTF-8 byte stream for configuration parsing.
     */
    private InputStream toInputStream(String content) {
        return new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
    }

    // ==================== Constructor Tests ====================

    /**
     * Creates the default in-memory configuration. Its correctness flag and empty root must be
     * available before a backing file has been assigned to the instance.
     */
    @Test
    void testDefaultConstructor() {
        Config config = new Config();
        assertTrue(config.isCorrect());
        assertNotNull(config.getRootSection());
        assertTrue(config.getAll().isEmpty());
    }

    /**
     * Creates an in-memory configuration for each supported format selector. Every selected format
     * must start with the correctness flag set for later parsing and saving.
     */
    @ParameterizedTest
    @ValueSource(ints = {Config.YAML, Config.JSON, Config.PROPERTIES, Config.ENUM})
    void testConstructorWithType(int type) {
        Config config = new Config(type);
        assertTrue(config.isCorrect());
    }

    /**
     * Constructs a configuration for an absent YAML-named path using detection. The instance must
     * report correctness and create the requested file during construction.
     */
    @Test
    void testConstructorWithFilePath() {
        Config config = new Config(yamlFile.toString());
        assertTrue(config.isCorrect());
        assertTrue(yamlFile.exists());
    }

    /**
     * Constructs a configuration from an absent JSON-named file object. The instance must report
     * correctness and create that requested path during the constructor call.
     */
    @Test
    void testConstructorWithFile() {
        Config config = new Config(jsonFile);
        assertTrue(config.isCorrect());
        assertTrue(jsonFile.exists());
    }

    /**
     * Creates a YAML configuration for a missing file with supplied defaults. String and integer
     * getters must expose the provided entries after construction completes.
     */
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

    /**
     * Loads existing files whose extensions are recognized or unknown. The correctness flag must
     * reflect format detection while valid empty JSON receives an object fixture.
     */
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

    /**
     * Constructs a configuration for a missing properties-named file using detection. The creation
     * path must leave the instance marked correct even before existing-file detection occurs.
     */
    @Test
    void testDetectPropertiesFormat() {
        Config config = new Config(propertiesFile.toString(), Config.DETECT);
        assertTrue(config.isCorrect());
    }

    /**
     * Constructs a configuration for a missing line-list file using detection. The creation path
     * must create an instance whose correctness flag is initially set.
     */
    @Test
    void testDetectEnumFormat() {
        Config config = new Config(enumFile.toString(), Config.DETECT);
        assertTrue(config.isCorrect());
    }

    // ==================== YAML Format Tests ====================

    /**
     * Loads YAML containing scalar values, a nested section, and a list. Typed and dotted-path
     * reads must expose those values and the list length from the fixture.
     */
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

    /**
     * Writes modified YAML values to the bound file and constructs another instance. The new
     * instance must read the saved name and integer count unchanged.
     */
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

    /**
     * Loads a JSON object containing scalar fields and a nested object. Typed numeric access and
     * dotted-path string access must expose the corresponding fixture values.
     */
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

    /**
     * Writes modified JSON values and loads the same file through another instance. The saved
     * title and numeric priority must remain accessible after that reload.
     */
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

    /**
     * Loads properties containing {@code "on"}/{@code "off"}, {@code "true"}/{@code "false"}, and {@code "yes"}/{@code "no"} spellings. Each accepted word
     * must become the corresponding boolean value rather than remain ordinary text.
     */
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

    /**
     * Saves strings and booleans using the properties writer. The file must contain the string
     * entry and the writer's {@code "on"}/{@code "off"} representation for boolean values.
     */
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

    /**
     * Loads three membership lines, adds another key, and saves the list file. A new configuration
     * must recognize the added membership after loading that file.
     */
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

    /**
     * Loads YAML from an in-memory stream into an unbound configuration. The load result must
     * succeed and make both string and numeric fixture values available.
     */
    @Test
    void testLoadFromInputStream() {
        Config config = createYamlConfig();
        assertTrue(config.load(toInputStream("key: value\ncount: 10")));
        assertEquals("value", config.getString("key"));
        assertEquals(10, config.getInt("count"));
    }

    /**
     * Passes a {@code null} stream to a valid in-memory YAML configuration. The load operation must return
     * {@code false} without claiming that any input data was parsed.
     */
    @Test
    void testLoadFromNullInputStream() {
        assertFalse(createYamlConfig().load((InputStream) null));
    }

    /**
     * Loads an in-memory JSON stream with a caller-supplied serializer. The operation must report
     * success and expose both its string and numeric fields afterward.
     */
    @Test
    void testLoadAsJsonFromInputStream() {
        Config config = new Config(Config.JSON);
        Gson gson = new GsonBuilder().create();
        assertTrue(config.loadAsJson(toInputStream("{\"name\": \"test\", \"value\": 123}"), gson));
        assertEquals("test", config.getString("name"));
        assertEquals(123, config.getInt("value"));
    }

    // ==================== Getter Tests ====================

    /**
     * Reads one present key and one missing key using an explicit fallback. The existing value
     * must win while the missing key returns the supplied default.
     */
    @Test
    void testGetWithDefault() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertEquals("value", config.get("key", "default"));
        assertEquals("default", config.get("nonexistent", "default"));
    }

    /**
     * Reads a stored integer and missing paths with implicit and explicit defaults. The getter
     * must retain the stored value and select the expected missing-path fallbacks.
     */
    @Test
    void testGetInt() {
        Config config = createYamlConfig();
        config.set("count", 42);
        assertEquals(42, config.getInt("count"));
        assertEquals(0, config.getInt("nonexistent"));
        assertEquals(100, config.getInt("nonexistent", 100));
    }

    /**
     * Reads a stored long beyond the integer range and two missing paths. Long access must retain
     * its width and apply implicit or explicit defaults only when absent.
     */
    @Test
    void testGetLong() {
        Config config = createYamlConfig();
        config.set("bigNumber", 9999999999L);
        assertEquals(9999999999L, config.getLong("bigNumber"));
        assertEquals(0L, config.getLong("nonexistent"));
        assertEquals(100L, config.getLong("nonexistent", 100L));
    }

    /**
     * Reads a stored fractional value and missing paths with two fallback choices. Floating-point
     * assertions must retain the stored fraction within the selected comparison tolerance.
     */
    @Test
    void testGetDouble() {
        Config config = createYamlConfig();
        config.set("ratio", 3.14159);
        assertEquals(3.14159, config.getDouble("ratio"), 0.00001);
        assertEquals(0.0, config.getDouble("nonexistent"), 0.00001);
        assertEquals(1.5, config.getDouble("nonexistent", 1.5), 0.00001);
    }

    /**
     * Reads a stored string and missing paths with default text. The existing name must be
     * returned while absent keys use empty or explicitly supplied fallback strings.
     */
    @Test
    void testGetString() {
        Config config = createYamlConfig();
        config.set("name", "TestName");
        assertEquals("TestName", config.getString("name"));
        assertEquals("", config.getString("nonexistent"));
        assertEquals("default", config.getString("nonexistent", "default"));
    }

    /**
     * Reads stored {@code true} and {@code false} values and missing paths. Boolean access must retain the stored
     * choices and use implicit {@code false} or explicit {@code true} for absence.
     */
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

    /**
     * Stores values of each supported scalar and list family. Type predicates must recognize the
     * stored wrapper kinds without treating strings as integers or integers as strings.
     */
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

    /**
     * Creates a nested section through a dotted write and reads its child. Existing-section lookup
     * must expose that child, while a missing section yields an empty result.
     */
    @Test
    void testGetSection() {
        Config config = createYamlConfig();
        config.set("section.innerKey", "innerValue");

        ConfigSection section = config.getSection("section");
        assertNotNull(section);
        assertEquals("innerValue", section.get("innerKey"));

        assertTrue(config.getSection("nonexistent").isEmpty());
    }

    /**
     * Stores one nested section and one scalar string at the root. The section predicate must
     * distinguish the nested container from the scalar entry.
     */
    @Test
    void testIsSection() {
        Config config = createYamlConfig();
        config.set("section.key", "value");
        config.set("notSection", "string");

        assertTrue(config.isSection("section"));
        assertFalse(config.isSection("notSection"));
    }

    /**
     * Stores two section-valued entries and one scalar root entry. Root section selection must
     * include the two containers and exclude the non-section value from its result.
     */
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

    /**
     * Reads a stored list and missing list paths. The result must retain the stored element count
     * and select {@code null} or an explicit fallback for absence.
     */
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

    /**
     * Stores lists of each supported scalar family and uses typed list accessors. Results must
     * retain expected lengths and representative first values for string, integer, and boolean
     * lists.
     */
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

    /**
     * Stores a list containing a linked map in the configuration. Map-list selection must retain
     * one map element rather than dropping it during typed list extraction.
     */
    @Test
    void testGetMapList() {
        Config config = createYamlConfig();
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("key1", "value1");
        config.set("maps", List.of(map1));

        assertEquals(1, config.getMapList("maps").size());
    }

    // ==================== Exists and Remove Tests ====================

    /**
     * Checks exact and case-insensitive presence before removing a root key. Presence must respect
     * the selected case mode, and the removed key must disappear afterward.
     */
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

    /**
     * Copies current entries and then replaces the root from a linked map. The replacement must
     * expose its new key and discard the previous root keys.
     */
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

    /**
     * Stores a root value and requests the mutable root section. The returned section must be
     * present and expose the same entry through its own lookup.
     */
    @Test
    void testGetRootSection() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertNotNull(config.getRootSection());
        assertEquals("value", config.getRootSection().get("key"));
    }

    // ==================== Keys Tests ====================

    /**
     * Creates root and nested entries before taking key snapshots. Child-inclusive enumeration
     * must contain dotted descendants, while root-only enumeration retains their parent but
     * excludes those descendants.
     */
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

    /**
     * Applies defaults containing one existing key and one missing key. The existing value must
     * remain, and the return count must include only the newly added entry.
     */
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

    /**
     * Saves an initial YAML value, replaces its file text, and reloads the same instance. The
     * in-memory getter must observe the replacement file value afterward.
     */
    @Test
    void testReload() throws IOException {
        Config config = new Config(yamlFile.toString(), Config.YAML);
        config.set("key", "original");
        config.save();

        Files.writeString(yamlFile.toPath(), "key: newValue\n");
        config.reload();
        assertEquals("newValue", config.getString("key"));
    }

    /**
     * Requests reload on an unbound YAML configuration. The operation must throw the missing-file
     * state error rather than attempting to parse an unspecified backing path.
     */
    @Test
    void testReloadWithoutFile() {
        assertThrows(IllegalStateException.class, () -> createYamlConfig().reload());
    }

    // ==================== Save Tests ====================

    /**
     * Binds and saves an in-memory YAML configuration to a supplied file. The operation must
     * report success and another instance must read the saved value.
     */
    @Test
    void testSaveToFile() {
        Config config = createYamlConfig();
        config.set("key", "value");
        assertTrue(config.save(yamlFile));
        assertTrue(yamlFile.exists());
        assertEquals("value", new Config(yamlFile, Config.YAML).getString("key"));
    }

    /**
     * Requests save on an unbound in-memory configuration. The missing destination must raise a
     * state error instead of reporting a successfully persisted configuration.
     */
    @Test
    void testSaveWithoutFile() {
        assertThrows(IllegalStateException.class, () -> createYamlConfig().save());
    }

    /**
     * Saves an in-memory configuration using a caller-supplied JSON serializer. A later JSON load
     * must find the saved string at the same root key.
     */
    @Test
    void testSaveAsJson() {
        Config config = new Config(Config.JSON);
        config.set("key", "value");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        assertTrue(config.saveAsJson(jsonFile, false, gson));
        assertEquals("value", new Config(jsonFile, Config.JSON).getString("key"));
    }

    // ==================== Nested Key Tests ====================

    /**
     * Writes a value through three path levels and reads intermediate sections. Dotted getters and
     * section predicates must agree on the resulting nested structure.
     */
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

    /**
     * Loads an existing file with an unknown extension under automatic detection. Incorrect
     * configurations must supply the tested scalar fallbacks and an empty key set.
     */
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

    /**
     * Loads text that cannot form the expected JSON object. Construction must propagate the
     * configuration wrapper exception rather than accepting malformed data as an empty
     * configuration.
     */
    @Test
    void testInvalidJsonThrowsException() throws IOException {
        Files.writeString(jsonFile.toPath(), "invalid json {{{");
        assertThrows(ConfigException.class, () -> new Config(jsonFile.toString(), Config.JSON));
    }

    /**
     * Loads an empty YAML file and an empty JSON object file. Both supported representations must
     * produce an empty root map rather than requiring populated configuration entries.
     */
    @Test
    @SuppressWarnings("ResultOfMethodCallIgnored")
    void testLoadEmptyFiles() throws IOException {
        yamlFile.createNewFile();
        Files.writeString(jsonFile.toPath(), "{}");

        assertTrue(new Config(yamlFile.toString(), Config.YAML).getAll().isEmpty());
        assertTrue(new Config(jsonFile.toString(), Config.JSON).getAll().isEmpty());
    }

    // ==================== Constants Tests ====================

    /**
     * Checks extension mappings and compatibility selector values together. Standard aliases must
     * resolve to their expected format selectors, including the properties and enumeration
     * constant aliases.
     */
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
