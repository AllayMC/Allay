package cn.allay.api.item.interfaces.reinforceddeepslate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemReinforcedDeepslateStack extends ItemStack {
    ItemType<ItemReinforcedDeepslateStack> REINFORCED_DEEPSLATE_TYPE = ItemTypeBuilder
            .builder(ItemReinforcedDeepslateStack.class)
            .vanillaItem(VanillaItemId.REINFORCED_DEEPSLATE)
            .build();
}
