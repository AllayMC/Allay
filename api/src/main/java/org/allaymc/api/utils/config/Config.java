package org.allaymc.api.utils.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.Utils;
import org.jetbrains.annotations.NotNull;
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
 * {@code Config} binds mutable configuration sections to YAML, JSON, properties, or line-list
 * files and supplies typed access through {@code ConfigSection}. File-based loads retain a
 * destination for later saves, while in-memory instances need an explicit file before ordinary
 * saving. The public {@code format} map controls extension detection, and the integer format
 * constants identify the available parsers and writers.
 *
 * <p>
 * Loading and saving are not transactional. Missing-file creation retains the supplied default
 * section directly, whereas existing files receive missing top-level defaults after parsing.
 * Detection occurs only for existing files, so a new file loaded with {@code DETECT} keeps
 * that unresolved type and ordinary saving writes empty content. Asynchronous saving captures
 * serialized text but resolves the mutable {@code file} when the worker executes; callers
 * coordinate mutations and destination changes until outstanding writes finish, and no future
 * is returned to expose their completion.
 *
 * @author MagicDroidX (Nukkit) | daoge_cmd
 */
@Slf4j
public class Config {

    /**
     * Format detection request marker.
     */
    public static final int DETECT = -1;
    /**
     * Properties text format selector.
     */
    public static final int PROPERTIES = 0;
    /**
     * Alias of {@code PROPERTIES}.
     */
    public static final int CNF = PROPERTIES;
    /**
     * JSON object format selector.
     */
    public static final int JSON = 1;
    /**
     * YAML document format selector.
     */
    public static final int YAML = 2;
    /**
     * Line-based key-list format selector.
     */
    public static final int ENUM = 5;
    /**
     * Alias of {@code ENUM}.
     */
    public static final int ENUMERATION = ENUM;

    /**
     * {@code format} maps lowercase extensions to format selectors for existing-file detection.
     * The map is publicly mutable, so changing it affects later detection calls across all
     * configuration instances.
     */
    public static final Map<String, Integer> format = new TreeMap<>();

    /**
     * {@code PROPERTIES_PATTERN} accepts properties lines with a restricted ASCII key and at least
     * one value character. Parsing ignores lines outside that grammar rather than treating them
     * as malformed documents.
     */
    private static final Pattern PROPERTIES_PATTERN = Pattern.compile("[a-zA-Z0-9\\-_.]*=.+");
    /**
     * {@code GSON} reads JSON objects and writes pretty-printed configuration text for the
     * standard
     * JSON format. Custom JSON load and save overloads use their supplied serializer instead.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    // Installs the standard extension aliases used by existing-file format detection.
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

    /**
     * {@code config} holds the current mutable root. Missing-file creation and {@code
     * setAll(ConfigSection)}
     * retain caller-owned sections directly, while root accessors also expose this same section.
     */
    private ConfigSection config = new ConfigSection();
    /**
     * {@code file} is the current save destination and reload source. Asynchronous workers read
     * this field when writing, so a later rebinding can redirect an earlier serialized save.
     */
    private File file;
    /**
     * {@code correct} gates several getters and saves after type selection fails. The flag does
     * not prove a successful disk write or parse because some errors are logged or thrown without
     * clearing it.
     */
    @Getter
    private boolean correct;
    /**
     * {@code type} selects parsing and writing. Existing-file loading resolves {@code DETECT},
     * while missing-file creation leaves that marker unchanged and ordinary saving uses empty
     * content for unsupported selectors.
     */
    private int type = DETECT;

    /**
     * Creates an empty in-memory configuration using YAML. No file is bound, so ordinary saving
     * requires a destination first; the correctness flag is initially set despite the absence of
     * a backing file.
     */
    public Config() {
        this(YAML);
    }

    /**
     * Creates an empty in-memory configuration retaining the supplied format selector.
     * Construction
     * does not validate that selector, binds no file, and sets {@code correct} so later parse
     * or save operations decide whether the selector is usable.
     */
    public Config(int type) {
        this.type = type;
        this.correct = true;
        this.config = new ConfigSection();
    }

    /**
     * Loads or creates the supplied file with format detection requested. Detection is applied
     * only when the file already exists; a newly created file keeps {@code DETECT} and can
     * initially be saved with empty content.
     */
    public Config(String file) {
        this(file, DETECT);
    }

    /**
     * Loads or creates the supplied file with format detection requested. Detection is applied
     * only when the file already exists; a newly created file keeps {@code DETECT} and can
     * initially be saved with empty content.
     */
    public Config(File file) {
        this(file.toString(), DETECT);
    }

    /**
     * Loads or creates the supplied file with an initially empty default section. The selected
     * format controls parsing and saving, and {@code DETECT} is resolved only on the existing-file
     * path rather than during missing-file creation.
     */
    public Config(String file, int type) {
        this(file, type, new ConfigSection());
    }

    /**
     * Loads or creates the supplied file with an initially empty default section. The selected
     * format controls parsing and saving, and {@code DETECT} is resolved only on the existing-file
     * path rather than during missing-file creation.
     */
    public Config(File file, int type) {
        this(file.toString(), type, new ConfigSection());
    }

    /**
     * Loads the file and applies the supplied non-{@code null} default section. Missing-file creation
     * retains {@code defaultMap} directly, while an existing file receives only absent top-level
     * keys and is saved when that insertion adds entries.
     */
    public Config(String file, int type, ConfigSection defaultMap) {
        this.load(file, type, defaultMap);
    }

    /**
     * Loads the file and applies the supplied non-{@code null} default section. Missing-file creation
     * retains {@code defaultMap} directly, while an existing file receives only absent top-level
     * keys and is saved when that insertion adds entries.
     */
    public Config(File file, int type, ConfigSection defaultMap) {
        this.load(file.toString(), type, defaultMap);
    }

    /**
     * Clears the current root and resets {@code correct} before reloading the bound file.
     * An unbound instance throws after clearing its data, and a failed reload does not restore
     * the previous section contents.
     */
    public void reload() {
        // Reload discards the current section before checking the file or parsing a
        // replacement. Any failure therefore leaves no automatic path back to its old entries.
        this.config.clear();
        this.correct = false;
        if (this.file == null) {
            throw new IllegalStateException("Failed to reload Config. File object is undefined.");
        }
        this.load(this.file.toString(), this.type);
    }

    /**
     * Loads or creates a file using extension detection and an empty default section. A missing
     * file is created before detection is attempted, so that path can retain the unresolved
     * {@code DETECT} selector.
     */
    public boolean load(String file) {
        return this.load(file, DETECT);
    }

    /**
     * Loads or creates a file with the supplied format and no added defaults. Existing-file
     * properties and list parsing can retain prior entries, while JSON and YAML parsing replace
     * the current root section.
     */
    public boolean load(String file, int type) {
        return this.load(file, type, new ConfigSection());
    }

    /**
     * Binds the source file, resets the type and correctness state, and loads or creates its
     * contents. The non-{@code null} default section is retained for a new file or merged at the top
     * level after an existing file is parsed. A {@code true} result does not establish that
     * every preceding file-creation or read operation succeeded.
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

    /**
     * Attempts to create the bound file and saves the supplied default section as the current
     * root. The section reference is retained directly, so later changes through either owner
     * affect the same configuration data.
     *
     * <p>
     * Directory creation results are ignored and file-creation I/O errors are logged before saving
     * is attempted. This path does not resolve {@code DETECT}; unsupported selectors serialize
     * empty content. The method returns {@code true} after the save call rather than validating
     * creation or retaining an earlier root for rollback.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private boolean createNewFile(ConfigSection defaultMap) {
        try {
            File parent = this.file.getParentFile();
            if (parent != null) {
                parent.mkdirs();
            }
            this.file.createNewFile();
        } catch (IOException e) {
            // File creation failure does not stop this path. Log it before attempting
            // the normal save, which can still fail while writing the configured destination.
            log.error("Could not create Config {}", this.file, e);
        }
        // Missing-file defaults become the live root, so their ownership stays shared with
        // the caller rather than receiving the structural copy used by map-based replacement.
        this.config = defaultMap;
        this.save();
        return true;
    }

    /**
     * Resolves a detected format, parses existing text, and adds missing top-level defaults.
     * An unresolved selector clears {@code correct} and returns {@code false} before parsing.
     *
     * <p>
     * A read failure is logged and leaves the parse input empty rather than stopping this path.
     * After parsing, inserted defaults trigger a save, which can replace disk contents even when
     * the original read failed. Properties and line-list parsers append to the current root;
     * JSON and YAML parsers replace it, so callers needing a cleared reload use {@code reload}.
     */
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
            // A failed read leaves content empty and parsing still proceeds. Later default
            // insertion can trigger a save, so this path does not preserve the file automatically.
            log.error("An error occurred while loading the file {}", this.file, e);
        }

        this.parseContent(content);
        if (!this.correct) {
            return false;
        }

        // Defaults are merged only after parsing and only at the root. Save inserted
        // entries so subsequent loads can observe them without requiring the same default section.
        if (this.setDefault(defaultMap) > 0) {
            this.save();
        }
        return true;
    }

    /**
     * Returns the selector for the final lowercase filename extension. Leading-dot names and
     * unmapped extensions return {@code DETECT}, and the publicly mutable {@code format} map
     * determines which aliases are recognized at this call.
     */
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
     * Parses a non-{@code null} stream using the current format only while {@code correct} is set.
     * Parsing reads and closes the stream through {@code Utils.readString}; a {@code null} stream or
     * I/O failure returns {@code false}, while an already incorrect instance leaves the stream
     * unread and unclosed.
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
                // The reader has no complete text to parse after an I/O failure. Report
                // that failure and return false without claiming that the configuration was loaded.
                log.error("An error occurred while loading a config from an input stream", e);
                return false;
            }
        }
        return correct;
    }

    /**
     * Parses a non-{@code null} stream using the supplied JSON serializer only while {@code correct} is
     * set.
     * Parsing reads and closes the stream through {@code Utils.readString}; a {@code null} stream or
     * I/O failure returns {@code false}, while an already incorrect instance leaves the stream
     * unread and unclosed.
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
                // The reader has no complete text to parse after an I/O failure. Report
                // that failure and return false without claiming that the configuration was loaded.
                log.error("An error occurred while loading a config from an input stream", e);
                return false;
            }
        }
        return correct;
    }

    /**
     * Rebinds the destination and delegates ordinary saving with the chosen asynchronous mode.
     * A queued write uses the destination field later, so callers must not rebind this instance
     * again while they depend on an earlier save reaching its original path.
     */
    public boolean save(File file, boolean async) {
        this.file = file;
        return save(async);
    }

    /**
     * Rebinds the destination and writes the selected format synchronously. Incorrect
     * configurations
     * return {@code false}; a completed write returns {@code true}, and write I/O failures become
     * {@code ConfigException} rather than a boolean failure.
     */
    public boolean save(File file) {
        this.file = file;
        return save();
    }

    /**
     * Rebinds the destination and serializes with the supplied JSON serializer. A {@code true}
     * asynchronous
     * result indicates submission rather than completed persistence, and rebinding before worker
     * execution can redirect the queued content to another destination.
     */
    public boolean saveAsJson(@NotNull File file, boolean async, @NotNull Gson gson) {
        this.file = file;
        return saveAsJson(async, gson);
    }

    /**
     * Writes the bound file synchronously using the selected format. An absent file raises
     * {@code IllegalStateException}, an incorrect configuration returns {@code false}, and write
     * I/O failures propagate as {@code ConfigException} after logging.
     */
    public boolean save() {
        return this.save(false);
    }

    /**
     * Serializes the current root as JSON with a trailing newline and dispatches the write.
     * Incorrect configurations return {@code false}; asynchronous mode returns after submission
     * and does not expose the worker's completion or write exception to this caller.
     */
    public boolean saveAsJson(boolean async, @NotNull Gson gson) {
        if (!this.correct) {
            return false;
        }
        writeToFile(async, gson.toJson(this.config) + "\n");
        return true;
    }

    /**
     * Serializes the current root using the selected format and writes or queues the text.
     * An unbound file fails before correctness is checked, incorrect configurations return
     * {@code false}, and unsupported format selectors serialize empty content.
     *
     * <p>
     * Synchronous writes propagate I/O failures as {@code ConfigException}. Asynchronous writes
     * return after submission, retain no public future, and resolve {@code file} when their worker
     * runs; a {@code null} mode is invalid because dispatch unboxes it.
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

    /**
     * Writes serialized text immediately or submits a worker to the server's virtual-thread pool.
     * Only {@code content} is captured as this call's serialized value; the destination remains
     * the mutable {@code file} read inside the worker.
     *
     * <p>
     * Callers therefore coordinate destination changes with outstanding writes if they require
     * the text to reach the originally bound file. The submitted future is discarded, so a later
     * worker failure is not returned to the save caller and no combined ordering or atomic
     * replacement guarantee is established here.
     */
    private void writeToFile(boolean async, String content) {
        if (async) {
            // Only content is captured by this worker. Keep file rebinding coordinated
            // externally because writeContentToFile resolves the destination when the worker executes.
            Server.getInstance().getVirtualThreadPool().submit(() -> writeContentToFile(content));
        } else {
            writeContentToFile(content);
        }
    }

    /**
     * Replaces the current destination's contents with UTF-8 text. The write uses the live
     * {@code file} field and no temporary-file replacement, so failures do not restore prior
     * bytes and are logged before becoming {@code ConfigException}.
     */
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
     * Sets a value through the root section's dot-path rules, regardless of {@code correct}.
     * The section retains supplied mutable values, and traversing an intermediate value that is
     * not a {@code ConfigSection} replaces it with a new section.
     */
    public void set(final String key, Object value) {
        this.config.set(key, value);
    }

    /**
     * Returns a root or dotted-path value, using {@code null} for absence or an incorrect
     * configuration. Returned mutable values can remain shared with the root section rather than
     * being independent copies.
     */
    public Object get(String key) {
        return this.get(key, null);
    }

    /**
     * Returns the section lookup result while {@code correct} is set, or {@code defaultValue}
     * otherwise. A default does not generally validate a present value's type, so incompatible
     * stored values can still fail when callers cast the result.
     */
    public <T> T get(String key, T defaultValue) {
        return this.correct ? this.config.get(key, defaultValue) : defaultValue;
    }

    /**
     * Returns the requested section, using a new empty section when absent or incorrect.
     * Existing sections remain shared, raw maps can be structurally converted, and incompatible
     * present values can still produce a cast failure or {@code null}.
     */
    @NotNull
    public ConfigSection getSection(String key) {
        return this.correct ? this.config.getSection(key) : new ConfigSection();
    }

    /**
     * Returns whether root lookup yields a section, regardless of {@code correct}.
     */
    public boolean isSection(String key) {
        return config.isSection(key);
    }

    /**
     * Returns a new section containing only section-valued entries under the supplied path.
     * Incorrect
     * configurations yield an empty section, and the structural copying or retained child
     * references
     * follow {@code ConfigSection.getSections} rather than guaranteeing independent mutable
     * values.
     */
    @NotNull
    public ConfigSection getSections(String key) {
        return this.correct ? this.config.getSections(key) : new ConfigSection();
    }

    /**
     * Returns a new section containing only section-valued entries at the root. Incorrect
     * configurations yield an empty section, and the structural copying or retained child
     * references
     * follow {@code ConfigSection.getSections} rather than guaranteeing independent mutable
     * values.
     */
    @NotNull
    public ConfigSection getSections() {
        return this.correct ? this.config.getSections() : new ConfigSection();
    }

    /**
     * Returns the numeric value at {@code key}, defaulting to zero for absence or an
     * incorrect configuration. Present values must be non-{@code null} {@code Number} instances;
     * incompatible
     * stored types are not converted from strings or replaced by the default.
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    /**
     * Returns the stored number converted to {@code int}, or {@code defaultValue} when
     * absent or incorrect. A present nonnumeric value or {@code null} still fails instead of
     * using that fallback, and numeric conversion follows {@code Number} behavior.
     */
    public int getInt(String key, int defaultValue) {
        return this.correct ? this.config.getInt(key, defaultValue) : defaultValue;
    }

    /**
     * Returns whether root lookup yields a boxed {@code Integer} value.
     */
    public boolean isInt(String key) {
        return config.isInt(key);
    }

    /**
     * Returns the numeric value at {@code key}, defaulting to zero for absence or an
     * incorrect configuration. Present values must be non-{@code null} {@code Number} instances;
     * incompatible
     * stored types are not converted from strings or replaced by the default.
     */
    public long getLong(String key) {
        return this.getLong(key, 0);
    }

    /**
     * Returns the stored number converted to {@code long}, or {@code defaultValue} when
     * absent or incorrect. A present nonnumeric value or {@code null} still fails instead of
     * using that fallback, and numeric conversion follows {@code Number} behavior.
     */
    public long getLong(String key, long defaultValue) {
        return this.correct ? this.config.getLong(key, defaultValue) : defaultValue;
    }

    /**
     * Returns whether root lookup yields a boxed {@code Long} value.
     */
    public boolean isLong(String key) {
        return config.isLong(key);
    }

    /**
     * Returns the numeric value at {@code key}, defaulting to zero for absence or an
     * incorrect configuration. Present values must be non-{@code null} {@code Number} instances;
     * incompatible
     * stored types are not converted from strings or replaced by the default.
     */
    public double getDouble(String key) {
        return this.getDouble(key, 0);
    }

    /**
     * Returns the stored number converted to {@code double}, or {@code defaultValue} when
     * absent or incorrect. A present nonnumeric value or {@code null} still fails instead of
     * using that fallback, and numeric conversion follows {@code Number} behavior.
     */
    public double getDouble(String key, double defaultValue) {
        return this.correct ? this.config.getDouble(key, defaultValue) : defaultValue;
    }

    /**
     * Returns whether root lookup yields a boxed {@code Double} value.
     */
    public boolean isDouble(String key) {
        return config.isDouble(key);
    }

    /**
     * Returns the section value converted to text, using the empty string for absence or an
     * incorrect configuration. A present {@code null} becomes the literal {@code "null"} through
     * {@code String.valueOf} while correctness is set.
     */
    @NotNull
    public String getString(String key) {
        return this.getString(key, "");
    }

    /**
     * Returns text through {@code ConfigSection.getString} while correct, or {@code defaultValue}
     * unchanged otherwise. In a correct section, present {@code null} and a {@code null} missing-key
     * default
     * become the literal string {@code "null"}.
     */
    public String getString(String key, String defaultValue) {
        return this.correct ? this.config.getString(key, defaultValue) : defaultValue;
    }

    /**
     * Returns whether root lookup yields a string, regardless of correctness state.
     */
    public boolean isString(String key) {
        return config.isString(key);
    }

    /**
     * Returns a stored boolean, using {@code false} when absent or when the configuration
     * is incorrect. Present non-boolean values are not parsed, and {@code null} stored values
     * can fail during unboxing rather than selecting the default.
     */
    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    /**
     * Returns a stored boolean, using {@code defaultValue} when absent or when the configuration
     * is incorrect. Present non-boolean values are not parsed, and {@code null} stored values
     * can fail during unboxing rather than selecting the default.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.correct ? this.config.getBoolean(key, defaultValue) : defaultValue;
    }

    /**
     * Returns whether root lookup yields a boolean, regardless of correctness state.
     */
    public boolean isBoolean(String key) {
        return config.isBoolean(key);
    }

    /**
     * Returns the stored list, using {@code null} for absence or an incorrect configuration.
     * The result retains the stored or supplied fallback reference; incompatible present values
     * can still fail casting instead of selecting an empty list.
     */
    public List<?> getList(String key) {
        return this.getList(key, null);
    }

    /**
     * Returns the stored list, using {@code defaultList} for absence or an incorrect
     * configuration.
     * The result retains the stored or supplied fallback reference; incompatible present values
     * can still fail casting instead of selecting an empty list.
     */
    public List<?> getList(String key, List<?> defaultList) {
        return this.correct ? this.config.getList(key, defaultList) : defaultList;
    }

    /**
     * Returns whether root lookup yields a list, regardless of correctness state.
     */
    public boolean isList(String key) {
        return config.isList(key);
    }

    /**
     * Returns a new mutable list of strings from supported scalar elements, without consulting
     * {@code correct}.
     * Strings, numbers, booleans, and characters are converted; other element types are omitted.
     * Missing lists yield empty results, while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<String> getStringList(String key) {
        return config.getStringList(key);
    }

    /**
     * Returns a new mutable list of integers from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters use their code units, and other numbers use integer
     * conversion; invalid strings and unsupported elements are omitted. Missing lists yield empty
     * results, while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Integer> getIntegerList(String key) {
        return config.getIntegerList(key);
    }

    /**
     * Returns a new mutable list of booleans from supported elements, without consulting {@code
     * correct}.
     * Boolean values and case-insensitive {@code "true"}/{@code "false"} strings are retained; numeric and other
     * elements are omitted. Missing lists yield empty results, while an incompatible stored list
     * value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Boolean> getBooleanList(String key) {
        return config.getBooleanList(key);
    }

    /**
     * Returns a new mutable list of doubles from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters use their code units, and other numbers use double
     * conversion; invalid strings and unsupported elements are omitted. Missing lists yield empty
     * results, while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Double> getDoubleList(String key) {
        return config.getDoubleList(key);
    }

    /**
     * Returns a new mutable list of floats from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters use their code units, and other numbers use float
     * conversion; invalid strings and unsupported elements are omitted. Missing lists yield empty
     * results, while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Float> getFloatList(String key) {
        return config.getFloatList(key);
    }

    /**
     * Returns a new mutable list of longs from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters use their code units, and other numbers use long
     * conversion; invalid strings and unsupported elements are omitted. Missing lists yield empty
     * results, while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Long> getLongList(String key) {
        return config.getLongList(key);
    }

    /**
     * Returns a new mutable list of bytes from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters and numbers use narrowing byte conversion, and
     * invalid strings or unsupported elements are omitted. Missing lists yield empty results,
     * while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Byte> getByteList(String key) {
        return config.getByteList(key);
    }

    /**
     * Returns a new mutable list of characters from supported scalar elements, without consulting
     * {@code correct}.
     * Single-character strings and characters are retained, while numbers use narrowed integer
     * code units and other elements are omitted. Missing lists yield empty results, while an
     * incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Character> getCharacterList(String key) {
        return config.getCharacterList(key);
    }

    /**
     * Returns a new mutable list of shorts from supported scalar elements, without consulting
     * {@code correct}.
     * Numeric strings are parsed, characters and numbers use narrowing short conversion, and
     * invalid strings or unsupported elements are omitted. Missing lists yield empty results,
     * while an incompatible stored list value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Short> getShortList(String key) {
        return config.getShortList(key);
    }

    /**
     * Returns a new mutable list of map elements from the stored list, without consulting {@code
     * correct}.
     * The new list retains each selected map reference, so changes to map contents remain shared
     * with the configuration. Missing lists yield empty results, while an incompatible stored list
     * value can still
     * fail casting before element conversion.
     */
    @NotNull
    public List<Map<?, ?>> getMapList(String key) {
        return config.getMapList(key);
    }

    // ==================== Utility Methods ====================

    /**
     * Returns whether the root key snapshot contains the exact supplied path. This operation
     * ignores {@code correct}, and only {@code ConfigSection} descendants contribute nested keys
     * to that snapshot.
     */
    public boolean exists(String key) {
        return config.exists(key);
    }

    /**
     * Returns whether a root or section-descendant key matches, optionally ignoring case. The
     * operation ignores {@code correct}; case-insensitive comparison lowercases whole paths with
     * {@code Locale.ROOT} rather than changing stored keys.
     */
    public boolean exists(String key, boolean ignoreCase) {
        return config.exists(key, ignoreCase);
    }

    /**
     * Removes the direct or nested root entry regardless of {@code correct}. Literal matching keys
     * take precedence over path traversal, and nested removal descends only through existing
     * {@code ConfigSection} values.
     */
    public void remove(String key) {
        config.remove(key);
    }

    /**
     * Returns a shallow mutable map copy of the root entries. Replacing entries in the result does
     * not replace root entries, but nested sections and other mutable values remain shared.
     */
    @NotNull
    public Map<String, Object> getAll() {
        return this.config.getAllMap();
    }

    /**
     * Replaces the root with the linked-map constructor's structural conversion. That conversion
     * copies supported sections and lists but can retain other nested maps or mutable values, so
     * it is not a deep independent copy.
     */
    public void setAll(LinkedHashMap<String, Object> map) {
        this.config = new ConfigSection(map);
    }

    /**
     * Replaces the root by retaining the supplied section directly. Subsequent edits through
     * either owner affect the same root, and this operation neither saves it nor changes the
     * correctness flag.
     */
    public void setAll(ConfigSection section) {
        this.config = section;
    }

    /**
     * Returns the live mutable root section regardless of correctness state. Callers can change
     * configuration entries through the result, and those changes are used by subsequent getters
     * and saves.
     */
    @NotNull
    public ConfigSection getRootSection() {
        return config;
    }

    /**
     * Converts the default map structurally and inserts only absent top-level root keys. Existing
     * keys, including {@code null}-valued keys, remain unchanged; the return value counts newly inserted
     * root entries rather than nested fields.
     */
    public int setDefault(LinkedHashMap<String, Object> map) {
        return setDefault(new ConfigSection(map));
    }

    /**
     * Inserts absent top-level defaults and returns the increase in root entry count. Existing
     * keys and nested sections are not recursively merged, and inserted mutable values remain
     * shared with the supplied default section.
     */
    public int setDefault(ConfigSection map) {
        int size = this.config.size();
        this.fillDefaults(map, this.config);
        return this.config.size() - size;
    }

    /**
     * Adds each default entry whose direct key is absent in {@code data}. The merge does not
     * descend into existing sections, and inserted values retain the references obtained from the
     * default section.
     */
    private void fillDefaults(ConfigSection defaultMap, ConfigSection data) {
        for (String key : defaultMap.keySet()) {
            if (!data.containsKey(key)) {
                data.put(key, defaultMap.get(key));
            }
        }
    }

    /**
     * Returns a mutable snapshot of root and section-descendant keys while correct. Incorrect
     * configurations yield an empty mutable set, and raw nested maps do not contribute descendant
     * paths.
     */
    @NotNull
    public Set<String> getKeys() {
        return this.correct ? config.getKeys() : new HashSet<>();
    }

    /**
     * Returns a mutable key snapshot, optionally including {@code ConfigSection} descendants.
     * Incorrect configurations yield an empty set, and callers can change the returned set without
     * changing the stored root keys.
     */
    @NotNull
    public Set<String> getKeys(boolean child) {
        return this.correct ? config.getKeys(child) : new HashSet<>();
    }

    // ==================== Parsing Methods ====================

    /**
     * Parses text according to {@code type}, replacing the root for JSON or YAML and appending
     * for properties or line lists. Unsupported selectors clear {@code correct}; runtime parsing
     * failures are logged and wrapped in {@code ConfigException} without automatically clearing
     * that flag.
     */
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

    /**
     * Replaces the root with the object decoded by the supplied JSON serializer. A {@code null} decoded
     * map becomes an empty section, and runtime parsing or conversion failures are logged before
     * being wrapped in {@code ConfigException}.
     */
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

    /**
     * Appends nonblank lines as direct root keys with {@code true} values. Line-ending variants
     * are normalized, surrounding spaces remain part of accepted keys, and existing keys not
     * mentioned by the input are not removed.
     */
    private void parseList(String content) {
        // Normalize platform line endings before splitting so carriage returns do not
        // become part of line-list keys retained in the configuration's direct root map.
        content = content.replace("\r\n", "\n").replace("\r", "\n");
        for (String v : content.split("\n")) {
            if (!v.trim().isEmpty()) {
                config.put(v, true);
            }
        }
    }

    /**
     * Appends accepted properties lines after trimming each whole line. Keys use the restricted
     * pattern, boolean words are converted case-insensitively, other values remain strings, and
     * later repeated keys replace earlier values; malformed or empty-value lines are skipped.
     */
    private void parseProperties(String content) {
        // Normalize platform line endings before splitting so the same property grammar
        // applies to Unix, Windows, and carriage-return-separated configuration files.
        content = content.replace("\r\n", "\n").replace("\r", "\n");

        for (String line : content.split("\n")) {
            line = line.trim();

            if (line.isEmpty() || line.startsWith("#")) {
                continue;
            }

            // Lines outside the supported key=value grammar are ignored rather than
            // partially interpreted, preserving other accepted properties in this append-style parser.
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

    /**
     * Serializes root entries with CRLF endings and a timestamp header. Boolean values become
     * {@code "on"} or {@code "off"}; other values use their text representation without escaping,
     * so nested objects and multiline strings are not guaranteed to round-trip.
     */
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

    /**
     * Serializes only root keys as CRLF-terminated lines, discarding entry values. Loading that
     * representation assigns {@code true} to each nonblank line, so callers use it for membership
     * lists rather than preserving arbitrary mapped values.
     */
    private String writeEnum() {
        StringBuilder content = new StringBuilder();
        for (Map.Entry<String, Object> entry : this.config.entrySet()) {
            content.append(entry.getKey()).append("\r\n");
        }
        return content.toString();
    }

    /**
     * Creates a YAML parser and writer configured for block-style output.
     */
    private static Yaml createYaml() {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(options);
    }
}
