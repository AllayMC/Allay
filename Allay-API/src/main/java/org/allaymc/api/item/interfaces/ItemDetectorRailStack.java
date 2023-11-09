package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDetectorRailStack extends ItemStack {
  ItemType<ItemDetectorRailStack> DETECTOR_RAIL_TYPE = ItemTypeBuilder
          .builder(ItemDetectorRailStack.class)
          .vanillaItem(VanillaItemId.DETECTOR_RAIL)
          .build();
}
