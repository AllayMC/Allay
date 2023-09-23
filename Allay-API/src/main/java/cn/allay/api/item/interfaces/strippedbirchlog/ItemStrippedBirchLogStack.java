package cn.allay.api.item.interfaces.strippedbirchlog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedBirchLogStack extends ItemStack {
    ItemType<ItemStrippedBirchLogStack> STRIPPED_BIRCH_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedBirchLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_BIRCH_LOG)
            .build();
}
