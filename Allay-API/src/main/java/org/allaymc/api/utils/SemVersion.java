package org.allaymc.api.utils;

import com.google.common.base.Preconditions;
import com.google.gson.*;

import java.lang.reflect.Type;

public record SemVersion(int major, int minor, int patch, int revision, int build) {
    public static SemVersion from(int[] versions) {
        if (versions.length == 0) {
            return new SemVersion(0, 0, 0, 0, 0);
        }
        Preconditions.checkArgument(versions.length == 5);
        return new SemVersion(versions[0], versions[1], versions[2], versions[3], versions[4]);
    }

    public int[] toArray() {
        return new int[]{major, minor, patch, revision, build};
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
