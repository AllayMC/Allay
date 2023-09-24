package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondStack extends ItemStack {
    ItemType<ItemDiamondStack> DIAMOND_TYPE = ItemTypeBuilder
            .builder(ItemDiamondStack.class)
            .vanillaItem(VanillaItemId.DIAMOND)
            .build();
}
