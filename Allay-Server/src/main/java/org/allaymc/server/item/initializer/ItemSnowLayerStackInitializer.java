package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSnowLayerStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowLayerStackInitializer {
  static void init() {
    ItemTypes.SNOW_LAYER_TYPE = ItemTypeBuilder
            .builder(ItemSnowLayerStack.class)
            .vanillaItem(VanillaItemId.SNOW_LAYER)
            .build();
  }
}
