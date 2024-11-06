package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import org.allaymc.server.loottable.LootTableType;
import org.allaymc.server.loottable.context.Context;

/**
 * @author daoge_cmd
 */
public interface EntryDeserializer<CONTEXT_TYPE extends Context> {
    /**
     * Deserializes an {@link Entry} from a {@link JsonObject}.
     *
     * @param json          The {@link JsonObject} to deserialize from.
     * @param lootTableType The {@link LootTableType} of the loot table.
     *
     * @return A new {@link Entry} instance.
     */
    Entry<CONTEXT_TYPE> deserialize(JsonObject json, LootTableType<CONTEXT_TYPE> lootTableType);
}
