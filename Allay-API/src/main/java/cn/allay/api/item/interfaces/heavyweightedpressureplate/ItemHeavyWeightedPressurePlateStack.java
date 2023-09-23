package cn.allay.api.item.interfaces.heavyweightedpressureplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHeavyWeightedPressurePlateStack extends ItemStack {
    ItemType<ItemHeavyWeightedPressurePlateStack> HEAVY_WEIGHTED_PRESSURE_PLATE_TYPE = ItemTypeBuilder
            .builder(ItemHeavyWeightedPressurePlateStack.class)
            .vanillaItem(VanillaItemId.HEAVY_WEIGHTED_PRESSURE_PLATE)
            .build();
}
