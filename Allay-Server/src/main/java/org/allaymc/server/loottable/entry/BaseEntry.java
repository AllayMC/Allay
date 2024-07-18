package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.server.loottable.LootTableType;
import org.allaymc.server.loottable.condition.Condition;
import org.allaymc.server.loottable.condition.Conditions;
import org.allaymc.server.loottable.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public abstract class BaseEntry<CONTEXT_TYPE extends Context> implements Entry<CONTEXT_TYPE> {
    protected String name;
    protected int weight;
    protected Conditions<CONTEXT_TYPE> conditions;

    protected static <CONTEXT_TYPE extends Context> Conditions<CONTEXT_TYPE> parseConditions(JsonObject json, LootTableType<CONTEXT_TYPE> lootTableType) {
        List<Condition<CONTEXT_TYPE>> list = new ArrayList<>();
        if (json.has("conditions")) {
            for (var element : json.get("conditions").getAsJsonArray()) {
                var conditionObj = element.getAsJsonObject();
                var name = conditionObj.get("condition").getAsString();
                list.add(lootTableType.getCondition(name, conditionObj));
            }
        }
        return new Conditions<>(list);
    }
}
