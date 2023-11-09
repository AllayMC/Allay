package org.allaymc.api.item.interfaces.slab;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickSlabStack extends ItemStack {
  ItemType<ItemMudBrickSlabStack> MUD_BRICK_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemMudBrickSlabStack.class)
          .vanillaItem(VanillaItemId.MUD_BRICK_SLAB)
          .build();
}
