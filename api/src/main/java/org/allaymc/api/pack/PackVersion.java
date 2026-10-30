package org.allaymc.api.pack;


import com.google.gson.*;
import org.semver4j.Semver;

/**
 * {@code PackVersion} represents a semantic pack version together with its
 * manifest JSON representation. A manifest can spell the same version as a
 * string or a three-element numeric array. The stored {@code type} records which
 * representation the parser received, allowing serialization to keep that
 * representation instead of converting every version into a string.
 *
 * <p>
 * Array versions retain only major, minor, and patch numbers, while string
 * versions use the semantic-version text accepted by {@code Semver}. The
 * manifest registers the nested adapters to read and write these forms. This
 * class is public for adapter access; its package-private constructors keep
 * direct construction within the pack API package.
 *
 * @author harry-xi
 */
public class PackVersion extends Semver {
    /**
     * {@code type} records the source representation. {@code Serializer} reads
     * it to choose a numeric array or semantic-version string when writing JSON.
     */
    private final PackVersionType type;

    /**
     * Creates a semantic version carrying the supplied JSON representation kind.
     * {@code Semver} parses {@code version} before the kind is retained; callers
     * must choose the representation that later serialization should emit.
     */
    PackVersion(String version, PackVersionType type) {
        super(version);
        this.type = type;
    }

    /**
     * Creates a version whose manifest representation is a JSON string.
     * {@code Semver} parses the supplied text, and serialization later emits its
     * semantic-version text rather than constructing a numeric array.
     */
    PackVersion(String version) {
        this(version, PackVersionType.Str);
    }

    /**
     * Creates a version whose manifest representation is a three-element array.
     * The constructor formats {@code major}, {@code minor}, and {@code patch}
     * as semantic-version text before parsing and retaining the array kind.
     */
    PackVersion(int major, int minor, int patch) {
        this(String.format("%d.%d.%d", major, minor, patch), PackVersionType.Arr);
    }

    /**
     * {@code PackVersionType} distinguishes the JSON forms retained for version serialization.
     * The adapters use this distinction when reading and writing manifest versions.
     */
    enum PackVersionType {
        /** Three-element numeric array. */
        Arr,
        /** Semantic version string. */
        Str
    }

    /**
     * {@code Serializer} reconstructs the manifest representation retained by a version.
     */
    protected static class Serializer implements JsonSerializer<PackVersion> {
        /**
         * Writes a version using the JSON form recorded when it was created.
         * {@code PackVersionType.Arr} emits major, minor, and patch as separate
         * numbers; other kinds emit the semantic-version string.
         *
         * <p>
         * The stored kind preserves the distinction between array and string
         * manifest versions even when their numeric versions are equal. Array
         * output contains exactly three numeric components and cannot represent
         * prerelease or build suffixes, so callers creating array kinds must
         * choose data that those components can express.
         */
        @Override
        public JsonElement serialize(PackVersion src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
            if(src.type == PackVersionType.Arr) {
                // Retain numeric array form because manifest consumers can
                // distinguish it from a string containing the same version.
                var arr = new JsonArray();
                arr.add(src.getMajor());
                arr.add(src.getMinor());
                arr.add(src.getPatch());
                return arr;
            } else {
                return new JsonPrimitive(src.toString());
            }
        }
    }

    /**
     * {@code Deserializer} parses supported manifest version forms and records their kind.
     */
    protected static class Deserializer implements JsonDeserializer<PackVersion> {
        /**
         * Reads a three-element array or a semantic-version string.
         * Arrays must contain exactly three elements, despite the exception
         * message referring to a minimum, and their values are read through
         * {@code getAsInt}.
         *
         * <p>
         * The constructed version records the input form so its serializer can
         * retain that form during a JSON round trip. Invalid string versions
         * are wrapped in {@code JsonParseException}; unsupported JSON forms and
         * incorrect array sizes also produce that exception, while array value
         * conversion failures can propagate directly.
         */
        @Override
        public PackVersion deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonArray()) {
                // Record the array form together with its components so a
                // later manifest serialization does not replace it with text.
                var arr = json.getAsJsonArray();
                // Numeric manifest versions contain only major, minor, and patch components.
                if(arr.size() != 3) {
                    throw new JsonParseException("Version array must have at least 3 elements");
                }
                var major = arr.get(0).getAsInt();
                var minor = arr.get(1).getAsInt();
                var patch = arr.get(2).getAsInt();
                return new PackVersion(major, minor, patch);
            } else if(json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
                try {
                    return new PackVersion(json.getAsJsonPrimitive().getAsString());
                } catch (Exception e) {
                    // Preserve the rejected string in the parsing error so
                    // manifest loading can identify the unusable version text.
                    throw new JsonParseException("Invalid version string: " + json.getAsString(), e);
                }
            } else {
                throw new JsonParseException("Invalid version format: " + json);
            }
        }
    }
}
