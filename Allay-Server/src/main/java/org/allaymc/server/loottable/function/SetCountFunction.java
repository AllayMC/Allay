package org.allaymc.server.loottable.function;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.allaymc.api.item.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Allay Project 2024/7/15
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class SetCountFunction implements Function {
    protected int min;
    protected int max;
    @Override
    public void apply(ItemStack itemStack) {
        if (min == max) {
            itemStack.setCount(itemStack.getItemData().maxStackSize() >= min ? min : itemStack.getItemData().maxStackSize());
            return;
        }
        var rand = min + ThreadLocalRandom.current().nextInt(max - min + 1);
        itemStack.setCount(itemStack.getItemData().maxStackSize() >= rand ? rand : itemStack.getItemData().maxStackSize());
    }

    public static FunctionDeserializer deserializer() {
        return new SetCountFunctionDeserializer();
    }

    public static class SetCountFunctionDeserializer implements FunctionDeserializer {

        @Override
        public Function deserialize(JsonObject json) {
            var count = json.get("count").getAsJsonObject();
            var min = count.get("min").getAsInt();
            var max = count.get("max").getAsInt();
            Preconditions.checkState(min < max, "max must be greater than min");
            return new SetCountFunction(min, max);
        }
    }
}
