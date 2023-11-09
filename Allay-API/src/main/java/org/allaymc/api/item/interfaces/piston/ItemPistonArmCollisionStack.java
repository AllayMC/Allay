package org.allaymc.api.item.interfaces.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPistonArmCollisionStack extends ItemStack {
  ItemType<ItemPistonArmCollisionStack> PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
          .builder(ItemPistonArmCollisionStack.class)
          .vanillaItem(VanillaItemId.PISTON_ARM_COLLISION)
          .build();
}
