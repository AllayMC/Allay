package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemStructureVoidStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStructureVoidStackInitializer {
  static void init() {
    ItemTypes.STRUCTURE_VOID_TYPE = ItemTypeBuilder
            .builder(ItemStructureVoidStack.class)
            .vanillaItem(VanillaItemId.STRUCTURE_VOID)
            .build();
  }
}
