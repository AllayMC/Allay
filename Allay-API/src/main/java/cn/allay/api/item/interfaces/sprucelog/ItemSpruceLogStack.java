package cn.allay.api.item.interfaces.sprucelog;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceLogStack extends ItemStack {
    ItemType<ItemSpruceLogStack> SPRUCE_LOG_TYPE = ItemTypeBuilder
            .builder(ItemSpruceLogStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_LOG)
            .build();
}
