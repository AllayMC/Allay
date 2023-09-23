package cn.allay.api.item.interfaces.wheatseeds;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWheatSeedsStack extends ItemStack {
    ItemType<ItemWheatSeedsStack> WHEAT_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemWheatSeedsStack.class)
            .vanillaItem(VanillaItemId.WHEAT_SEEDS)
            .build();
}
