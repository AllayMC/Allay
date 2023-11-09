package org.allaymc.api.item.interfaces.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCommandBlockMinecartStack extends ItemStack {
  ItemType<ItemCommandBlockMinecartStack> COMMAND_BLOCK_MINECART_TYPE = ItemTypeBuilder
          .builder(ItemCommandBlockMinecartStack.class)
          .vanillaItem(VanillaItemId.COMMAND_BLOCK_MINECART)
          .build();
}
