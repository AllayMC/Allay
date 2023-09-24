package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedFlowerStack extends ItemStack {
    ItemType<ItemRedFlowerStack> RED_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemRedFlowerStack.class)
            .vanillaItem(VanillaItemId.RED_FLOWER)
            .build();
}
