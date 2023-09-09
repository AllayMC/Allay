package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueDyeStack extends ItemStack {
    ItemType<ItemLightBlueDyeStack> LIGHT_BLUE_DYE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueDyeStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_DYE)
            .build();
}
