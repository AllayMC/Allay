package cn.allay.api.item.interfaces.warpedwartblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedWartBlockStack extends ItemStack {
    ItemType<ItemWarpedWartBlockStack> WARPED_WART_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemWarpedWartBlockStack.class)
            .vanillaItem(VanillaItemId.WARPED_WART_BLOCK)
            .build();
}
