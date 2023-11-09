package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChorusFruitStack extends ItemStack {
  ItemType<ItemChorusFruitStack> CHORUS_FRUIT_TYPE = ItemTypeBuilder
          .builder(ItemChorusFruitStack.class)
          .vanillaItem(VanillaItemId.CHORUS_FRUIT)
          .build();
}
