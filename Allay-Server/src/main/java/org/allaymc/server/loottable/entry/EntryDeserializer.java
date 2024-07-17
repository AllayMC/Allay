package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import org.allaymc.server.loottable.LootTableType;
import org.allaymc.server.loottable.context.Context;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
public interface EntryDeserializer<CONTEXT_TYPE extends Context> {
    Entry<CONTEXT_TYPE> deserialize(JsonObject json, LootTableType<CONTEXT_TYPE> lootTableType);
}
