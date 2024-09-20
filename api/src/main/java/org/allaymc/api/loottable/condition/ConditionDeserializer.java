package org.allaymc.api.loottable.condition;

import com.google.gson.JsonObject;
import org.allaymc.api.loottable.context.Context;

/**
 * @author daoge_cmd
 */
public interface ConditionDeserializer<CONTEXT_TYPE extends Context> {

    /**
     * Deserializes a {@link JsonObject} into a {@link Condition} object.
     *
     * @param json The {@link JsonObject} to deserialize.
     *
     * @return The deserialized {@link Condition} object.
     */
    Condition<CONTEXT_TYPE> deserialize(JsonObject json);
}
