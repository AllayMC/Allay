package org.allaymc.server.item.initializer.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.piston.ItemPistonArmCollisionStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPistonArmCollisionStackInitializer {
  static void init() {
    ItemTypes.PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
            .builder(ItemPistonArmCollisionStack.class)
            .vanillaItem(VanillaItemId.PISTON_ARM_COLLISION)
            .build();
  }
}
