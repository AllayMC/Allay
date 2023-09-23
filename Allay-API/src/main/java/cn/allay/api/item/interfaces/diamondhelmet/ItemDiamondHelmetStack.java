package cn.allay.api.item.interfaces.diamondhelmet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDiamondHelmetStack extends ItemStack {
    ItemType<ItemDiamondHelmetStack> DIAMOND_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemDiamondHelmetStack.class)
            .vanillaItem(VanillaItemId.DIAMOND_HELMET)
            .build();
}
