package org.allaymc.server.loottable;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.allaymc.server.loottable.condition.Condition;
import org.allaymc.server.loottable.condition.ConditionDeserializer;
import org.allaymc.server.loottable.context.Context;
import org.allaymc.server.loottable.entry.Entry;
import org.allaymc.server.loottable.entry.EntryDeserializer;
import org.allaymc.server.loottable.function.Function;
import org.allaymc.server.loottable.function.FunctionDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class LootTableType<CONTEXT_TYPE extends Context> {
    protected Map<String, FunctionDeserializer> functions;
    protected Map<String, ConditionDeserializer<CONTEXT_TYPE>> conditions;
    protected Map<String, EntryDeserializer<CONTEXT_TYPE>> entries;

    public static <CONTEXT_TYPE extends Context> LootTableTypeBuilder<CONTEXT_TYPE> builder() {
        return new LootTableTypeBuilder<>();
    }

    public Function getFunction(String name, JsonObject json) {
        return functions.get(name).deserialize(json);
    }

    public Condition<CONTEXT_TYPE> getCondition(String name, JsonObject json) {
        return conditions.get(name).deserialize(json);
    }

    public Entry<CONTEXT_TYPE> getEntry(String type, JsonObject json) {
        return entries.get(type).deserialize(json, this);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class LootTableTypeBuilder<CONTEXT_TYPE extends Context> {
        private final Map<String, FunctionDeserializer> functions = new HashMap<>();
        private final Map<String, ConditionDeserializer<CONTEXT_TYPE>> conditions = new HashMap<>();
        private final Map<String, EntryDeserializer<CONTEXT_TYPE>> entries = new HashMap<>();

        public LootTableTypeBuilder<CONTEXT_TYPE> supportFunction(String name, FunctionDeserializer deserializer) {
            this.functions.putIfAbsent(name, deserializer);
            return this;
        }

        public LootTableTypeBuilder<CONTEXT_TYPE> supportCondition(String name, ConditionDeserializer<CONTEXT_TYPE> deserializer) {
            this.conditions.putIfAbsent(name, deserializer);
            return this;
        }

        public LootTableTypeBuilder<CONTEXT_TYPE> supportEntry(String type, EntryDeserializer<CONTEXT_TYPE> deserializer) {
            this.entries.putIfAbsent(type, deserializer);
            return this;
        }

        public LootTableType<CONTEXT_TYPE> build() {
            return new LootTableType<>(this.functions, this.conditions, this.entries);
        }
    }
}
