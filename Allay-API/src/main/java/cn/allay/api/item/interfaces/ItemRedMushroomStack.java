package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedMushroomStack extends ItemStack {
    ItemType<ItemRedMushroomStack> RED_MUSHROOM_TYPE = ItemTypeBuilder
            .builder(ItemRedMushroomStack.class)
            .vanillaItem(VanillaItemId.RED_MUSHROOM)
            .build();
}
