package org.allaymc.server.loottable.condition;

import com.google.common.base.Preconditions;
import com.google.gson.JsonObject;
import lombok.AllArgsConstructor;
import org.allaymc.api.item.enchantment.EnchantmentType;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.loottable.context.BreakBlockContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Allay Project 2024/7/17
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
public class MatchToolCondition implements Condition<BreakBlockContext> {

    protected ItemType<?> itemType;
    // TODO: ranged count check (min, max)
    protected Integer count; // null -> count check is disabled
    // TODO: ranged durability check (min, max)
    protected Integer durability; // null -> durability check is disabled
    // TODO: enchantment level check
    protected Set<EnchantmentType> enchantmentTypes;

    public static ConditionDeserializer<BreakBlockContext> deserializer() {
        return new MatchToolConditionDeserializer();
    }

    @Override
    public boolean test(BreakBlockContext context) {
        var usedItem = context.getUsedItem();
        if (count != null && usedItem.getCount() != count) return false;
        if (durability != null && usedItem.getDurability() != durability) return false;
        if (itemType != null && usedItem.getItemType() != itemType) return false;
        return enchantmentTypes.stream().allMatch(usedItem::hasEnchantment);
    }

    public static class MatchToolConditionDeserializer implements ConditionDeserializer<BreakBlockContext> {
        @Override
        public Condition<BreakBlockContext> deserialize(JsonObject json) {
            ItemType<?> itemType = null;
            if (json.has("item")) {
                itemType = Registries.ITEM_TYPES.get(new Identifier(json.get("item").getAsString()));
            }
            Integer count = null;
            if (json.has("count")) {
                count = json.get("count").getAsInt();
            }
            Integer durability = null;
            if (json.has("durability")) {
                durability = json.get("durability").getAsInt();
            }
            Set<EnchantmentType> enchTypes = new HashSet<>();
            if (json.has("enchantments")) {
                for (var element : json.get("enchantments").getAsJsonArray()) {
                    var enchObj = element.getAsJsonObject();
                    var enchName = enchObj.get("enchantment").getAsString();
                    if (!enchName.contains(":")) enchName = Identifier.DEFAULT_NAMESPACE + ":" + enchName;
                    var enchType = Registries.ENCHANTMENTS.getByK2(new Identifier(enchName));
                    Preconditions.checkNotNull(enchType);
                    enchTypes.add(enchType);
                }
            }
            return new MatchToolCondition(itemType, count, durability, enchTypes);
        }
    }
}
