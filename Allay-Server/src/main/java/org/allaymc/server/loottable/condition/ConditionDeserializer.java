package org.allaymc.server.loottable.condition;

import com.google.gson.JsonObject;
import org.allaymc.server.loottable.context.Context;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
public interface ConditionDeserializer<CONTEXT_TYPE extends Context> {
    Condition<CONTEXT_TYPE> deserialize(JsonObject json);
}
