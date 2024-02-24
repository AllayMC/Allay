package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemPrismarineCrystalsStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineCrystalsStackInitializer {
  static void init() {
    ItemTypes.PRISMARINE_CRYSTALS_TYPE = ItemTypeBuilder
            .builder(ItemPrismarineCrystalsStack.class)
            .vanillaItem(VanillaItemId.PRISMARINE_CRYSTALS)
            .build();
  }
}
