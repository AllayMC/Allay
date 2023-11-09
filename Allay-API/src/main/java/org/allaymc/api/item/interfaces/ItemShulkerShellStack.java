package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShulkerShellStack extends ItemStack {
  ItemType<ItemShulkerShellStack> SHULKER_SHELL_TYPE = ItemTypeBuilder
          .builder(ItemShulkerShellStack.class)
          .vanillaItem(VanillaItemId.SHULKER_SHELL)
          .build();
}
