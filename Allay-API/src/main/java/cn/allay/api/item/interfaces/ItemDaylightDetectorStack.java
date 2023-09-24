package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDaylightDetectorStack extends ItemStack {
    ItemType<ItemDaylightDetectorStack> DAYLIGHT_DETECTOR_TYPE = ItemTypeBuilder
            .builder(ItemDaylightDetectorStack.class)
            .vanillaItem(VanillaItemId.DAYLIGHT_DETECTOR)
            .build();
}
