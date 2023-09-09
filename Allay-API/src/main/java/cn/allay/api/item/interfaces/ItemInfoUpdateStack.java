package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInfoUpdateStack extends ItemStack {
    ItemType<ItemInfoUpdateStack> INFO_UPDATE_TYPE = ItemTypeBuilder
            .builder(ItemInfoUpdateStack.class)
            .vanillaItem(VanillaItemId.INFO_UPDATE)
            .build();
}
