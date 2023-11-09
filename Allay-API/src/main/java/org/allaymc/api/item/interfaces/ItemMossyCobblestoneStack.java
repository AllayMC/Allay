package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyCobblestoneStack extends ItemStack {
  ItemType<ItemMossyCobblestoneStack> MOSSY_COBBLESTONE_TYPE = ItemTypeBuilder
          .builder(ItemMossyCobblestoneStack.class)
          .vanillaItem(VanillaItemId.MOSSY_COBBLESTONE)
          .build();
}
