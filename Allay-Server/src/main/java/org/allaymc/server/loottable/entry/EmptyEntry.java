package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.loottable.LootTableType;
import org.allaymc.server.loottable.condition.Conditions;
import org.allaymc.server.loottable.context.Context;

import java.util.Set;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
public class EmptyEntry<CONTEXT_TYPE extends Context> extends BaseEntry<CONTEXT_TYPE> {
    public EmptyEntry(int weight, Conditions<CONTEXT_TYPE> conditions) {
        super(null, weight, conditions);
    }

    @Override
    public Set<ItemStack> loot(Context context) {
        return Set.of();
    }

    public static <CONTEXT_TYPE extends Context> EntryDeserializer<CONTEXT_TYPE> deserializer() {
        return new EmptyEntryDeserializer<>();
    }

    public static class EmptyEntryDeserializer<CONTEXT_TYPE extends Context> implements EntryDeserializer<CONTEXT_TYPE> {
        @Override
        public Entry<CONTEXT_TYPE> deserialize(JsonObject json, LootTableType<CONTEXT_TYPE> lootTableType) {
            var weight = 1;
            if (json.has("weight")) {
                weight = json.get("weight").getAsInt();
            }
            var conditions = parseConditions(json, lootTableType);
            return new EmptyEntry<>(weight, conditions);
        }
    }
}
