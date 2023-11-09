package org.allaymc.api.item.interfaces.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestMinecartStack extends ItemStack {
  ItemType<ItemChestMinecartStack> CHEST_MINECART_TYPE = ItemTypeBuilder
          .builder(ItemChestMinecartStack.class)
          .vanillaItem(VanillaItemId.CHEST_MINECART)
          .build();
}
