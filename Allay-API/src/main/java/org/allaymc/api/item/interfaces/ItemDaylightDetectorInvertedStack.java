package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
