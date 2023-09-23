package cn.allay.api.item.interfaces.cyanshulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCyanShulkerBoxStack extends ItemStack {
    ItemType<ItemCyanShulkerBoxStack> CYAN_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemCyanShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.CYAN_SHULKER_BOX)
            .build();
}
