package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStack extends ItemStack {
  ItemType<ItemRedNetherBrickStack> RED_NETHER_BRICK_TYPE = ItemTypeBuilder
          .builder(ItemRedNetherBrickStack.class)
          .vanillaItem(VanillaItemId.RED_NETHER_BRICK)
          .build();
}