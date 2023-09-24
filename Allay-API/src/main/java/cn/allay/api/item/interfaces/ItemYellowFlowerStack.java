package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowFlowerStack extends ItemStack {
    ItemType<ItemYellowFlowerStack> YELLOW_FLOWER_TYPE = ItemTypeBuilder
            .builder(ItemYellowFlowerStack.class)
            .vanillaItem(VanillaItemId.YELLOW_FLOWER)
            .build();
}
