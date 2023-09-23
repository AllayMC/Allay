package cn.allay.api.item.interfaces.deepslatetiledoubleslab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTileDoubleSlabStack extends ItemStack {
    ItemType<ItemDeepslateTileDoubleSlabStack> DEEPSLATE_TILE_DOUBLE_SLAB_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTileDoubleSlabStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILE_DOUBLE_SLAB)
            .build();
}
