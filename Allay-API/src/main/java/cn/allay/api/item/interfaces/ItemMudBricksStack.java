package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBricksStack extends ItemStack {
    ItemType<ItemMudBricksStack> MUD_BRICKS_TYPE = ItemTypeBuilder
            .builder(ItemMudBricksStack.class)
            .vanillaItem(VanillaItemId.MUD_BRICKS)
            .build();
}
