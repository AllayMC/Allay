package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShulkerShellStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerShellStackInitializer {
  static void init() {
    ItemTypes.SHULKER_SHELL_TYPE = ItemTypeBuilder
            .builder(ItemShulkerShellStack.class)
            .vanillaItem(VanillaItemId.SHULKER_SHELL)
            .build();
  }
}
