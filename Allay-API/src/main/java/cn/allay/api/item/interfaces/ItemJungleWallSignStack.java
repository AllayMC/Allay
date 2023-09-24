package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleWallSignStack extends ItemStack {
    ItemType<ItemJungleWallSignStack> JUNGLE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemJungleWallSignStack.class)
            .vanillaItem(VanillaItemId.JUNGLE_WALL_SIGN)
            .build();
}
