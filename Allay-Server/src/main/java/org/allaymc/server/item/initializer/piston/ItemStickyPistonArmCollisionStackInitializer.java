package org.allaymc.server.item.initializer.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.piston.ItemStickyPistonArmCollisionStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonArmCollisionStackInitializer {
  static void init() {
    ItemTypes.STICKY_PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
            .builder(ItemStickyPistonArmCollisionStack.class)
            .vanillaItem(VanillaItemId.STICKY_PISTON_ARM_COLLISION)
            .build();
  }
}
