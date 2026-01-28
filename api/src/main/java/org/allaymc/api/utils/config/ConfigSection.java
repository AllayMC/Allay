package org.allaymc.api.utils.config;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * A section of configuration data that supports hierarchical key-value storage.
 * <p>
 * ConfigSection extends {@link LinkedHashMap} and provides convenient methods for:
 * <ul>
 *   <li>Nested key access using dot notation (e.g., "parent.child.key")</li>
 *   <li>Type-safe value retrieval with default values</li>
 *   <li>List value extraction with automatic type conversion</li>
 * </ul>
 *
 * @author fromgate | daoge_cmd
 */
public class ConfigSection extends LinkedHashMap<String, Object> {

    /**
     * Creates an empty ConfigSection.
     */
    public ConfigSection() {
        super();
    }

    /**
     * Creates a ConfigSection with a single key-value pair.
     *
     * @param key   the key
     * @param value the value
     */
    public ConfigSection(String key, Object value) {
        this();
        this.set(key, value);
    }

    /**
     * Creates a ConfigSection from a LinkedHashMap.
     * <p>
     * Nested maps are automatically converted to ConfigSection instances.
     *
     * @param map the source map (can be null)
     */
    @SuppressWarnings("unchecked")
    public ConfigSection(LinkedHashMap<String, Object> map) {
        this();
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            switch (value) {
                case LinkedHashMap<?, ?> linkedHashMap -> super.put(entry.getKey(), new ConfigSection((LinkedHashMap<String, Object>) linkedHashMap));
                case List<?> list -> super.put(entry.getKey(), parseList(list));
                case null, default -> super.put(entry.getKey(), value);
            }
        }
    }

    /**
     * Creates a ConfigSection from a Map.
     * <p>
     * Nested maps are automatically converted to ConfigSection instances.
     *
     * @param map the source map (can be null)
     */
    @SuppressWarnings("unchecked")
    public ConfigSection(Map<String, Object> map) {
        this();
        if (map == null || map.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            switch (value) {
                case LinkedHashMap<?, ?> linkedHashMap -> super.put(entry.getKey(), new ConfigSection((LinkedHashMap<String, Object>) linkedHashMap));
                case Map<?, ?> nestedMap -> super.put(entry.getKey(), new ConfigSection((Map<String, Object>) nestedMap));
                case List<?> list -> super.put(entry.getKey(), parseList(list));
                case null, default -> super.put(entry.getKey(), value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private List<?> parseList(List<?> list) {
        List<Object> newList = new ArrayList<>(list.size());
        for (Object o : list) {
            if (o instanceof LinkedHashMap<?, ?>) {
                newList.add(new ConfigSection((LinkedHashMap<String, Object>) o));
            } else {
                newList.add(o);
            }
        }
        return newList;
    }

    // ==================== Basic Operations ====================

    /**
     * Returns a copy of this section as a LinkedHashMap.
     *
     * @return a new LinkedHashMap containing all entries
     */
    @NotNull
    public Map<String, Object> getAllMap() {
        return new LinkedHashMap<>(this);
    }

    /**
     * Returns a copy of this section.
     *
     * @return a new ConfigSection with the same entries
     */
    @NotNull
    public ConfigSection getAll() {
        return new ConfigSection(this);
    }

    /**
     * Gets a value by key.
     *
     * @param key the key (supports dot notation for nested access)
     * @return the value, or {@code null}  if not found
     */
    public Object get(String key) {
        return this.get(key, null);
    }

    /**
     * Gets a value by key with a default value.
     * <p>
     * Supports dot notation for nested key access (e.g., "parent.child.key").
     *
     * @param key          the key
     * @param defaultValue the default value if key is not found
     * @param <T>          the value type
     * @return the value, or defaultValue if not found
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> T get(String key, T defaultValue) {
        if (key == null || key.isEmpty()) {
            return defaultValue;
        }

        // Direct key lookup
        if (super.containsKey(key)) {
            Object value = super.get(key);
            if (defaultValue != null && !defaultValue.getClass().isInstance(value)) {
                if (value instanceof Map map && defaultValue instanceof ConfigSection) {
                    return (T) new ConfigSection(map);
                }
            }
            return (T) value;
        }

        // Nested key lookup (dot notation)
        String[] keys = key.split("\\.", 2);
        if (keys.length < 2 || !super.containsKey(keys[0])) {
            return defaultValue;
        }

        Object value = super.get(keys[0]);
        if (value instanceof ConfigSection section) {
            return section.get(keys[1], defaultValue);
        } else if (value instanceof Map map) {
            return new ConfigSection(map).get(keys[1], defaultValue);
        }

        return defaultValue;
    }

    /**
     * Sets a value for the specified key.
     * <p>
     * Supports dot notation for nested key access. Parent sections are
     * automatically created if they don't exist.
     *
     * @param key   the key (supports dot notation)
     * @param value the value to set
     */
    public void set(String key, Object value) {
        String[] subKeys = key.split("\\.", 2);
        if (subKeys.length > 1) {
            ConfigSection childSection;
            Object existing = super.get(subKeys[0]);
            if (existing instanceof ConfigSection existingSection) {
                childSection = existingSection;
            } else {
                childSection = new ConfigSection();
            }
            childSection.set(subKeys[1], value);
            super.put(subKeys[0], childSection);
        } else {
            super.put(subKeys[0], value);
        }
    }

    // ==================== Section Operations ====================

    /**
     * Checks if the value at the specified key is a ConfigSection.
     *
     * @param key the key
     * @return true if the value is a ConfigSection
     */
    public boolean isSection(String key) {
        return this.get(key) instanceof ConfigSection;
    }

    /**
     * Gets the ConfigSection at the specified key.
     *
     * @param key the key
     * @return the ConfigSection, or an empty ConfigSection if not found
     */
    @NotNull
    public ConfigSection getSection(String key) {
        return this.get(key, new ConfigSection());
    }

    /**
     * Gets all ConfigSection entries at the root level.
     * <p>
     * Example: For a config with keys a1 (section), a2 (section), a3 (string),
     * this method returns only a1 and a2.
     *
     * @return a ConfigSection containing only section entries
     */
    @NotNull
    public ConfigSection getSections() {
        return getSections(null);
    }

    /**
     * Gets all ConfigSection entries under the specified path.
     *
     * @param key the path (null or empty for root level)
     * @return a ConfigSection containing only section entries
     */
    @NotNull
    public ConfigSection getSections(String key) {
        ConfigSection sections = new ConfigSection();
        ConfigSection parent = (key == null || key.isEmpty()) ? this.getAll() : getSection(key);
        parent.forEach((k, v) -> {
            if (v instanceof ConfigSection) {
                sections.put(k, v);
            }
        });
        return sections;
    }

    // ==================== Primitive Type Getters ====================

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
        return this.get(key, (Number) defaultValue).intValue();
    }

    /**
     * Checks if the value is an Integer.
     *
     * @param key the key
     * @return true if the value is an Integer
     */
    public boolean isInt(String key) {
        return get(key) instanceof Integer;
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
        return this.get(key, (Number) defaultValue).longValue();
    }

    /**
     * Checks if the value is a Long.
     *
     * @param key the key
     * @return true if the value is a Long
     */
    public boolean isLong(String key) {
        return get(key) instanceof Long;
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
        return this.get(key, (Number) defaultValue).doubleValue();
    }

    /**
     * Checks if the value is a Double.
     *
     * @param key the key
     * @return true if the value is a Double
     */
    public boolean isDouble(String key) {
        return get(key) instanceof Double;
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
        Object result = this.get(key, defaultValue);
        return String.valueOf(result);
    }

    /**
     * Checks if the value is a String.
     *
     * @param key the key
     * @return true if the value is a String
     */
    public boolean isString(String key) {
        return get(key) instanceof String;
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
        return this.get(key, defaultValue);
    }

    /**
     * Checks if the value is a Boolean.
     *
     * @param key the key
     * @return true if the value is a Boolean
     */
    public boolean isBoolean(String key) {
        return get(key) instanceof Boolean;
    }

    // ==================== List Getters ====================

    /**
     * Gets a list value.
     *
     * @param key the key
     * @return the list, or {@code null}  if not found
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
        return this.get(key, defaultList);
    }

    /**
     * Checks if the value is a List.
     *
     * @param key the key
     * @return true if the value is a List
     */
    public boolean isList(String key) {
        return get(key) instanceof List;
    }

    /**
     * Gets a list of strings.
     * <p>
     * Values are converted to strings using {@link String#valueOf(Object)}.
     *
     * @param key the key
     * @return the string list, or empty list if not found
     */
    @NotNull
    public List<String> getStringList(String key) {
        List<?> value = this.getList(key);
        if (value == null) {
            return new ArrayList<>(0);
        }
        List<String> result = new ArrayList<>(value.size());
        for (Object o : value) {
            if (o instanceof String || o instanceof Number || o instanceof Boolean || o instanceof Character) {
                result.add(String.valueOf(o));
            }
        }
        return result;
    }

    /**
     * Gets a list of integers.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the integer list, or empty list if not found
     */
    @NotNull
    public List<Integer> getIntegerList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Integer> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Integer i) {
                result.add(i);
            } else if (object instanceof String s) {
                try {
                    result.add(Integer.valueOf(s));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((int) c);
            } else if (object instanceof Number n) {
                result.add(n.intValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of booleans.
     *
     * @param key the key
     * @return the boolean list, or empty list if not found
     */
    @NotNull
    public List<Boolean> getBooleanList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Boolean> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Boolean b) {
                result.add(b);
            } else if (object instanceof String s) {
                if ("true".equalsIgnoreCase(s)) {
                    result.add(true);
                } else if ("false".equalsIgnoreCase(s)) {
                    result.add(false);
                }
            }
        }
        return result;
    }

    /**
     * Gets a list of doubles.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the double list, or empty list if not found
     */
    @NotNull
    public List<Double> getDoubleList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Double> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Double d) {
                result.add(d);
            } else if (object instanceof String s) {
                try {
                    result.add(Double.valueOf(s));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((double) c);
            } else if (object instanceof Number n) {
                result.add(n.doubleValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of floats.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the float list, or empty list if not found
     */
    @NotNull
    public List<Float> getFloatList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Float> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Float f) {
                result.add(f);
            } else if (object instanceof String s) {
                try {
                    result.add(Float.valueOf(s));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((float) c);
            } else if (object instanceof Number n) {
                result.add(n.floatValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of longs.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the long list, or empty list if not found
     */
    @NotNull
    public List<Long> getLongList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Long> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Long l) {
                result.add(l);
            } else if (object instanceof String s) {
                try {
                    result.add(Long.valueOf(s));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((long) c);
            } else if (object instanceof Number n) {
                result.add(n.longValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of bytes.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the byte list, or empty list if not found
     */
    @NotNull
    public List<Byte> getByteList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Byte> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Byte b) {
                result.add(b);
            } else if (object instanceof String s) {
                try {
                    result.add(Byte.valueOf(s));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((byte) c.charValue());
            } else if (object instanceof Number n) {
                result.add(n.byteValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of characters.
     * <p>
     * Supports automatic conversion from Number and single-character String.
     *
     * @param key the key
     * @return the character list, or empty list if not found
     */
    @NotNull
    public List<Character> getCharacterList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Character> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Character c) {
                result.add(c);
            } else if (object instanceof String s) {
                if (s.length() == 1) {
                    result.add(s.charAt(0));
                }
            } else if (object instanceof Number n) {
                result.add((char) n.intValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of shorts.
     * <p>
     * Supports automatic conversion from Number, String, and Character.
     *
     * @param key the key
     * @return the short list, or empty list if not found
     */
    @NotNull
    public List<Short> getShortList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Short> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Short s) {
                result.add(s);
            } else if (object instanceof String str) {
                try {
                    result.add(Short.valueOf(str));
                } catch (NumberFormatException ignored) {
                }
            } else if (object instanceof Character c) {
                result.add((short) c.charValue());
            } else if (object instanceof Number n) {
                result.add(n.shortValue());
            }
        }
        return result;
    }

    /**
     * Gets a list of maps.
     *
     * @param key the key
     * @return the map list, or empty list if not found
     */
    @NotNull
    public List<Map<?, ?>> getMapList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Map<?, ?>> result = new ArrayList<>(list.size());
        for (Object object : list) {
            if (object instanceof Map<?, ?> map) {
                result.add(map);
            }
        }
        return result;
    }

    // ==================== Utility Methods ====================

    /**
     * Checks if a key exists.
     *
     * @param key the key (supports dot notation)
     * @return true if the key exists
     */
    public boolean exists(String key) {
        return exists(key, false);
    }

    /**
     * Checks if a key exists with optional case-insensitive matching.
     *
     * @param key        the key (supports dot notation)
     * @param ignoreCase whether to ignore case when matching
     * @return true if the key exists
     */
    public boolean exists(String key, boolean ignoreCase) {
        String searchKey = ignoreCase ? key.toLowerCase(Locale.ROOT) : key;
        for (String existKey : this.getKeys(true)) {
            String compareKey = ignoreCase ? existKey.toLowerCase(Locale.ROOT) : existKey;
            if (compareKey.equals(searchKey)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a key from this section.
     * <p>
     * Supports dot notation for nested key removal.
     *
     * @param key the key to remove
     */
    public void remove(String key) {
        if (key == null || key.isEmpty()) {
            return;
        }

        if (super.containsKey(key)) {
            super.remove(key);
            return;
        }

        // Handle nested key removal
        if (key.contains(".")) {
            String[] keys = key.split("\\.", 2);
            Object value = super.get(keys[0]);
            if (value instanceof ConfigSection section) {
                section.remove(keys[1]);
            }
        }
    }

    /**
     * Gets all keys including nested keys.
     *
     * @return a set of all keys
     */
    @NotNull
    public Set<String> getKeys() {
        return this.getKeys(true);
    }

    /**
     * Gets keys with optional nested key inclusion.
     *
     * @param child true to include nested keys (dot notation)
     * @return a set of keys
     */
    @NotNull
    public Set<String> getKeys(boolean child) {
        Set<String> keys = new LinkedHashSet<>();
        this.forEach((key, value) -> {
            keys.add(key);
            if (child && value instanceof ConfigSection section) {
                for (String childKey : section.getKeys(true)) {
                    keys.add(key + "." + childKey);
                }
            }
        });
        return keys;
    }
}
