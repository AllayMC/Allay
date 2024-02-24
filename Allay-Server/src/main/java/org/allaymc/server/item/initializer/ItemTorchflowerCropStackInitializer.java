package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemTorchflowerCropStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerCropStackInitializer {
  static void init() {
    ItemTypes.TORCHFLOWER_CROP_TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerCropStack.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER_CROP)
            .build();
  }
}
