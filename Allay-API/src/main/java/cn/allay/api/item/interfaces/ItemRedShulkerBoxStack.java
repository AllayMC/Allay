package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedShulkerBoxStack extends ItemStack {
    ItemType<ItemRedShulkerBoxStack> RED_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemRedShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.RED_SHULKER_BOX)
            .build();
}
