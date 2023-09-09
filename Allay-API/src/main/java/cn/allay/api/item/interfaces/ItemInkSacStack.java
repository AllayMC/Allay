package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInkSacStack extends ItemStack {
    ItemType<ItemInkSacStack> INK_SAC_TYPE = ItemTypeBuilder
            .builder(ItemInkSacStack.class)
            .vanillaItem(VanillaItemId.INK_SAC)
            .build();
}
