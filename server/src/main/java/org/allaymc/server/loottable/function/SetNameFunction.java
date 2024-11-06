package org.allaymc.server.loottable.function;

import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.allaymc.api.item.ItemStack;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
public class SetNameFunction implements Function {
    protected String name;

    public static FunctionDeserializer deserializer() {
        return new Deserializer();
    }

    @Override
    public void apply(ItemStack itemStack) {
        itemStack.setCustomName(name);
    }

    public static class Deserializer implements FunctionDeserializer {
        @Override
        public Function deserialize(JsonObject json) {
            var name = json.get("name").getAsString();
            return new SetNameFunction(name);
        }
    }
}
