package org.allaymc.server.loottable.function;

import com.google.gson.JsonObject;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
public interface FunctionDeserializer {
    Function deserialize(JsonObject json);
}
