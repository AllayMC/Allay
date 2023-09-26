package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayStainedGlassStack extends ItemStack {
    ItemType<ItemGrayStainedGlassStack> GRAY_STAINED_GLASS_TYPE = ItemTypeBuilder
            .builder(ItemGrayStainedGlassStack.class)
            .vanillaItem(VanillaItemId.GRAY_STAINED_GLASS)
            .build();
}
