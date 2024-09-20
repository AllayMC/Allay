package org.allaymc.server.loottable.function;

import com.google.gson.JsonObject;

/**
 * @author daoge_cmd
 */
public interface FunctionDeserializer {
    Function deserialize(JsonObject json);
}
