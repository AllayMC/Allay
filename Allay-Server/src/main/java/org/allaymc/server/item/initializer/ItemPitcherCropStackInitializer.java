package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPitcherCropStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherCropStackInitializer {
  static void init() {
    ItemTypes.PITCHER_CROP_TYPE = ItemTypeBuilder
            .builder(ItemPitcherCropStack.class)
            .vanillaItem(VanillaItemId.PITCHER_CROP)
            .build();
  }
}
