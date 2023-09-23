package cn.allay.api.item.interfaces.birchlog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchLogStack extends ItemStack {
    ItemType<ItemBirchLogStack> BIRCH_LOG_TYPE = ItemTypeBuilder
            .builder(ItemBirchLogStack.class)
            .vanillaItem(VanillaItemId.BIRCH_LOG)
            .build();
}
