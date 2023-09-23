package cn.allay.api.item.interfaces.magentashulkerbox;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaShulkerBoxStack extends ItemStack {
    ItemType<ItemMagentaShulkerBoxStack> MAGENTA_SHULKER_BOX_TYPE = ItemTypeBuilder
            .builder(ItemMagentaShulkerBoxStack.class)
            .vanillaItem(VanillaItemId.MAGENTA_SHULKER_BOX)
            .build();
}
