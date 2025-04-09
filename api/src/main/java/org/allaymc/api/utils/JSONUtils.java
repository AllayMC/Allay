package org.allaymc.api.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.UtilityClass;
import org.allaymc.api.pack.Pack;
import org.allaymc.api.pack.PackManifest;
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
 * Gson Utilities.
 * <p>
 * Advantage:
 * <li>When the data volume is less than 10,000, the speed has an absolute advantage</li>
 * <li>API and annotation support is relatively complete, and loose parsing is supported</li>
 * <li>Supports a wide range of data sources (strings, objects, files, streams, readers)</li>
 *
 * @author duanxinyuan | CoolLoong
 */
@UtilityClass
public class JSONUtils {

    private static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        // Prevent escaping some special characters to Unicode encoding
        gsonBuilder.disableHtmlEscaping();
        registerTypeAdapter(gsonBuilder);
        GSON = gsonBuilder.create();
    }

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

        // Custom type adapters
        gsonBuilder.registerTypeAdapter(SemVersion.class, new SemVersion.Serializer());
        gsonBuilder.registerTypeAdapter(SemVersion.class, new SemVersion.Deserializer());
        gsonBuilder.registerTypeAdapter(Pack.Type.class, new Pack.Type.Deserializer());
        gsonBuilder.registerTypeAdapter(Pack.Type.class, new Pack.Type.Serializer());
        gsonBuilder.registerTypeAdapter(PackManifest.Capability.class, new PackManifest.Capability.Deserializer());
        gsonBuilder.registerTypeAdapter(PackManifest.Capability.class, new PackManifest.Capability.Serializer());
    }

    /**
     * Parse a JSON string (from reader) to an object.
     *
     * @param reader the reader.
     * @param type   the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(Reader reader, Class<V> type) {
        JsonReader jsonReader = new JsonReader(Objects.requireNonNull(reader));
        return GSON.fromJson(jsonReader, type);
    }

    /**
     * Parse a JSON string (from reader) to an object.
     *
     * @param reader    the reader.
     * @param typeToken the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(Reader reader, TypeToken<V> typeToken) {
        JsonReader jsonReader = new JsonReader(Objects.requireNonNull(reader));
        return GSON.fromJson(jsonReader, typeToken);
    }

    /**
     * Parse a JSON string (from input stream) to an object.
     *
     * @param inputStream the input stream.
     * @param type        the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return GSON.fromJson(reader, type);
    }

    /**
     * Parse a JSON string (from input stream) to an object.
     *
     * @param inputStream the input stream.
     * @param typeToken   the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(InputStream inputStream, TypeToken<V> typeToken) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Parse a JSON string (from input stream) to a list.
     *
     * @param inputStream the input stream.
     * @param type        the type of elements in the list.
     *
     * @return the list.
     */
    public static <V> List<V> fromList(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Parse a JSON string (in file) to an object.
     *
     * @param file the file.
     * @param type the type of the object.
     *
     * @return the object.
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
     * Parse a JSON string (in file) to an object.
     *
     * @param file      the file.
     * @param typeToken the type of the object.
     *
     * @return the object.
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
     * Parse a JSON string (in file) to a list.
     *
     * @param file the file.
     * @param type the type of elements in the list.
     *
     * @return the list.
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
     * Parse a JSON string to an object.
     *
     * @param json the JSON string.
     * @param type the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(String json, Class<V> type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Parse a JSON string to an object.
     *
     * @param json the JSON string.
     * @param type the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(String json, Type type) {
        return GSON.fromJson(json, type);
    }

    /**
     * Parse a JSON string to an object.
     *
     * @param json      the JSON string.
     * @param typeToken the type of the object.
     *
     * @return the object.
     */
    public static <V> V from(String json, TypeToken<V> typeToken) {
        return GSON.fromJson(json, typeToken.getType());
    }

    /**
     * Parse a JSON string to a list.
     *
     * @param json the JSON string.
     * @param type the type of elements in the list.
     *
     * @return the list.
     */
    public static <V> List<V> fromList(String json, Class<V> type) {
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(json, typeToken.getType());
    }

    /**
     * Parse a JSON string to a map.
     *
     * @param json the JSON string.
     *
     * @return the map.
     */
    public static Map<String, Object> fromMap(String json) {
        return GSON.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    /**
     * Parse a JSON string (from input stream) to an object leniently.
     *
     * @param inputStream the input stream.
     * @param type        the type of the object.
     *
     * @return the object.
     */
    public static <V> V fromLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Parse a JSON string to an object leniently.
     *
     * @param inputStream the input stream.
     * @param type        the type of the object.
     *
     * @return the object.
     */
    public static <V> V fromLenient(InputStream inputStream, TypeToken<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        return GSON.fromJson(reader, type);
    }

    /**
     * Parse a JSON string to a list leniently.
     *
     * @param inputStream the input stream.
     * @param type        the type of elements in the list.
     *
     * @return the list.
     */
    public static <V> List<V> fromListLenient(InputStream inputStream, Class<V> type) {
        JsonReader reader = new JsonReader(new InputStreamReader(Objects.requireNonNull(inputStream)));
        reader.setStrictness(Strictness.LENIENT);
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return GSON.fromJson(reader, typeToken.getType());
    }

    /**
     * Parse a JSON string (in file) to an object leniently.
     *
     * @param file the file.
     * @param type the type of the object.
     *
     * @return the object.
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
     * Parse a JSON string (in file) to a list leniently.
     *
     * @param file the file.
     * @param type the type of elements in the list.
     *
     * @return the list.
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
     * Parse a JSON string to an object leniently.
     *
     * @param json the JSON string.
     * @param type the type of the object.
     *
     * @return the object.
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
     * Parse a JSON string to an object leniently.
     *
     * @param json the JSON string.
     * @param type the type of the object.
     *
     * @return the object.
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
     * Parse a JSON string to an object leniently.
     *
     * @param json      the JSON string.
     * @param typeToken the type of the object.
     *
     * @return the object.
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
     * Read a list from a JSON string leniently.
     *
     * @param json the JSON string.
     * @param type the type of elements in the list.
     *
     * @return the list.
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
     * Convert a list to a JSON string.
     *
     * @param list the list.
     *
     * @return the JSON string.
     */
    public static <V> String to(List<V> list) {
        return GSON.toJson(list);
    }

    /**
     * Convert an object to a JSON string.
     *
     * @param v the object.
     *
     * @return the JSON string.
     */
    public static <V> String to(V v) {
        return GSON.toJson(v);
    }

    /**
     * Write an object to a file.
     *
     * @param path the file path.
     * @param list the list.
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
     * Write an object to a file.
     *
     * @param path the file path.
     * @param v    the object.
     */
    public static <V> void toFile(String path, V v) {
        toFile(path, v, null);
    }

    /**
     * Write an object to a file.
     *
     * @param path                   the file path.
     * @param v                      the object.
     * @param jsonWriterConfigurator the JSON writer configurator.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or {@code null} if the field does not exist.
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
            propertyValue = jsonByKey.toString();
        }
        return propertyValue;
    }

    /**
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
     */
    public static BigInteger getAsBigInteger(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return new BigInteger(String.valueOf(0.00));
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return new BigInteger(String.valueOf(0.00));
        }
        try {
            return jsonByKey.getAsBigInteger();
        } catch (Exception e) {
            throw new GsonException("gson get big integer error, json: {}, key: {}", json, key, e);
        }
    }

    /**
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or {@code false} if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or zero if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or {@code null} if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the value of the field, or {@code null} if the field does not exist.
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
     * Get the value of a field from a JSON string.
     *
     * @return the field.
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
     * Add a field to a JSON string.
     *
     * @return the JSON string with the field added.
     */
    public static <V> String add(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * Add a field to a JSON string.
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
     * Remove a field from a JSON string.
     *
     * @return the JSON string with the field removed.
     */
    public static String remove(String json, String key) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObj = element.getAsJsonObject();
        jsonObj.remove(key);
        return jsonObj.toString();
    }

    /**
     * Update a field in a JSON string.
     *
     * @return the JSON string with the field updated.
     */
    public static <V> String update(String json, String key, V value) {
        JsonElement element = JsonParser.parseString(json);
        JsonObject jsonObject = element.getAsJsonObject();
        jsonObject.remove(key);
        add(jsonObject, key, value);
        return jsonObject.toString();
    }

    /**
     * Format a JSON string.
     *
     * @return the formatted JSON string.
     */
    public static String format(String json) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonElement jsonElement = JsonParser.parseString(json);
        return gson.toJson(jsonElement);
    }

    /**
     * Check if a string is a JSON string.
     *
     * @return {@code true} if the string is a JSON string, otherwise {@code false}.
     */
    public static boolean isJson(String json) {
        try {
            return JsonParser.parseString(json).isJsonObject();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @author duanxinyuan
     */
    private static class NumberTypeAdapter<T> extends TypeAdapter<Number> {

        private final Class<T> c;

        public NumberTypeAdapter(Class<T> c) {
            this.c = c;
        }

        @Override
        public void write(JsonWriter jsonWriter, Number number) throws IOException {
            if (number != null) {
                jsonWriter.value(number);
            } else {
                jsonWriter.nullValue();
            }
        }

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
                return null;
            }
        }
    }

    /**
     * @author duanxinyuan
     */
    @Setter
    @Getter
    public static class GsonException extends FormativeException {

        public GsonException() {
            super();
        }

        public GsonException(String message) {
            super(message);
        }

        public GsonException(Throwable cause) {
            super(cause);
        }

        public GsonException(String format, Object... arguments) {
            super(format, arguments);
        }
    }
}