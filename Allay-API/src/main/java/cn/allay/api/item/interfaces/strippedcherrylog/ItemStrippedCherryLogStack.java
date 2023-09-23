package cn.allay.api.item.interfaces.strippedcherrylog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCherryLogStack extends ItemStack {
    ItemType<ItemStrippedCherryLogStack> STRIPPED_CHERRY_LOG_TYPE = ItemTypeBuilder
            .builder(ItemStrippedCherryLogStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_CHERRY_LOG)
            .build();
}
