package cn.allay.api.item.interfaces.bonemeal;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneMealStack extends ItemStack {
    ItemType<ItemBoneMealStack> BONE_MEAL_TYPE = ItemTypeBuilder
            .builder(ItemBoneMealStack.class)
            .vanillaItem(VanillaItemId.BONE_MEAL)
            .build();
}
