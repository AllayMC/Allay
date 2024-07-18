package org.allaymc.server.block.type;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.server.loottable.LootTable;
import org.allaymc.server.loottable.LootTableType;
import org.allaymc.server.loottable.Pool;
import org.allaymc.server.loottable.Rolls;
import org.allaymc.server.loottable.condition.Condition;
import org.allaymc.server.loottable.condition.Conditions;
import org.allaymc.server.loottable.condition.MatchToolCondition;
import org.allaymc.server.loottable.context.BreakBlockContext;
import org.allaymc.server.loottable.entry.EmptyEntry;
import org.allaymc.server.loottable.entry.Entries;
import org.allaymc.server.loottable.entry.Entry;
import org.allaymc.server.loottable.entry.ItemEntry;
import org.allaymc.server.loottable.function.SetCountFunction;
import org.allaymc.server.loottable.function.SetDamageFunction;
import org.allaymc.server.loottable.function.SetDataFunction;
import org.allaymc.server.loottable.function.SetNameFunction;
import org.allaymc.server.loottable.roll.RangedRolls;
import org.allaymc.server.loottable.roll.SimpleRolls;
import org.allaymc.server.utils.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
@Slf4j
public final class BlockLootTable {
    public static final LootTableType<BreakBlockContext> BLOCK_LOOT_TABLE_TYPE = LootTableType
            .<BreakBlockContext>builder()
            .supportFunction("set_count", SetCountFunction.deserializer())
            .supportFunction("set_damage", SetDamageFunction.deserializer())
            .supportFunction("set_data", SetDataFunction.deserializer())
            .supportFunction("set_name", SetNameFunction.deserializer())
            .supportEntry("item", ItemEntry.deserializer())
            .supportEntry("empty", EmptyEntry.deserializer())
            .supportCondition("match_tool", MatchToolCondition.deserializer())
            .build();

    public static final Map<VanillaBlockId, LootTable<BreakBlockContext>> BLOCK_LOOT_TABLES = new EnumMap<>(VanillaBlockId.class);

    public static void init() {
        readFrom(new InputStreamReader(new BufferedInputStream(ResourceUtils.getResource("loot_tables/blocks.json"))));
    }

    public static void readFrom(InputStreamReader reader) {
        JsonParser.parseReader(reader).getAsJsonObject().entrySet().forEach(entry -> {
            List<Pool<BreakBlockContext>> pools = new ArrayList<>();

            for (var element : entry.getValue().getAsJsonArray()) {
                var poolObj = element.getAsJsonObject();
                var conditions = parseConditions(poolObj);
                var rolls = parseRolls(poolObj);
                var entries = parseEntries(poolObj);
                pools.add(new Pool<>(conditions, rolls, entries));
            }

            try {
                var regex = Pattern.compile(entry.getKey());
                var blocks = Arrays.stream(VanillaBlockId.values())
                        .map(VanillaBlockId::getIdentifier)
                        .toList();
                for (var block : blocks) {
                    if (regex.matcher(block.toString()).matches()) {
                        var blockId = VanillaBlockId.fromIdentifier(block);
                        BLOCK_LOOT_TABLES.put(blockId, new LootTable<>(BLOCK_LOOT_TABLE_TYPE, pools));
                    }
                }
            } catch (PatternSyntaxException exception) {
                throw new RuntimeException("invalid block regex: ", exception);
            }
        });
    }

    private static Conditions<BreakBlockContext> parseConditions(JsonObject poolObj) {
        List<Condition<BreakBlockContext>> conditions = new ArrayList<>();
        if (poolObj.has("conditions")) {
            for (var element : poolObj.get("conditions").getAsJsonArray()) {
                conditions.add(parseCondition(element.getAsJsonObject()));
            }
        }
        return new Conditions<>(conditions);
    }

    private static Condition<BreakBlockContext> parseCondition(JsonObject conditionObj) {
        var name = conditionObj.get("condition").getAsString();
        return BLOCK_LOOT_TABLE_TYPE.getCondition(name, conditionObj);
    }

    private static Entries<BreakBlockContext> parseEntries(JsonObject poolObj) {
        List<Entry<BreakBlockContext>> entries = new ArrayList<>();
        for (var element : poolObj.get("entries").getAsJsonArray()) {
            entries.add(parseEntry(element.getAsJsonObject()));
        }
        return new Entries<>(entries);
    }

    private static Entry<BreakBlockContext> parseEntry(JsonObject entryObj) {
        var type = entryObj.get("type").getAsString();
        return BLOCK_LOOT_TABLE_TYPE.getEntry(type, entryObj);
    }

    private static Rolls parseRolls(JsonObject poolObj) {
        Rolls rolls;
        var rollsElement = poolObj.get("rolls");
        if (rollsElement.isJsonObject()) {
            var rollsObj = rollsElement.getAsJsonObject();
            var min = rollsObj.get("min").getAsInt();
            Preconditions.checkState(min >= 0, "min shouldn't be negative");
            var max = rollsObj.get("max").getAsInt();
            Preconditions.checkState(max >= 0, "max shouldn't be negative");
            Preconditions.checkState(min < max, "max must be greater than min");
            rolls = new RangedRolls(min, max);
        } else {
            var num = rollsElement.getAsInt();
            Preconditions.checkState(num >= 0, "rolls shouldn't be negative");
            rolls = new SimpleRolls(num);
        }
        return rolls;
    }

    public static LootTable<BreakBlockContext> getLootTable(VanillaBlockId blockId) {
        return BLOCK_LOOT_TABLES.get(blockId);
    }
}
