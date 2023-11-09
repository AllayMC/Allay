package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedstoneLampStack extends ItemStack {
  ItemType<ItemRedstoneLampStack> REDSTONE_LAMP_TYPE = ItemTypeBuilder
          .builder(ItemRedstoneLampStack.class)
          .vanillaItem(VanillaItemId.REDSTONE_LAMP)
          .build();
}
