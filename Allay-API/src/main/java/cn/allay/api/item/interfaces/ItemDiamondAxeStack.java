package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondAxeStack extends ItemStack {
    ItemType<ItemDiamondAxeStack> DIAMOND_AXE_TYPE = ItemTypeBuilder
            .builder(ItemDiamondAxeStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_AXE)
            .build();
}
