package cn.allay.api.item.interfaces.warpedfungusonastick;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWarpedFungusOnAStickStack extends ItemStack {
    ItemType<ItemWarpedFungusOnAStickStack> WARPED_FUNGUS_ON_A_STICK_TYPE = ItemTypeBuilder
            .builder(ItemWarpedFungusOnAStickStack.class)
            .vanillaItem(VanillaItemId.WARPED_FUNGUS_ON_A_STICK)
            .build();
}
