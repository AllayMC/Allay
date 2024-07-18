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
public class SetDamageFunction implements Function {
    protected float min, max;

    public static FunctionDeserializer deserializer() {
        return new SetDamageFunctionDeserializer();
    }

    @Override
    public void apply(ItemStack itemStack) {
        var rand = (int) ThreadLocalRandom.current().nextFloat(min, max);
        itemStack.setDurability(itemStack.getItemData().maxDamage() * rand);
    }

    public static class SetDamageFunctionDeserializer implements FunctionDeserializer {
        @Override
        public Function deserialize(JsonObject json) {
            var count = json.get("damage").getAsJsonObject();
            var min = count.get("min").getAsFloat();
            Preconditions.checkState(min > 0 && min <= 1, "damage must be between 0 and 1");
            var max = count.get("max").getAsFloat();
            Preconditions.checkState(max > 0 && max <= 1, "damage must be between 0 and 1");
            Preconditions.checkState(min < max, "max must be greater than min");
            return new SetDamageFunction(min, max);
        }
    }
}
