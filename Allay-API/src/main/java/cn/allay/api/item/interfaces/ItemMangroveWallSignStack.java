package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveWallSignStack extends ItemStack {
    ItemType<ItemMangroveWallSignStack> MANGROVE_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveWallSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_WALL_SIGN)
            .build();
}
