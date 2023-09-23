package cn.allay.api.item.interfaces.blazepowder;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlazePowderStack extends ItemStack {
    ItemType<ItemBlazePowderStack> BLAZE_POWDER_TYPE = ItemTypeBuilder
            .builder(ItemBlazePowderStack.class)
            .vanillaItem(VanillaItemId.BLAZE_POWDER)
            .build();
}
