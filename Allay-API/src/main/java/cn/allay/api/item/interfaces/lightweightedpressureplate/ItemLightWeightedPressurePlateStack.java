package cn.allay.api.item.interfaces.lightweightedpressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightWeightedPressurePlateStack extends ItemStack {
    ItemType<ItemLightWeightedPressurePlateStack> LIGHT_WEIGHTED_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemLightWeightedPressurePlateStack.class)
            .vanillaItem(VanillaItemId.LIGHT_WEIGHTED_PRESSURE_PLATE)
            .build();
}
