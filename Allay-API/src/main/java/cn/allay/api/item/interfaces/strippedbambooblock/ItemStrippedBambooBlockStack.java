package cn.allay.api.item.interfaces.strippedbambooblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedBambooBlockStack extends ItemStack {
    ItemType<ItemStrippedBambooBlockStack> STRIPPED_BAMBOO_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemStrippedBambooBlockStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_BAMBOO_BLOCK)
            .build();
}
