package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayConcreteStack extends ItemStack {
    ItemType<ItemLightGrayConcreteStack> LIGHT_GRAY_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLightGrayConcreteStack.class)
            .vanillaItem(VanillaItemId.LIGHT_GRAY_CONCRETE)
            .build();
}
