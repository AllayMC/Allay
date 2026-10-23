package org.allaymc.server.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.allaymc.api.utils.SemVersion;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Consumer;

/**
 * {@code JSONUtils} reads, writes, and modifies JSON using a shared serializer with date,
 * numeric, and version adapters. Stream and reader overloads leave their supplied inputs
 * open, while file-reading overloads open readers without explicitly closing them. File
 * writers append rather than replace, so repeated calls can create adjacent JSON roots.
 *
 * <p>
 * Numeric decoding distinguishes primitive and wrapper targets rather than applying one
 * uniform malformed-value rule. Some primitive conversions and the float wrapper use
 * zero-producing parsers, other targets return {@code null} after conversion failure, and
 * the adapter can leave a failing reader token unconsumed. Property helpers have separate
 * missing-value results, including the invalid decimal-string construction in the big-integer
 * fallback. Object insertion also preserves strings and numbers as native properties while
 * serializing other values into JSON-containing strings, which differs from inserting a
 * nested JSON object or boolean directly.
 *
 * @author duanxinyuan | CoolLoong
 */
@UtilityClass
public class JSONUtils {

    /**
     * {@code GSON} holds the shared date, numeric, and version configuration built during class
     * initialization. Ordinary helper overloads reuse it, while {@code format} creates a separate
     * pretty-printing serializer for its parsed tree.
     */
    private static final Gson GSON;

    // Builds the shared serializer with custom scalar/version adapters and the configured date pattern.
    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        // Keep HTML-sensitive characters literal in encoded JSON text. Callers embedding
        // the result into HTML must therefore apply the surrounding document's own escaping rules.
        gsonBuilder.disableHtmlEscaping();
        registerTypeAdapter(gsonBuilder);
        GSON = gsonBuilder.create();
    }

    /**
     * Installs the numeric coercion adapters and both version conversion directions on the
     * supplied
     * builder. Numeric targets use the adapter's type-specific failure rules, and version JSON
     * retains only the major, minor, and patch components.
     */
    private static void registerTypeAdapter(GsonBuilder gsonBuilder) {
        gsonBuilder.registerTypeAdapter(short.class, new NumberTypeAdapter<>(short.class));
        gsonBuilder.registerTypeAdapter(Short.class, new NumberTypeAdapter<>(Short.class));
        gsonBuilder.registerTypeAdapter(int.class, new NumberTypeAdapter<>(int.class));
        gsonBuilder.registerTypeAdapter(Integer.class, new NumberTypeAdapter<>(Integer.class));
        gsonBuilder.registerTypeAdapter(long.class, new NumberTypeAdapter<>(long.class));
        gsonBuilder.registerTypeAdapter(Long.class, new NumberTypeAdapter<>(Long.class));
        gsonBuilder.registerTypeAdapter(float.class, new NumberTypeAdapter<>(float.class));
        gsonBuilder.registerTypeAdapter(Float.class, new NumberTypeAdapter<>(Float.class));
        gsonBuilder.registerTypeAdapter(double.class, new NumberTypeAdapter<>(double.class));
        gsonBuilder.registerTypeAdapter(Double.class, new NumberTypeAdapter<>(Double.class));
        gsonBuilder.registerTypeAdapter(BigDecimal.class, new NumberTypeAdapter<>(BigDecimal.class));
        gsonBuilder.registerTypeAdapter(SemVersion.class, new SemVersion.Serializer());
        gsonBuilder.registerTypeAdapter(SemVersion.class, new SemVersion.Deserializer());
    }

    /**
     * Decodes one value from the supplied reader using the shared adapters. The reader must be
     * present and remains open after this call, so its owner closes it; parsing failures propagate
     * instead of selecting an empty object.
     */
    public static <V> V from(Reader reader, Class<V> type) {
        JsonReader jsonReader = new JsonReader(Objects.requireNonNull(reader));
        return GSON.fromJson(jsonReader, type);
    }

    /**
     * Decodes one value from the supplied reader using the shared adapters. The reader must be
     * present and remains open after this call, so its owner closes it; parsing failures propagate
     * instead of selecting an empty object.
     */
    public static <V> V from(Reader reader, TypeToken<V> typeToken) {
        JsonReader jsonReader = new JsonReader(Objects.requireNonNull(reader));
        return GSON.fromJson(jsonReader, typeToken);
    }

    /**
     * Decodes the requested value from a stream using the platform-default character encoding.
     * The stream must be present and remains open; its owner closes it, and the shared adapters
     * control target-specific numeric or version conversions.
     */
    public static <V> V from(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return GSON.fromJson(reader, type);
    }

    /**
     * Decodes the requested value from a stream using the platform-default character encoding.
     * The stream must be present and remains open; its owner closes it, and the shared adapters
     * control target-specific numeric or version conversions.
     */
    public static <V> V from(InputStream inputStream, TypeToken<V> typeToken) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Decodes the requested value from a stream using the platform-default character encoding.
     * The stream must be present and remains open; its owner closes it, and the shared adapters
     * control target-specific numeric or version conversions.
     */
    public static <V> List<V> fromList(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Decodes the requested file value with platform-default character encoding. This helper
     * does not explicitly close its internally opened reader; missing-file failures are wrapped
     * in {@code GsonException}, while parsing failures follow the shared serializer's behavior.
     */
    public static <V> V from(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return GSON.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new GsonException("gson from error, file path: {}, type: {}", file.getPath(), type, e);
        }
    }

    /**
     * Decodes the requested file value with platform-default character encoding. This helper
     * does not explicitly close its internally opened reader; missing-file failures are wrapped
     * in {@code GsonException}, while parsing failures follow the shared serializer's behavior.
     */
    public static <V> V from(File file, TypeToken<V> typeToken) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            return GSON.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            throw new GsonException("gson from error, file path: {}, type: {}", file.getPath(), typeToken.getType(), e);
        }
    }

    /**
     * Decodes the requested file value with platform-default character encoding. This helper
     * does not explicitly close its internally opened reader; missing-file failures are wrapped
     * in {@code GsonException}, while parsing failures follow the shared serializer's behavior.
     */
    public static <V> List<V> fromList(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return GSON.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            throw new GsonException("gson from error, file path: {}, type: {}", file.getPath(), type, e);
        }
    }

    /**
     * Decodes the requested object, collection, or map from JSON text with the shared adapters.
     * Absent or empty input can produce {@code null}, and incompatible JSON shapes or malformed
     * content propagate serializer failures rather than a general empty-container default.
     */
    public static <V> V from(String json, Class<V> type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Decodes the requested object, collection, or map from JSON text with the shared adapters.
     * Absent or empty input can produce {@code null}, and incompatible JSON shapes or malformed
     * content propagate serializer failures rather than a general empty-container default.
     */
    public static <V> V from(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Decodes the requested object, collection, or map from JSON text with the shared adapters.
     * Absent or empty input can produce {@code null}, and incompatible JSON shapes or malformed
     * content propagate serializer failures rather than a general empty-container default.
     */
    public static <V> V from(String json, TypeToken<V> typeToken) {
        return GSON.fromJson(json, typeToken.getType());
    }

    /**
     * Decodes the requested object, collection, or map from JSON text with the shared adapters.
     * Absent or empty input can produce {@code null}, and incompatible JSON shapes or malformed
     * content propagate serializer failures rather than a general empty-container default.
     */
    public static <V> List<V> fromList(String json, Class<V> type) {
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(json, typeToken.getType());
    }

    /**
     * Decodes the requested object, collection, or map from JSON text with the shared adapters.
     * Absent or empty input can produce {@code null}, and incompatible JSON shapes or malformed
     * content propagate serializer failures rather than a general empty-container default.
     */
    public static Map<String, Object> fromMap(String json) {
        return GSON.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    /**
     * Decodes stream text with a reader explicitly set to {@code Strictness.LENIENT}. The
     * platform-default character encoding is used and the stream remains open, while target-type
     * conversion can still fail despite accepting lenient syntax.
     */
    public static <V> V fromLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Decodes stream text with a reader explicitly set to {@code Strictness.LENIENT}. The
     * platform-default character encoding is used and the stream remains open, while target-type
     * conversion can still fail despite accepting lenient syntax.
     */
    public static <V> V fromLenient(InputStream inputStream, TypeToken<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Decodes stream text with a reader explicitly set to {@code Strictness.LENIENT}. The
     * platform-default character encoding is used and the stream remains open, while target-type
     * conversion can still fail despite accepting lenient syntax.
     */
    public static <V> List<V> fromListLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Decodes file text with explicit lenient syntax and platform-default character encoding.
     * The internally opened reader is not explicitly closed; missing-file errors become
     * {@code GsonException}, and leniency does not suppress incompatible target-type failures.
     */
    public static <V> V fromLenient(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.setStrictness(Strictness.LENIENT);
            return GSON.fromJson(reader, type);
        } catch (FileNotFoundException e) {
            throw new GsonException("gson lenient from error, file path: {}, type: {}", file.getPath(), type, e);
        }
    }

    /**
     * Decodes file text with explicit lenient syntax and platform-default character encoding.
     * The internally opened reader is not explicitly closed; missing-file errors become
     * {@code GsonException}, and leniency does not suppress incompatible target-type failures.
     */
    public static <V> List<V> fromListLenient(File file, Class<V> type) {
        try {
            JsonReader reader = new JsonReader(new FileReader(file));
            reader.setStrictness(Strictness.LENIENT);
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return GSON.fromJson(reader, typeToken.getType());
        } catch (FileNotFoundException e) {
            throw new GsonException("gson lenient from error, file path: {}, type: {}", file.getPath(), type, e);
        }
    }

    /**
     * Decodes JSON text using an explicitly lenient reader and the shared target adapters.
     * {@code null} or empty strings return {@code null} before parsing; other text can still fail if
     * its values cannot be decoded into the requested target representation.
     */
    public static <V> V fromLenient(String json, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Decodes JSON text using an explicitly lenient reader and the shared target adapters.
     * {@code null} or empty strings return {@code null} before parsing; other text can still fail if
     * its values cannot be decoded into the requested target representation.
     */
    public static <V> V fromLenient(String json, Type type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Decodes JSON text using an explicitly lenient reader and the shared target adapters.
     * {@code null} or empty strings return {@code null} before parsing; other text can still fail if
     * its values cannot be decoded into the requested target representation.
     */
    public static <V> V fromLenient(String json, TypeToken<V> typeToken) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Decodes JSON text using an explicitly lenient reader and the shared target adapters.
     * {@code null} or empty strings return {@code null} before parsing; other text can still fail if
     * its values cannot be decoded into the requested target representation.
     */
    public static <V> List<V> fromListLenient(String json, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonReader reader = new JsonReader(new StringReader(json));
        reader.setStrictness(Strictness.LENIENT);
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Serializes the supplied list to JSON text with the shared adapters.
     */
    public static <V> String to(List<V> list) {
        return GSON.toJson(list);
    }

    /**
     * Serializes the supplied value to JSON text with the shared adapters.
     */
    public static <V> String to(V v) {
        return GSON.toJson(v);
    }

    /**
     * Appends one serialized list to the path with platform-default character encoding. The writer
     * is closed before return, but no separator or enclosing document is added between calls;
     * failures are wrapped in {@code GsonException}.
     */
    public static <V> void toFile(String path, List<V> list) {
        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(path, true))) {
            GSON.toJson(list, new TypeToken<List<V>>() {
            }.getType(), jsonWriter);
            jsonWriter.flush();
        } catch (Exception e) {
            throw new GsonException("gson to file error, path: {}, list: {}", path, list, e);
        }
    }

    /**
     * Appends one object value through the writer-configurator overload without additional
     * configuration. Existing file contents remain before the new root, and {@code null} values
     * fail when the delegated implementation requests their runtime class.
     */
    public static <V> void toFile(String path, V v) {
        toFile(path, v, null);
    }

    /**
     * Appends one object using its runtime class and an optional writer configurator. The file
     * uses platform-default encoding and the writer is closed after flushing; a {@code null} object,
     * configuration failure, or write failure becomes {@code GsonException}.
     */
    public static <V> void toFile(String path, V v, Consumer<JsonWriter> jsonWriterConfigurator) {
        try (JsonWriter jsonWriter = new JsonWriter(new FileWriter(path, true))) {
            if (jsonWriterConfigurator != null) jsonWriterConfigurator.accept(jsonWriter);
            GSON.toJson(v, v.getClass(), jsonWriter);
            jsonWriter.flush();
        } catch (Exception e) {
            throw new GsonException("gson to file error, path: {}, obj: {}", path, v, e);
        }
    }

    /**
     * Returns a property's string conversion, or {@code null} for empty input or an absent key.
     * Values rejected by {@code getAsString} fall back to their JSON text, so objects, arrays,
     * or explicit JSON {@code null} can produce serialized representations instead.
     */
    public static String getAsString(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        String propertyValue;
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            propertyValue = jsonByKey.getAsString();
        } catch (Exception e) {
            // A value without a supported scalar string conversion still has JSON text.
            // Return that representation so structured properties remain inspectable through this helper.
            propertyValue = jsonByKey.toString();
        }
        return propertyValue;
    }

    /**
     * Returns the property's {@code int} conversion, using zero for empty input or an
     * absent key. A present incompatible property is wrapped in {@code GsonException}, while
     * root parsing occurs before that conversion-specific error boundary.
     */
    public static int getAsInt(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0;
        }
        try {
            return jsonByKey.getAsInt();
        } catch (Exception e) {
            throw new GsonException("gson get int error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns the property's {@code long} conversion, using zero for empty input or an
     * absent key. A present incompatible property is wrapped in {@code GsonException}, while
     * root parsing occurs before that conversion-specific error boundary.
     */
    public static long getAsLong(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0L;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0L;
        }
        try {
            return jsonByKey.getAsLong();
        } catch (Exception e) {
            throw new GsonException("gson get long error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns the property's {@code double} conversion, using zero for empty input or an
     * absent key. A present incompatible property is wrapped in {@code GsonException}, while
     * root parsing occurs before that conversion-specific error boundary.
     */
    public static double getAsDouble(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0.0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0.0;
        }
        try {
            return jsonByKey.getAsDouble();
        } catch (Exception e) {
            throw new GsonException("gson get double error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns a present property's big-integer conversion. Empty input or an absent property
     * attempts to parse {@code "0.0"} as {@code BigInteger} and therefore throws
     * {@code NumberFormatException}; that fallback executes outside the conversion wrapper.
     */
    public static BigInteger getAsBigInteger(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            // This fallback constructs a decimal-spelled zero, which BigInteger rejects.
            // The failure occurs before the conversion catch and is not wrapped as GsonException.
            return new BigInteger(String.valueOf(0.00));
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            // This fallback constructs a decimal-spelled zero, which BigInteger rejects.
            // The failure occurs before the conversion catch and is not wrapped as GsonException.
            return new BigInteger(String.valueOf(0.00));
        }
        try {
            return jsonByKey.getAsBigInteger();
        } catch (Exception e) {
            throw new GsonException("gson get big integer error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns a property's decimal conversion, using a new decimal zero for empty input or an
     * absent key. Incompatible present values become {@code GsonException}, while root parsing
     * still occurs outside that conversion-specific wrapper.
     */
    public static BigDecimal getAsBigDecimal(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return new BigDecimal("0.0");
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return new BigDecimal("0.0");
        }
        try {
            return jsonByKey.getAsBigDecimal();
        } catch (Exception e) {
            throw new GsonException("gson get big decimal error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns a primitive property's boolean interpretation, using {@code false} for empty input
     * or an absent key. Boolean primitives retain their value, strings use {@code BooleanUtils}
     * with a special {@code true} result for {@code "1"}, and numbers use nonzero integer truth.
     * Nonprimitive properties fail the cast before the conversion wrapper.
     */
    public static boolean getAsBoolean(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        JsonPrimitive jsonByKey = (JsonPrimitive) getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return false;
        }
        try {
            if (jsonByKey.isBoolean()) {
                return jsonByKey.getAsBoolean();
            } else {
                if (jsonByKey.isString()) {
                    String string = jsonByKey.getAsString();
                    if ("1".equals(string)) {
                        return true;
                    } else {
                        return BooleanUtils.toBoolean(string);
                    }
                } else {// number
                    return BooleanUtils.toBoolean(jsonByKey.getAsInt());
                }
            }
        } catch (Exception e) {
            throw new GsonException("gson get boolean error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Returns the property's {@code byte} conversion, using zero for empty input or an
     * absent key. A present incompatible property is wrapped in {@code GsonException}, while
     * root parsing occurs before that conversion-specific error boundary.
     */
    public static byte getAsByte(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0;
        }
        try {
            return jsonByKey.getAsByte();
        } catch (Exception e) {
            throw new GsonException("gson get byte error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Decodes JSON text contained in a property's scalar string representation. Empty input or
     * an absent key returns {@code null}; the property is not directly deserialized as a nested
     * object, and extraction or decoding failures become {@code GsonException}.
     */
    public static <V> V getAsObject(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            return from(jsonByKey.getAsString(), type);
        } catch (Exception e) {
            throw new GsonException("gson get list error, json: {}, key: {}, type: {}", json, key, type, e);
        }
    }

    /**
     * Decodes an array-valued property into a list of the requested element class. Empty input
     * or an absent key returns {@code null}; an incompatible present property or element
     * conversion
     * failure becomes {@code GsonException} after root parsing.
     */
    public static <V> List<V> getAsList(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            JsonArray jsonArray = jsonByKey.getAsJsonArray();
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return from(jsonArray.toString(), typeToken);
        } catch (Exception e) {
            throw new GsonException("gson get list error, json: {}, key: {}, type: {}", json, key, type, e);
        }
    }

    /**
     * Returns the element under {@code key} from a parsed object root. An absent key returns
     * {@code null}, syntax errors become {@code GsonException}, and a non-object root can fail
     * outside that syntax-only wrapper.
     */
    public static JsonElement getAsJsonObject(String json, String key) {
        try {
            JsonElement element = JsonParser.parseString(json);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(key);
        } catch (JsonSyntaxException e) {
            throw new GsonException("gson get object from json error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Adds or replaces a property in parsed object text and returns compact JSON. Strings and
     * numbers become native properties; every other value is first serialized and stored as a
     * string containing that JSON representation.
     */
    public static <V> String add(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * Assigns a native string or number property, or stores serialized JSON inside a string for
     * other values. Objects and booleans therefore do not become nested native values through
     * this path, and {@code null} becomes the string {@code "null"}.
     */
    private static <V> void add(JsonObject jsonObject, String key, V value) {
        if (value instanceof String) {
            jsonObject.addProperty(key, (String) value);
        } else if (value instanceof Number) {
            jsonObject.addProperty(key, (Number) value);
        } else {
            jsonObject.addProperty(key, to(value));
        }
    }

    /**
     * Removes a property from a parsed object and returns compact JSON. An absent property
     * leaves the object's data unchanged, while invalid JSON or a non-object root propagates
     * the parser or tree-access failure.
     */
    public static String remove(String json, String key) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObj = element.getAsJsonObject();
        jsonObj.remove(key);
        return jsonObj.toString();
    }

    /**
     * Removes the old property and re-adds it using the helper's native-string, native-number,
     * or JSON-containing-string rules. The returned object text is compact, and the replacement
     * can move the property to the end of object iteration order.
     */
    public static <V> String update(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        jsonObject.remove(key);
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * Pretty-prints the parsed JSON tree with a newly created default serializer.
     */
    public static String format(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(json);
        return gson.toJson(jsonElement);
    }

    /**
     * Returns whether the input parses specifically as a JSON object root. Arrays, primitives,
     * JSON {@code null}, and any caught parsing failure return {@code false}, so this predicate does not
     * recognize every syntactically valid JSON document.
     */
    public static boolean isJson(String json) {
        try {
            return JsonParser.parseString(json).isJsonObject();
        } catch (Exception e) {
            // This predicate treats parse failure as absence of an object root, leaving
            // callers with the same false result used for valid non-object JSON documents.
            return false;
        }
    }

    /**
     * {@code NumberTypeAdapter} applies target-specific numeric coercion to JSON token text.
     * Primitive and wrapper classes can select different default or rejection behavior, and
     * conversion failures are suppressed as {@code null} rather than wrapped here.
     *
     * @param <T> requested numeric target class
     *
     * @author duanxinyuan
     */
    private static class NumberTypeAdapter<T> extends TypeAdapter<Number> {

        /**
         * Target numeric coercion class.
         */
        private final Class<T> c;

        /**
         * Retains the requested class used to select numeric coercion behavior.
         */
        public NumberTypeAdapter(Class<T> c) {
            this.c = c;
        }

        /**
         * Writes the supplied number as a JSON numeric value or writes JSON {@code null} for {@code null}.
         * The writer remains caller-owned, and output failures propagate through its ordinary
         * {@code IOException} contract.
         */
        @Override
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number != null) {
                jsonWriter.value(number);
            } else {
                jsonWriter.nullValue();
            }
        }

        /**
         * Reads token text and applies the converter selected by {@code c}. Primitive short,
         * integer,
         * long, and double targets use zero-producing parsers, while primitive float uses strict
         * parsing; the float wrapper instead uses a zero-producing parser after its empty-text
         * check.
         *
         * <p>
         * Other registered wrappers and decimals reject malformed nonempty text, and empty wrapper
         * text returns {@code null}. Any caught failure also returns {@code null}, including
         * reader
         * failures; because a failed token read need not consume that token, callers cannot assume
         * this fallback leaves the reader ready for the next value.
         */
        @Override
        public Number read(JsonReader jsonReader) {
            try {
                if (jsonReader.peek() == null) {
                    return null;
                }
                String json = jsonReader.nextString();
                if (c == short.class) {
                    return NumberUtils.toShort(json);
                } else if (c == Short.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Short.parseShort(json);
                } else if (c == int.class) {
                    return NumberUtils.toInt(json);
                } else if (c == Integer.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Integer.parseInt(json);
                } else if (c == long.class) {
                    return NumberUtils.toLong(json);
                } else if (c == Long.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Long.parseLong(json);
                } else if (c == float.class) {
                    return Float.parseFloat(json);
                } else if (c == Float.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return NumberUtils.toFloat(json);
                } else if (c == double.class) {
                    return NumberUtils.toDouble(json);
                } else if (c == Double.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return Double.parseDouble(json);
                } else if (c == BigDecimal.class) {
                    if (StringUtils.isEmpty(json)) {
                        return null;
                    }
                    return new BigDecimal(json);
                } else {
                    return Integer.parseInt(json);
                }
            } catch (Exception e) {
                // Numeric coercion deliberately exposes failure as a null adapter result.
                // A failure before nextString consumes its token can leave that token unread.
                return null;
            }
        }
    }

    /**
     * {@code GsonException} represents JSON helper failures using brace-formatted messages from
     * {@code FormativeException}. Throwable constructors retain stack-trace text separately from
     * the inherited exception cause slot.
     *
     * @author duanxinyuan
     */
    @Setter
    @Getter
    public static class GsonException extends FormativeException {

        /**
         * Creates a JSON helper exception whose custom message is initially absent.
         */
        public GsonException() {
            super();
        }

        /**
         * Creates a JSON helper exception retaining the supplied message without substitution.
         */
        public GsonException(String message) {
            super(message);
        }

        /**
         * Creates a JSON helper exception containing the throwable's stack-trace text. The parent
         * retains that throwable separately, so the inherited {@code getCause} result does not
         * automatically expose the supplied error.
         */
        public GsonException(Throwable cause) {
            super(cause);
        }

        /**
         * Creates a JSON helper exception using the parent's placeholder and trailing-throwable
         * selection rules. Formatting occurs during construction, and a retained throwable
         * contributes
         * trace text without becoming the inherited exception cause.
         */
        public GsonException(String format, Object... arguments) {
            super(format, arguments);
        }
    }
}