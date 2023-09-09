package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFlowingWaterStack extends ItemStack {
    ItemType<ItemFlowingWaterStack> FLOWING_WATER_TYPE = ItemTypeBuilder
            .builder(ItemFlowingWaterStack.class)
            .vanillaItem(VanillaItemId.FLOWING_WATER)
            .build();
}
