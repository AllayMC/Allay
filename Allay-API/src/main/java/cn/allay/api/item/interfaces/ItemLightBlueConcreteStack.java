package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightBlueConcreteStack extends ItemStack {
    ItemType<ItemLightBlueConcreteStack> LIGHT_BLUE_CONCRETE_TYPE = ItemTypeBuilder
            .builder(ItemLightBlueConcreteStack.class)
            .vanillaItem(VanillaItemId.LIGHT_BLUE_CONCRETE)
            .build();
}
