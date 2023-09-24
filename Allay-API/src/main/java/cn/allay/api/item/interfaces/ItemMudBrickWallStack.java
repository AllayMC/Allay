package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickWallStack extends ItemStack {
    ItemType<ItemMudBrickWallStack> MUD_BRICK_WALL_TYPE = ItemTypeBuilder
            .builder(ItemMudBrickWallStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICK_WALL)
            .build();
}
