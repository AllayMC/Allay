package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackStainedGlassStack extends ItemStack {
    ItemType<ItemBlackStainedGlassStack> BLACK_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemBlackStainedGlassStack.class)
            .vanillaItem(VanillaItemId.BLACK_STAINED_GLASS)
            .build();
}
