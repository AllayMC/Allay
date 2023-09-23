package cn.allay.api.item.interfaces.sculk;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSculkStack extends ItemStack {
    ItemType<ItemSculkStack> SCULK_TYPE = ItemTypeBuilder
            .builder(ItemSculkStack.class)
            .vanillaItem(VanillaItemId.SCULK)
            .build();
}
