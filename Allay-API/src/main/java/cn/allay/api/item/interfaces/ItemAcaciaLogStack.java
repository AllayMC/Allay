package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaLogStack extends ItemStack {
    ItemType<ItemAcaciaLogStack> ACACIA_LOG_TYPE = ItemTypeBuilder
            .builder(ItemAcaciaLogStack.class)
            .vanillaItem(VanillaItemId.ACACIA_LOG)
            .build();
}
