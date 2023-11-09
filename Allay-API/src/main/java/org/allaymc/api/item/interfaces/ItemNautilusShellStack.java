package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNautilusShellStack extends ItemStack {
  ItemType<ItemNautilusShellStack> NAUTILUS_SHELL_TYPE = ItemTypeBuilder
          .builder(ItemNautilusShellStack.class)
          .vanillaItem(VanillaItemId.NAUTILUS_SHELL)
          .build();
}
