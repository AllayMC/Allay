package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeShulkerBoxStack extends ItemStack {
    ItemType<ItemOrangeShulkerBoxStack> ORANGE_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemOrangeShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.ORANGE_SHULKER_BOX)
            .build();
}
