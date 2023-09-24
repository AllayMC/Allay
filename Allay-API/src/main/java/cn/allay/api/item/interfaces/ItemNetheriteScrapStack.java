package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteScrapStack extends ItemStack {
    ItemType<ItemNetheriteScrapStack> NETHERITE_SCRAP_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteScrapStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_SCRAP)
            .build();
}
