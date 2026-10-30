package org.allaymc.api.utils.config;

import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * {@code ConfigSection} stores insertion-ordered configuration entries and supports dotted
 * path access alongside ordinary map operations. Exact stored keys take precedence during
 * lookup and removal, so a literal dotted key can coexist with a nested path of the same
 * spelling. Inherited map operations keep literal-key behavior and do not apply the path
 * normalization performed by {@code set}.
 *
 * <p>
 * Copying depends on the selected constructor: the linked-map overload converts nested linked
 * maps, while the general-map overload also converts other nested maps. Lists receive new
 * containers but only direct linked-map elements are converted; other mutable elements can
 * remain shared. Generic defaults represent missing paths rather than validating present
 * types, so typed getters can still throw for incompatible or {@code null} stored values. Dotted
 * lookup can inspect raw maps through temporary conversion, while setting and removing nested
 * paths use the more specific section rules documented on those operations.
 *
 * @author fromgate | daoge_cmd
 */
public class ConfigSection extends LinkedHashMap<String, Object> {

    /**
     * Creates an empty insertion-ordered configuration section for direct and dotted keys.
     */
    public ConfigSection() {
        super();
    }

    /**
     * Creates an empty section and assigns one entry through {@code set}. Dots create nested
     * sections rather than a literal dotted key, and the supplied value reference is retained
     * without general deep copying.
     */
    public ConfigSection(String key, Object value) {
        this();
        this.set(key, value);
    }

    /**
     * Copies entries from a linked map, treating {@code null} or empty input as an empty section.
     * Nested linked maps become sections and list containers are copied, but other map types and
     * mutable list elements can remain shared.
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
     * Copies entries from a general map, treating {@code null} or empty input as an empty section.
     * Direct nested maps become sections and list containers are copied; list conversion still
     * retains non-linked-map elements and other mutable values.
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

    /**
     * Copies the list container and converts direct linked-map elements into sections. Other
     * elements, including nested lists and non-linked maps, retain their original references,
     * so the result is not a recursively independent copy.
     */
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
     * Returns a shallow mutable linked-map copy of all direct entries. The copied map has
     * independent entry storage, but its nested sections, collections, and other mutable values
     * retain the references held by this section.
     */
    @NotNull
    public Map<String, Object> getAllMap() {
        return new LinkedHashMap<>(this);
    }

    /**
     * Copies this section through the linked-map constructor. Nested linked-map sections and
     * list containers are structurally copied, while other nested maps, list elements, and mutable
     * objects can still remain shared with the original.
     */
    @NotNull
    public ConfigSection getAll() {
        return new ConfigSection(this);
    }

    /**
     * Returns the exact-key or dotted-path value, using {@code null} for missing paths or an empty
     * or {@code null} key. The returned value can be a shared mutable reference, and an exact literal
     * key takes precedence over traversal.
     */
    public Object get(String key) {
        return this.get(key, null);
    }

    /**
     * Returns a direct value first, then traverses a dotted path when no exact key exists.
     * {@code null} or empty keys and missing path components return {@code defaultValue}; a present {@code null}
     * value remains {@code null} rather than selecting that default.
     *
     * <p>
     * A section-shaped default causes a present raw map to be converted to a new section, but
     * other incompatible present types are returned unchecked rather than rejected here.
     * Traversing
     * a raw map uses a temporary section without installing it into the original map, so mutable
     * values and later mutations need not share the same structural conversion.
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public <T> T get(String key, T defaultValue) {
        if (key == null || key.isEmpty()) {
            return defaultValue;
        }

        // Literal dotted keys can coexist with nested entries. Resolve the complete key
        // first so a stored literal value is not silently replaced by traversal of its prefix.
        if (super.containsKey(key)) {
            Object value = super.get(key);
            if (defaultValue != null && !defaultValue.getClass().isInstance(value)) {
                if (value instanceof Map map && defaultValue instanceof ConfigSection) {
                    return (T) new ConfigSection(map);
                }
            }
            return (T) value;
        }

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
     * Sets a dotted path by recursively creating section-valued parents. Existing
     * {@code ConfigSection} parents are reused, but any other intermediate value, including a
     * plain map, is replaced with an empty section before the new child is installed.
     *
     * <p>
     * This operation always interprets dots rather than checking an existing literal dotted key.
     * Callers use inherited {@code put} when they need such a literal key, and they must preserve
     * or convert raw intermediate maps themselves if their existing entries are needed after the
     * path update. The final value reference is retained without copying.
     */
    public void set(String key, Object value) {
        String[] subKeys = key.split("\\.", 2);
        if (subKeys.length > 1) {
            ConfigSection childSection;
            Object existing = super.get(subKeys[0]);
            if (existing instanceof ConfigSection existingSection) {
                childSection = existingSection;
            } else {
                // Only ConfigSection parents participate in path mutation. Replacing a
                // plain map here discards its old children rather than converting them into this path.
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
     * Returns whether path lookup resolves to an existing configuration-section value.
     */
    public boolean isSection(String key) {
        return this.get(key) instanceof ConfigSection;
    }

    /**
     * Returns the requested section, using a new empty section for a missing path. Existing
     * sections remain shared, raw maps can be copied into sections, and incompatible stored values
     * or present {@code null} values can still violate the apparent section result.
     */
    @NotNull
    public ConfigSection getSection(String key) {
        return this.get(key, new ConfigSection());
    }

    /**
     * Returns the root's section-valued entries through a structural root copy. The result has
     * independent direct entry storage, but its copied sections follow constructor-specific
     * sharing
     * rules rather than a general guarantee of deep independence.
     */
    @NotNull
    public ConfigSection getSections() {
        return getSections(null);
    }

    /**
     * Selects section-valued children into a new section. A {@code null} or empty path first copies the
     * root structurally; a named existing section supplies its child references directly, so the
     * result can share mutable child sections with that parent.
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
     * Returns the stored number converted to {@code int}, using zero when the path is absent.
     * Present values must be non-{@code null} {@code Number} instances; strings are not parsed and
     * incompatible types or stored nulls still fail.
     */
    public int getInt(String key) {
        return this.getInt(key, 0);
    }

    /**
     * Returns the stored number's {@code int} value, or {@code defaultValue} for an absent
     * path. Numeric conversion follows {@code Number} semantics, and a present nonnumeric or
     * {@code null} value fails rather than selecting the default.
     */
    public int getInt(String key, int defaultValue) {
        return this.get(key, (Number) defaultValue).intValue();
    }

    /**
     * Returns whether path lookup yields a boxed {@code Integer} value.
     */
    public boolean isInt(String key) {
        return get(key) instanceof Integer;
    }

    /**
     * Returns the stored number converted to {@code long}, using zero when the path is absent.
     * Present values must be non-{@code null} {@code Number} instances; strings are not parsed and
     * incompatible types or stored nulls still fail.
     */
    public long getLong(String key) {
        return this.getLong(key, 0);
    }

    /**
     * Returns the stored number's {@code long} value, or {@code defaultValue} for an absent
     * path. Numeric conversion follows {@code Number} semantics, and a present nonnumeric or
     * {@code null} value fails rather than selecting the default.
     */
    public long getLong(String key, long defaultValue) {
        return this.get(key, (Number) defaultValue).longValue();
    }

    /**
     * Returns whether path lookup yields a boxed {@code Long} value.
     */
    public boolean isLong(String key) {
        return get(key) instanceof Long;
    }

    /**
     * Returns the stored number converted to {@code double}, using zero when the path is absent.
     * Present values must be non-{@code null} {@code Number} instances; strings are not parsed and
     * incompatible types or stored nulls still fail.
     */
    public double getDouble(String key) {
        return this.getDouble(key, 0);
    }

    /**
     * Returns the stored number's {@code double} value, or {@code defaultValue} for an absent
     * path. Numeric conversion follows {@code Number} semantics, and a present nonnumeric or
     * {@code null} value fails rather than selecting the default.
     */
    public double getDouble(String key, double defaultValue) {
        return this.get(key, (Number) defaultValue).doubleValue();
    }

    /**
     * Returns whether path lookup yields a boxed {@code Double} value.
     */
    public boolean isDouble(String key) {
        return get(key) instanceof Double;
    }

    /**
     * Returns {@code String.valueOf} of the lookup result, using the empty string when absent.
     * Any present value can supply its text representation, including a stored {@code null}
     * becoming the literal {@code "null"}.
     */
    @NotNull
    public String getString(String key) {
        return this.getString(key, "");
    }

    /**
     * Returns {@code String.valueOf} of the stored value or supplied missing-path default.
     * The operation can stringify non-string values, and either a stored {@code null} or a {@code null} default
     * becomes the literal string {@code "null"}.
     */
    public String getString(String key, String defaultValue) {
        Object result = this.get(key, defaultValue);
        return String.valueOf(result);
    }

    /**
     * Returns whether path lookup yields a stored string value without conversion.
     */
    public boolean isString(String key) {
        return get(key) instanceof String;
    }

    /**
     * Returns a stored boolean, using {@code false} only for an absent path. Strings and
     * numbers are not parsed as boolean values, and an incompatible or {@code null} present value can
     * fail during casting or unboxing.
     */
    public boolean getBoolean(String key) {
        return this.getBoolean(key, false);
    }

    /**
     * Returns a stored boolean, using {@code defaultValue} only for an absent path. Strings and
     * numbers are not parsed as boolean values, and an incompatible or {@code null} present value can
     * fail during casting or unboxing.
     */
    public boolean getBoolean(String key, boolean defaultValue) {
        return this.get(key, defaultValue);
    }

    /**
     * Returns whether path lookup yields a stored boolean without converting other values.
     */
    public boolean isBoolean(String key) {
        return get(key) instanceof Boolean;
    }

    // ==================== List Getters ====================

    /**
     * Returns the stored list, using {@code null} only for an absent path. The stored or
     * fallback reference is returned without copying, and an incompatible present value fails
     * casting rather than becoming an empty list.
     */
    public List<?> getList(String key) {
        return this.getList(key, null);
    }

    /**
     * Returns the stored list, using {@code defaultList} only for an absent path. The stored or
     * fallback reference is returned without copying, and an incompatible present value fails
     * casting rather than becoming an empty list.
     */
    public List<?> getList(String key, List<?> defaultList) {
        return this.get(key, defaultList);
    }

    /**
     * Returns whether path lookup yields a stored list without element validation.
     */
    public boolean isList(String key) {
        return get(key) instanceof List;
    }

    /**
     * Returns a new mutable list of strings from supported scalar elements. Strings, numbers,
     * booleans, and characters are converted; other element types are omitted. Missing or {@code null}
     * lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
     * Returns a new mutable list of integers from supported scalar elements. Numeric strings are
     * parsed, characters use their code units, and other numbers use integer conversion; invalid
     * strings and unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
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
     * Returns a new mutable list of booleans from supported elements. Boolean values and
     * case-insensitive {@code "true"}/{@code "false"} strings are retained; numeric and other elements are omitted.
     * Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
     * Returns a new mutable list of doubles from supported scalar elements. Numeric strings are
     * parsed, characters use their code units, and other numbers use double conversion; invalid
     * strings and unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
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
     * Returns a new mutable list of floats from supported scalar elements. Numeric strings are
     * parsed, characters use their code units, and other numbers use float conversion; invalid
     * strings and unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
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
     * Returns a new mutable list of longs from supported scalar elements. Numeric strings are
     * parsed, characters use their code units, and other numbers use long conversion; invalid
     * strings and unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
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
     * Returns a new mutable list of bytes from supported scalar elements. Numeric strings are
     * parsed, characters and numbers use narrowing byte conversion, and invalid strings or
     * unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
                }
            } else if (object instanceof Character c) {
                // Narrowing keeps only the low eight bits of the character code unit.
                result.add((byte) c.charValue());
            } else if (object instanceof Number n) {
                result.add(n.byteValue());
            }
        }
        return result;
    }

    /**
     * Returns a new mutable list of characters from supported scalar elements. Single-character
     * strings and characters are retained, while numbers use narrowed integer code units and other
     * elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                // Character conversion retains only the low sixteen bits of the integer value.
                result.add((char) n.intValue());
            }
        }
        return result;
    }

    /**
     * Returns a new mutable list of shorts from supported scalar elements. Numeric strings are
     * parsed, characters and numbers use narrowing short conversion, and invalid strings or
     * unsupported elements are omitted. Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
                    // One invalid numeric string does not invalidate the whole selected list.
                    // Omit that element while preserving the relative order of successfully converted values.
                }
            } else if (object instanceof Character c) {
                // Narrowing reinterprets the sixteen-bit character code as a signed short value.
                result.add((short) c.charValue());
            } else if (object instanceof Number n) {
                result.add(n.shortValue());
            }
        }
        return result;
    }

    /**
     * Returns a new mutable list of map elements from the stored list. The new list retains each
     * selected map reference, so changes to map contents remain shared with the configuration.
     * Missing or {@code null} lists produce
     * empty results, while a present non-list value still fails before element conversion.
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
     * Returns whether the exact supplied path appears in the expanded key snapshot. Only
     * section-valued descendants are expanded, so paths inside raw nested maps can be readable
     * without being reported by this operation.
     */
    public boolean exists(String key) {
        return exists(key, false);
    }

    /**
     * Returns whether an expanded key matches, optionally using whole-path lowercase comparison.
     * Case-insensitive mode requires a non-{@code null} search key, and inherited map operations can
     * insert {@code null} stored keys that cause either mode to fail during comparison.
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
     * Removes an exact direct key before attempting dotted-path traversal. {@code null} or empty keys
     * make no change, and a direct literal dotted key is removed without touching a nested path
     * that happens to have the same spelling.
     *
     * <p>
     * When no direct key exists, removal descends only through {@code ConfigSection} values.
     * Raw maps are not converted for mutation, so a path readable through temporary-map lookup
     * can remain unchanged by this operation. Removing the final child does not prune its empty
     * parent section.
     */
    public void remove(String key) {
        if (key == null || key.isEmpty()) {
            return;
        }

        if (super.containsKey(key)) {
            super.remove(key);
            return;
        }

        // Direct-key removal already had precedence. Descend only through actual sections
        // so temporary conversion of a plain map cannot suggest that its stored children were mutated.
        if (key.contains(".")) {
            String[] keys = key.split("\\.", 2);
            Object value = super.get(keys[0]);
            if (value instanceof ConfigSection section) {
                section.remove(keys[1]);
            }
        }
    }

    /**
     * Returns a mutable snapshot of direct keys and section-descendant paths. Traversal visits
     * each section key before its descendants, but earlier literal dotted keys can already occupy
     * a descendant's position in the insertion-ordered result and suppress that duplicate path.
     */
    @NotNull
    public Set<String> getKeys() {
        return this.getKeys(true);
    }

    /**
     * Returns a mutable insertion-ordered key snapshot, optionally expanding section descendants
     * with dotted prefixes. Plain nested maps are not traversed, and changing the returned set
     * does not change this section or its children.
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
