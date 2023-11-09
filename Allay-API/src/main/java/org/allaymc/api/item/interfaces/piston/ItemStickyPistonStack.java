package org.allaymc.api.item.interfaces.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonStack extends ItemStack {
  ItemType<ItemStickyPistonStack> STICKY_PISTON_TYPE = ItemTypeBuilder
          .builder(ItemStickyPistonStack.class)
          .vanillaItem(VanillaItemId.STICKY_PISTON)
          .build();
}
