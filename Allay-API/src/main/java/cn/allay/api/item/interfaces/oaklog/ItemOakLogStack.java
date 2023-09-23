package cn.allay.api.item.interfaces.oaklog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOakLogStack extends ItemStack {
    ItemType<ItemOakLogStack> OAK_LOG_TYPE = ItemTypeBuilder
            .builder(ItemOakLogStack.class)
            .vanillaItem(VanillaItemId.OAK_LOG)
            .build();
}
