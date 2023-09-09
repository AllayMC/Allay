package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedOakLogStack extends ItemStack {
    ItemType<ItemStrippedOakLogStack> STRIPPED_OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedOakLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_OAK_LOG)
            .build();
}
