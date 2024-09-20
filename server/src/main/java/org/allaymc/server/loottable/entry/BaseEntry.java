package org.allaymc.server.loottable.entry;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.loottable.LootTableType;
import org.allaymc.api.loottable.condition.Condition;
import org.allaymc.api.loottable.condition.Conditions;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.Entry;

import java.util.ArrayList;
import java.util.List;

/**
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
