package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBrickWallStack extends ItemStack {
    ItemType<ItemPolishedBlackstoneBrickWallStack> POLISHED_BLACKSTONE_BRICK_WALL_TYPE = ItemTypeBuilder
            .builder(ItemPolishedBlackstoneBrickWallStack.class)
            .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICK_WALL)
            .build();
}
