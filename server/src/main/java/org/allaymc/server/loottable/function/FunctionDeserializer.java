package org.allaymc.server.loottable.function;

import com.google.gson.JsonObject;

/**
 * @author daoge_cmd
 */
public interface FunctionDeserializer {
    /**
     * Deserializes a {@code JsonObject} into a {@code Function} object.
     *
     * @param json The JsonObject to deserialize.
     *
     * @return The deserialized {@code Function} object.
     */
    Function deserialize(JsonObject json);
}
