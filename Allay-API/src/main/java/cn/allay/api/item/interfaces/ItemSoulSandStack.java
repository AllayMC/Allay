package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulSandStack extends ItemStack {
    ItemType<ItemSoulSandStack> SOUL_SAND_TYPE = ItemTypeBuilder
            .builder(ItemSoulSandStack.class)
            .vanillaItem(VanillaItemId.SOUL_SAND)
            .build();
}
