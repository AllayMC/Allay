package org.allaymc.api.item.interfaces.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonArmCollisionStack extends ItemStack {
  ItemType<ItemStickyPistonArmCollisionStack> STICKY_PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
          .builder(ItemStickyPistonArmCollisionStack.class)
          .vanillaItem(VanillaItemId.STICKY_PISTON_ARM_COLLISION)
          .build();
}
