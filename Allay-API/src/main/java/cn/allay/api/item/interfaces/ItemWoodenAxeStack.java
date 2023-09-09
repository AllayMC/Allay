package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWoodenAxeStack extends ItemStack {
    ItemType<ItemWoodenAxeStack> WOODEN_AXE_TYPE = ItemTypeBuilder
            .builder(ItemWoodenAxeStack.class)
            .vanillaItem(VanillaItemId.WOODEN_AXE)
            .build();
}
