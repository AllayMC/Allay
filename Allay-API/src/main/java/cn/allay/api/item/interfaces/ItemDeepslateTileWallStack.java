package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileWallStack extends ItemStack {
    ItemType<ItemDeepslateTileWallStack> DEEPSLATE_TILE_WALL_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTileWallStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILE_WALL)
            .build();
}
