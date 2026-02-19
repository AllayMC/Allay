package org.allaymc.api.utils;

import com.google.gson.*;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A semantic version implementation that follows the Semantic Versioning 2.0.0 specification.
 *
 * @see <a href="https://semver.org/">Semantic Versioning</a>
 */
public record SemanticVersion(int major, int minor, int patch, @Nullable String perRelease,
                              @Nullable String build) implements Comparable<SemanticVersion> {

    public static SemanticVersion fromString(String versionStr) {
        var r = Pattern.compile("^(0|[1-9]\\d*)\\.(0|[1-9]\\d*)\\.(0|[1-9]\\d*)(?:-((?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\\.(?:0|[1-9]\\d*|\\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\\+([0-9a-zA-Z-]+(?:\\.[0-9a-zA-Z-]+)*))?$", Pattern.MULTILINE);
        Matcher matcher = r.matcher(versionStr);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid version string: " + versionStr);
        }
        var major = Integer.parseInt(matcher.group(1));
        var minor = Integer.parseInt(matcher.group(2));
        var patch = Integer.parseInt(matcher.group(3));
        var preRelease = matcher.group(4);
        var build = matcher.group(5);

        return new SemanticVersion(major, minor, patch, preRelease, build);
    }

    public String toVersionString() {
        var versionStr = String.format("%d.%d.%d", major, minor, patch);
        if (perRelease != null) {
            versionStr += "-" + perRelease;
        }
        if (build != null) {
            versionStr += "+" + build;
        }
        return versionStr;
    }

    @Override
    public int compareTo(@NonNull SemanticVersion o) {
        if (this.major != o.major) {
            return Integer.compare(this.major, o.major);
        }
        if (this.minor != o.minor) {
            return Integer.compare(this.minor, o.minor);
        }
        if (this.patch != o.patch) {
            return Integer.compare(this.patch, o.patch);
        }
        if (this.perRelease == null && o.perRelease != null) {
            return 1;
        }
        if (this.perRelease != null && o.perRelease == null) {
            return -1;
        }
        if (this.perRelease != null) {
            var thisPreReleaseParts = this.perRelease.split("\\.");
            var otherPreReleaseParts = o.perRelease.split("\\.");
            for (int i = 0; i < Math.max(thisPreReleaseParts.length, otherPreReleaseParts.length); i++) {
                if (i >= thisPreReleaseParts.length) {
                    return -1;
                }
                if (i >= otherPreReleaseParts.length) {
                    return 1;
                }
                var thisPart = thisPreReleaseParts[i];
                var otherPart = otherPreReleaseParts[i];
                var thisPartIsNumeric = thisPart.matches("\\d+");
                var otherPartIsNumeric = otherPart.matches("\\d+");
                if (thisPartIsNumeric && otherPartIsNumeric) {
                    var cmp = Integer.compare(Integer.parseInt(thisPart), Integer.parseInt(otherPart));
                    if (cmp != 0) {
                        return cmp;
                    }
                } else if (!thisPartIsNumeric && !otherPartIsNumeric) {
                    var cmp = thisPart.compareTo(otherPart);
                    if (cmp != 0) {
                        return cmp;
                    }
                } else {
                    return thisPartIsNumeric ? -1 : 1;
                }
            }
        }
        return 0;
    }

    public static class PackManifestSerializer implements JsonSerializer<SemanticVersion> {

        @Override
        public JsonElement serialize(SemanticVersion src, Type typeOfSrc, JsonSerializationContext context) {
            if(src.perRelease == null && src.build == null) {
                var json = new JsonArray();
                json.add(src.major);
                json.add(src.minor);
                json.add(src.patch);
                return json;
            } else {
                return new JsonPrimitive(src.toVersionString());
            }
        }
    }

    public static class PackManifestDeserializer implements JsonDeserializer<SemanticVersion> {

        @Override
        public SemanticVersion deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonArray() && (json.getAsJsonArray().size() == 3)) {
                var major = json.getAsJsonArray().get(0).getAsInt();
                var minor = json.getAsJsonArray().get(1).getAsInt();
                var patch = json.getAsJsonArray().get(2).getAsInt();
                return new SemanticVersion(major, minor, patch, null, null);
            } else if(json.isJsonPrimitive() && json.getAsJsonPrimitive().isString()) {
                return SemanticVersion.fromString(json.getAsString());
            } else {
                throw new JsonParseException("Invalid version format: " + json);
            }
        }
    }
}
