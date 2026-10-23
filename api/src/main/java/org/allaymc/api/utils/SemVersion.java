package org.allaymc.api.utils;

import com.google.common.base.Preconditions;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * {@code SemVersion} represents Minecraft-style version components without enforcing
 * nonnegative values or semantic-version ordering. Array and string conversions retain five
 * components, while the JSON adapter writes and reads only the first three, so revision and
 * build are not preserved by that JSON representation.
 *
 * @param major major version component
 * @param minor minor version component
 * @param patch patch version component
 * @param revision optional revision component
 * @param build optional build component
 */
public record SemVersion(int major, int minor, int patch, int revision, int build) {
    /**
     * Creates a version from the first three to five array elements. An empty array produces all
     * zeroes, lengths one and two are rejected, missing revision or build defaults to zero, and
     * elements after the fifth are ignored.
     */
    public static SemVersion from(int[] versions) {
        if (versions.length == 0) {
            return new SemVersion(0, 0, 0, 0, 0);
        }
        Preconditions.checkArgument(versions.length >= 3);
        var major = versions[0];
        var minor = versions[1];
        var patch = versions[2];
        var revision = versions.length >= 4 ? versions[3] : 0;
        var build = versions.length >= 5 ? versions[4] : 0;
        return new SemVersion(major, minor, patch, revision, build);
    }

    /**
     * Parses at least three dot-separated integer components without trimming them. Missing
     * revision or build defaults to zero, components after the fifth are ignored, and malformed
     * or empty consumed components fail integer parsing.
     */
    public static SemVersion from(String versionStr) {
        var versions = AllayStringUtils.fastSplit(versionStr, ".");
        Preconditions.checkArgument(versions.size() >= 3);
        var major = Integer.parseInt(versions.get(0));
        var minor = Integer.parseInt(versions.get(1));
        var patch = Integer.parseInt(versions.get(2));
        var revision = versions.size() >= 4 ? Integer.parseInt(versions.get(3)) : 0;
        var build = versions.size() >= 5 ? Integer.parseInt(versions.get(4)) : 0;
        return new SemVersion(major, minor, patch, revision, build);
    }

    /**
     * Returns all five version components in a new primitive integer array.
     */
    public int[] toArray() {
        return new int[]{major, minor, patch, revision, build};
    }

    /**
     * Returns all five version components in a new boxed integer array.
     */
    public Integer[] toBoxedArray() {
        return new Integer[]{major, minor, patch, revision, build};
    }

    /**
     * {@code Serializer} writes only the major, minor, and patch components as a JSON array.
     */
    public static class Serializer implements JsonSerializer<SemVersion> {

        /**
         * Writes a three-element JSON array containing {@code major}, {@code minor}, and {@code
         * patch}.
         * The revision and build components are omitted, so callers needing those values must
         * choose
         * another representation for a full round trip.
         */
        @Override
        public JsonElement serialize(SemVersion src, Type typeOfSrc, JsonSerializationContext context) {
            var json = new JsonArray();
            json.add(src.major);
            json.add(src.minor);
            json.add(src.patch);
            return json;
        }
    }

    /**
     * {@code Deserializer} reads three JSON version components and supplies zero revision and
     * build values.
     */
    public static class Deserializer implements JsonDeserializer<SemVersion> {

        /**
         * Reads the first three JSON array elements and constructs zero revision and build
         * components.
         * Additional elements are ignored, and missing elements or incompatible component values
         * fail
         * rather than producing the empty-array default.
         */
        @Override
        public SemVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            var array = json.getAsJsonArray();
            return new SemVersion(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt(), 0, 0);
        }
    }
}
