package cn.allay.api.item.interfaces.pinkshulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkShulkerBoxStack extends ItemStack {
    ItemType<ItemPinkShulkerBoxStack> PINK_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemPinkShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.PINK_SHULKER_BOX)
            .build();
}
