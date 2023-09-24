package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDaylightDetectorInvertedStack extends ItemStack {
    ItemType<ItemDaylightDetectorInvertedStack> DAYLIGHT_DETECTOR_INVERTED_TYPE = ItemTypeBuilder
            .builder(ItemDaylightDetectorInvertedStack.class)
            .vanillaItem(VanillaItemId.DAYLIGHT_DETECTOR_INVERTED)
            .build();
}
