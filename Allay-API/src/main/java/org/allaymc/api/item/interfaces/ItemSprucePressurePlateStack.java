package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSprucePressurePlateStack extends ItemStack {
  ItemType<ItemSprucePressurePlateStack> SPRUCE_PRESSURE_PLATE_TYPE = ItemTypeBuilder
          .builder(ItemSprucePressurePlateStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_PRESSURE_PLATE)
          .build();
}
