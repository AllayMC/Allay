package cn.allay.api.item.interfaces.diamondboots;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondBootsStack extends ItemStack {
    ItemType<ItemDiamondBootsStack> DIAMOND_BOOTS_TYPE = ItemTypeBuilder
            .builder(ItemDiamondBootsStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_BOOTS)
            .build();
}
