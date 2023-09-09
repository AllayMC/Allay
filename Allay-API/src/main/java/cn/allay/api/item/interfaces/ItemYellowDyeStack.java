package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowDyeStack extends ItemStack {
    ItemType<ItemYellowDyeStack> YELLOW_DYE_TYPE = ItemTypeBuilder
            .builder(ItemYellowDyeStack.class)
            .vanillaItem(VanillaItemId.YELLOW_DYE)
            .build();
}
