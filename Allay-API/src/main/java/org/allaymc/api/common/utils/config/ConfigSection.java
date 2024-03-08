package org.allaymc.api.common.utils.config;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Allay Project 2024/3/2
 *
 * @author fromgate | daoge_cmd
 *
 * TODO: refactor
 */
public class ConfigSection extends LinkedHashMap<String, Object> {

    /**
     * Empty ConfigSection constructor
     */
    public ConfigSection() {
        super();
    }

    /**
     * Constructor of ConfigSection that contains initial key/value data
     */
    public ConfigSection(String key, Object value) {
        this();
        this.set(key, value);
    }

    /**
     * Constructor of ConfigSection, based on values stored in map.
     */
    @SuppressWarnings("unchecked")
    public ConfigSection(LinkedHashMap<String, Object> map) {
        this();
        if (map == null || map.isEmpty()) return;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof LinkedHashMap linkedHashMap) {
                super.put(entry.getKey(), new ConfigSection(linkedHashMap));
            } else if (entry.getValue() instanceof List list) {
                super.put(entry.getKey(), parseList(list));
            } else {
                super.put(entry.getKey(), entry.getValue());
            }
        }
    }

    @SuppressWarnings("unchecked")
    public ConfigSection(Map<String, Object> map) {
        this();
        if (map == null || map.isEmpty()) return;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof LinkedHashMap linkedHashMap) {
                super.put(entry.getKey(), new ConfigSection(linkedHashMap));
            } else if (entry.getValue() instanceof Map map1) {
                super.put(entry.getKey(), new ConfigSection(map1));
            } else if (entry.getValue() instanceof List list) {
                super.put(entry.getKey(), parseList(list));
            } else {
                super.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private List<?> parseList(List<?> list) {
        List<Object> newList = new ArrayList<>();

        for (Object o : list) {
            if (o instanceof LinkedHashMap) {
                newList.add(new ConfigSection((LinkedHashMap<String, Object>) o));
            } else {
                newList.add(o);
            }
        }

        return newList;
    }

    /**
     * Get root section as LinkedHashMap
     */
    public Map<String, Object> getAllMap() {
        return new LinkedHashMap<>(this);
    }

    /**
     * Get new instance of config section
     */
    public ConfigSection getAll() {
        return new ConfigSection(this);
    }

    /**
     * Get object by key. If section does not contain value, return null
     */
    public Object get(String key) {
        return this.get(key, null);
    }

    /**
     * Get object by key. If section does not contain value, return default value
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> T get(String key, T defaultValue) {
        if (key == null || key.isEmpty()) return defaultValue;
        if (super.containsKey(key)) {
            var value = super.get(key);
            if (defaultValue != null && !defaultValue.getClass().isInstance(value)) {
                if (value instanceof Map map && defaultValue instanceof ConfigSection) {
                    return (T) new ConfigSection(map);
                }
            }
            return (T) value;
        }
        String[] keys = key.split("\\.", 2);
        if (!super.containsKey(keys[0])) return defaultValue;
        Object value = super.get(keys[0]);
        if (value instanceof ConfigSection section) {
            return section.get(keys[1], defaultValue);
        } else if (value instanceof Map map) {
            ConfigSection section = new ConfigSection(map);
            return section.get(keys[1], defaultValue);
        }
        return defaultValue;
    }

    /**
     * Store value into config section
     */
    public void set(String key, Object value) {
        String[] subKeys = key.split("\\.", 2);
        if (subKeys.length > 1) {
            ConfigSection childSection = new ConfigSection();
            if (this.containsKey(subKeys[0]) && super.get(subKeys[0]) instanceof ConfigSection)
                childSection = (ConfigSection) super.get(subKeys[0]);
            childSection.set(subKeys[1], value);
            super.put(subKeys[0], childSection);
        } else super.put(subKeys[0], value);
    }

    /**
     * Check type of section element defined by key. Return true this element is ConfigSection
     */
    public boolean isSection(String key) {
        Object value = this.get(key);
        return value instanceof ConfigSection;
    }

    /**
     * Get config section element defined by key
     */
    public ConfigSection getSection(String key) {
        return this.get(key, new ConfigSection());
    }

    //@formatter:off

    /**
     * Get all ConfigSections in root path.
     * Example config:
     * a1:
     * b1:
     * c1:
     * c2:
     * a2:
     * b2:
     * c3:
     * c4:
     * a3: true
     * a4: "hello"
     * a5: 100
     * <p>
     * getSections() will return new ConfigSection, that contains sections a1 and a2 only.
     */
    //@formatter:on
    public ConfigSection getSections() {
        return getSections(null);
    }

    /**
     * Get sections (and only sections) from provided path
     *
     * @param key - config section path, if null or empty root path will be used.
     */
    public ConfigSection getSections(String key) {
        ConfigSection sections = new ConfigSection();
        ConfigSection parent = key == null || key.isEmpty() ? this.getAll() : getSection(key);
        if (parent == null) return sections;
        parent.forEach((key1, value) -> {
            if (value instanceof ConfigSection)
                sections.put(key1, value);
        });
        return sections;
    }

    /**
     * Get int value of config section element
     *
     * @param key - key (inside) current section (default value equals to 0)
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    /**
     * Get int value of config section element
     *
     * @param key          - key (inside) current section
     * @param defaultValue - default value that will return if section element is not exists
     */
    public int getInt(String key, int defaultValue) {
        return this.get(key, ((Number) defaultValue)).intValue();
    }

    /**
     * Check type of section element defined by key. Return true this element is Integer
     */
    public boolean isInt(String key) {
        Object val = get(key);
        return val instanceof Integer;
    }

    /**
     * Get long value of config section element
     *
     * @param key - key (inside) current section
     */
    public long getLong(String key) {
        return this.getLong(key, 0);
    }

    /**
     * Get long value of config section element
     *
     * @param key          - key (inside) current section
     * @param defaultValue - default value that will return if section element is not exists
     */
    public long getLong(String key, long defaultValue) {
        return this.get(key, ((Number) defaultValue)).longValue();
    }

    /**
     * Check type of section element defined by key. Return true this element is Long
     */
    public boolean isLong(String key) {
        Object val = get(key);
        return val instanceof Long;
    }

    /**
     * Get double value of config section element
     *
     * @param key - key (inside) current section
     */
    public double getDouble(String key) {
        return this.getDouble(key, 0);
    }

    /**
     * Get double value of config section element
     *
     * @param key          - key (inside) current section
     * @param defaultValue - default value that will return if section element is not exists
     */
    public double getDouble(String key, double defaultValue) {
        return this.get(key, ((Number) defaultValue)).doubleValue();
    }

    /**
     * Check type of section element defined by key. Return true this element is Double
     */
    public boolean isDouble(String key) {
        Object val = get(key);
        return val instanceof Double;
    }

    /**
     * Get String value of config section element
     *
     * @param key - key (inside) current section
     */
    public String getString(String key) {
        return this.getString(key, "");
    }

    /**
     * Get String value of config section element
     *
     * @param key          - key (inside) current section
     * @param defaultValue - default value that will return if section element is not exists
     */
    public String getString(String key, String defaultValue) {
        Object result = this.get(key, defaultValue);
        return String.valueOf(result);
    }

    /**
     * Check type of section element defined by key. Return true this element is String
     */
    public boolean isString(String key) {
        Object val = get(key);
        return val instanceof String;
    }

    /**
     * Get boolean value of config section element
     *
     * @param key - key (inside) current section
     */
    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    /**
     * Get boolean value of config section element
     *
     * @param key          - key (inside) current section
     * @param defaultValue - default value that will return if section element is not exists
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.get(key, defaultValue);
    }

    /**
     * Check type of section element defined by key. Return true this element is Integer
     */
    public boolean isBoolean(String key) {
        Object val = get(key);
        return val instanceof Boolean;
    }

    /**
     * Get List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<?> getList(String key) {
        return this.getList(key, null);
    }

    /**
     * Get List value of config section element
     *
     * @param key         - key (inside) current section
     * @param defaultList - default value that will return if section element is not exists
     */
    public List<?> getList(String key, List<?> defaultList) {
        return this.get(key, defaultList);
    }

    /**
     * Check type of section element defined by key. Return true this element is List
     */
    public boolean isList(String key) {
        Object val = get(key);
        return val instanceof List;
    }

    /**
     * Get String List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<String> getStringList(String key) {
        List<?> value = this.getList(key);
        if (value == null) {
            return new ArrayList<>(0);
        }
        List<String> result = new ArrayList<>();
        for (Object o : value) {
            if (o instanceof String || o instanceof Number || o instanceof Boolean || o instanceof Character) {
                result.add(String.valueOf(o));
            }
        }
        return result;
    }

    /**
     * Get Integer List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Integer> getIntegerList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Integer> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Integer) {
                result.add((Integer) object);
            } else if (object instanceof String) {
                try {
                    result.add(Integer.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((int) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).intValue());
            }
        }
        return result;
    }

    /**
     * Get Boolean List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Boolean> getBooleanList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Boolean> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Boolean) {
                result.add((Boolean) object);
            } else if (object instanceof String) {
                if (Boolean.TRUE.toString().equals(object)) {
                    result.add(true);
                } else if (Boolean.FALSE.toString().equals(object)) {
                    result.add(false);
                }
            }
        }
        return result;
    }

    /**
     * Get Double List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Double> getDoubleList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Double> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Double) {
                result.add((Double) object);
            } else if (object instanceof String) {
                try {
                    result.add(Double.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((double) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).doubleValue());
            }
        }
        return result;
    }

    /**
     * Get Float List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Float> getFloatList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Float> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Float) {
                result.add((Float) object);
            } else if (object instanceof String) {
                try {
                    result.add(Float.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((float) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).floatValue());
            }
        }
        return result;
    }

    /**
     * Get Long List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Long> getLongList(String key) {
        List<?> list = getList(key);
        if (list == null) {
            return new ArrayList<>(0);
        }
        List<Long> result = new ArrayList<>();
        for (Object object : list) {
            if (object instanceof Long) {
                result.add((Long) object);
            } else if (object instanceof String) {
                try {
                    result.add(Long.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((long) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).longValue());
            }
        }
        return result;
    }

    /**
     * Get Byte List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Byte> getByteList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Byte> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Byte) {
                result.add((Byte) object);
            } else if (object instanceof String) {
                try {
                    result.add(Byte.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((byte) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).byteValue());
            }
        }

        return result;
    }

    /**
     * Get Character List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Character> getCharacterList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Character> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Character) {
                result.add((Character) object);
            } else if (object instanceof String str) {
                if (str.length() == 1) {
                    result.add(str.charAt(0));
                }
            } else if (object instanceof Number) {
                result.add((char) ((Number) object).intValue());
            }
        }

        return result;
    }

    /**
     * Get Short List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Short> getShortList(String key) {
        List<?> list = getList(key);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Short> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Short) {
                result.add((Short) object);
            } else if (object instanceof String) {
                try {
                    result.add(Short.valueOf((String) object));
                } catch (Exception ex) {
                    //ignore
                }
            } else if (object instanceof Character) {
                result.add((short) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).shortValue());
            }
        }

        return result;
    }

    /**
     * Get Map List value of config section element
     *
     * @param key - key (inside) current section
     */
    public List<Map<?, ?>> getMapList(String key) {
        List<Map<?, ?>> list = (List<Map<?, ?>>) getList(key);
        List<Map<?, ?>> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        for (Map<?, ?> object : list) {
            if (object != null) {
                result.add(object);
            }
        }

        return result;
    }

    /**
     * Check existence of config section element
     */
    public boolean exists(String key, boolean ignoreCase) {
        if (ignoreCase) key = key.toLowerCase();
        for (String existKey : this.getKeys(true)) {
            if (ignoreCase) existKey = existKey.toLowerCase();
            if (existKey.equals(key)) return true;
        }
        return false;
    }

    /**
     * Check existence of config section element
     */
    public boolean exists(String key) {
        return exists(key, false);
    }

    /**
     * Remove config section element
     */
    public void remove(String key) {
        if (key == null || key.isEmpty()) return;
        if (super.containsKey(key)) super.remove(key);
        else if (this.containsKey(".")) {
            String[] keys = key.split("\\.", 2);
            if (super.get(keys[0]) instanceof ConfigSection section) {
                section.remove(keys[1]);
            }
        }
    }

    /**
     * Get all keys
     *
     * @param child - true = include child keys
     */
    public Set<String> getKeys(boolean child) {
        Set<String> keys = new LinkedHashSet<>();
        this.forEach((key, value) -> {
            keys.add(key);
            if (value instanceof ConfigSection) {
                if (child)
                    ((ConfigSection) value).getKeys(true).forEach(childKey -> keys.add(key + "." + childKey));
            }
        });
        return keys;
    }

    /**
     * Get all keys
     */
    public Set<String> getKeys() {
        return this.getKeys(true);
    }
}
