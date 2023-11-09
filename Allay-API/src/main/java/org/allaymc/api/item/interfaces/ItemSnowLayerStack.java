package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowLayerStack extends ItemStack {
  ItemType<ItemSnowLayerStack> SNOW_LAYER_TYPE = ItemTypeBuilder
          .builder(ItemSnowLayerStack.class)
          .vanillaItem(VanillaItemId.SNOW_LAYER)
          .build();
}
