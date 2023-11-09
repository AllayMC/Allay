package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPhantomMembraneStack extends ItemStack {
  ItemType<ItemPhantomMembraneStack> PHANTOM_MEMBRANE_TYPE = ItemTypeBuilder
          .builder(ItemPhantomMembraneStack.class)
          .vanillaItem(VanillaItemId.PHANTOM_MEMBRANE)
          .build();
}
