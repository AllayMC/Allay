package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedDarkOakLogStack extends ItemStack {
    ItemType<ItemStrippedDarkOakLogStack> STRIPPED_DARK_OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedDarkOakLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_DARK_OAK_LOG)
            .build();
}
