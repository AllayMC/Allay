package org.allaymc.api.utils;

import com.google.common.base.Preconditions;
import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * A semantic like version for Minecraft.
 * If you need a real semantic version, please use {@link SemanticVersion} instead.
 *
 * @see <a href="https://semver.org/">Semantic Versioning</a>
 */
public record SemVersion(int major, int minor, int patch, int revision, int build) {
    /**
     * Create a semantic version from an int[] array.
     * <p>
     * The length of the array must be bigger than 3.
     *
     * @param versions the version array
     *
     * @return the semantic version
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
     * Create a semantic version from a string.
     * <p>
     * The string must be in the format of "major.minor.patch[.revision.build]".
     *
     * @param versionStr the version string
     *
     * @return the semantic version
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

    public int[] toArray() {
        return new int[]{major, minor, patch, revision, build};
    }

    public Integer[] toBoxedArray() {
        return new Integer[]{major, minor, patch, revision, build};
    }

    public static class Serializer implements JsonSerializer<SemVersion> {

        @Override
        public JsonElement serialize(SemVersion src, Type typeOfSrc, JsonSerializationContext context) {
            var json = new JsonArray();
            json.add(src.major);
            json.add(src.minor);
            json.add(src.patch);
            return json;
        }
    }

    public static class Deserializer implements JsonDeserializer<SemVersion> {

        @Override
        public SemVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            var array = json.getAsJsonArray();
            return new SemVersion(array.get(0).getAsInt(), array.get(1).getAsInt(), array.get(2).getAsInt(), 0, 0);
        }
    }
}
