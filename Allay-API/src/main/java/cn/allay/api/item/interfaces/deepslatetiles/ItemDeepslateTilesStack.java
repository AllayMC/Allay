package cn.allay.api.item.interfaces.deepslatetiles;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeepslateTilesStack extends ItemStack {
    ItemType<ItemDeepslateTilesStack> DEEPSLATE_TILES_TYPE = ItemTypeBuilder
            .builder(ItemDeepslateTilesStack.class)
            .vanillaItem(VanillaItemId.DEEPSLATE_TILES)
            .build();
}
