package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNautilusShellStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNautilusShellStackInitializer {
  static void init() {
    ItemTypes.NAUTILUS_SHELL_TYPE = ItemTypeBuilder
            .builder(ItemNautilusShellStack.class)
            .vanillaItem(VanillaItemId.NAUTILUS_SHELL)
            .build();
  }
}
