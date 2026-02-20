package org.allaymc.api.pack;


import com.google.gson.*;
import org.semver4j.Semver;

/**
 * This class is not for public use! It has been mark as public just for the convenience of Gson serialization and deserialization.
 * A version for pack manifest, which store the version in either an array format or a string format.
 *
 * @author harry-xi
 */
public class PackVersion extends Semver {
    private final PackVersionType type;

    PackVersion(String version, PackVersionType type) {
        super(version);
        this.type = type;
    }

    PackVersion(String version) {
        this(version, PackVersionType.Arr);
    }

    PackVersion(int major, int minor, int patch) {
        this( String.format("%d.%d.%d", major, minor, patch), PackVersionType.Arr);
    }

    enum PackVersionType {
        Arr,
        Str
    }

    protected static class Serializer implements JsonSerializer<PackVersion> {
        @Override
        public JsonElement serialize(PackVersion src, java.lang.reflect.Type typeOfSrc, JsonSerializationContext context) {
            if(src.type == PackVersionType.Arr) {
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

    protected static class Deserializer implements JsonDeserializer<PackVersion> {
        @Override
        public PackVersion deserialize(JsonElement json, java.lang.reflect.Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(json.isJsonArray()) {
                var arr = json.getAsJsonArray();
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
                    throw new JsonParseException("Invalid version string: " + json.getAsString(), e);
                }
            } else {
                throw new JsonParseException("Invalid version format: " + json);
            }
        }
    }
}
