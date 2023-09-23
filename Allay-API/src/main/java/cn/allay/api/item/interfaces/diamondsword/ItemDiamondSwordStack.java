package cn.allay.api.item.interfaces.diamondsword;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondSwordStack extends ItemStack {
    ItemType<ItemDiamondSwordStack> DIAMOND_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemDiamondSwordStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_SWORD)
            .build();
}
