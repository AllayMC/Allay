package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.loottable.LootTableType;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.EntryDeserializer;
import org.allaymc.api.loottable.function.Function;
import org.allaymc.api.loottable.function.Functions;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class ItemEntry<CONTEXT_TYPE extends Context> extends BaseEntry<CONTEXT_TYPE> {
    protected Functions functions;
    protected ItemType<?> itemType;

    public ItemEntry(String name, int weight, Conditions<CONTEXT_TYPE> conditions, Functions functions) {
        super(name, weight, conditions);
        this.functions = functions;
        this.itemType = Registries.ITEMS.get(new Identifier(name));
    }

    protected static Functions parseFunctions(JsonObject json, LootTableType<?> lootTableType) {
        List<Function> list = new ArrayList<>();
        if (json.has("functions")) {
            for (var element : json.get("functions").getAsJsonArray()) {
                var functionObj = element.getAsJsonObject();
                var name = functionObj.get("function").getAsString();
                list.add(lootTableType.getFunction(name, functionObj));
            }
        }
        return new Functions(list);
    }

    public static <CONTEXT_TYPE extends Context> EntryDeserializer<CONTEXT_TYPE> deserializer() {
        return new Deserializer<>();
    }

    @Override
    public Set<ItemStack> loot(Context context) {
        var item = itemType.createItemStack();
        functions.apply(item);
        return Set.of(item);
    }

    public static class Deserializer<CONTEXT_TYPE extends Context> implements EntryDeserializer<CONTEXT_TYPE> {
        @Override
        public ItemEntry<CONTEXT_TYPE> deserialize(JsonObject json, LootTableType<CONTEXT_TYPE> lootTableType) {
            var name = json.get("name").getAsString();
            var weight = 1;
            if (json.has("weight")) {
                weight = json.get("weight").getAsInt();
            }
            var conditions = parseConditions(json, lootTableType);
            var functions = parseFunctions(json, lootTableType);
            return new ItemEntry<>(name, weight, conditions, functions);
        }
    }
}
