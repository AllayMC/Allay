package org.allaymc.api.loottable;

import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.allaymc.api.loottable.condition.Condition;
import org.allaymc.api.loottable.condition.ConditionDeserializer;
import org.allaymc.api.loottable.context.Context;
import org.allaymc.api.loottable.entry.Entry;
import org.allaymc.api.loottable.entry.EntryDeserializer;
import org.allaymc.api.loottable.function.Function;
import org.allaymc.api.loottable.function.FunctionDeserializer;

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

    public static <CONTEXT_TYPE extends Context> Builder<CONTEXT_TYPE> builder() {
        return new Builder<>();
    }

    /**
     * Gets the function from the given name and JsonObject.
     *
     * @param name The name of the function.
     * @param json The JsonObject containing the function data.
     *
     * @return The function.
     */
    public Function getFunction(String name, JsonObject json) {
        return functions.get(name).deserialize(json);
    }

    /**
     * Gets the condition from the given name and JsonObject.
     *
     * @param name The name of the condition.
     * @param json The JsonObject containing the condition data.
     *
     * @return The condition.
     */
    public Condition<CONTEXT_TYPE> getCondition(String name, JsonObject json) {
        return conditions.get(name).deserialize(json);
    }

    /**
     * Gets the entry from the given type and JsonObject.
     *
     * @param type The type of the entry.
     * @param json The JsonObject containing the entry data.
     *
     * @return The entry.
     */
    public Entry<CONTEXT_TYPE> getEntry(String type, JsonObject json) {
        return entries.get(type).deserialize(json, this);
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder<CONTEXT_TYPE extends Context> {
        private final Map<String, FunctionDeserializer> functions = new HashMap<>();
        private final Map<String, ConditionDeserializer<CONTEXT_TYPE>> conditions = new HashMap<>();
        private final Map<String, EntryDeserializer<CONTEXT_TYPE>> entries = new HashMap<>();

        public Builder<CONTEXT_TYPE> supportFunction(String name, FunctionDeserializer deserializer) {
            this.functions.putIfAbsent(name, deserializer);
            return this;
        }

        public Builder<CONTEXT_TYPE> supportCondition(String name, ConditionDeserializer<CONTEXT_TYPE> deserializer) {
            this.conditions.putIfAbsent(name, deserializer);
            return this;
        }

        public Builder<CONTEXT_TYPE> supportEntry(String type, EntryDeserializer<CONTEXT_TYPE> deserializer) {
            this.entries.putIfAbsent(type, deserializer);
            return this;
        }

        public LootTableType<CONTEXT_TYPE> build() {
            return new LootTableType<>(this.functions, this.conditions, this.entries);
        }
    }
}
