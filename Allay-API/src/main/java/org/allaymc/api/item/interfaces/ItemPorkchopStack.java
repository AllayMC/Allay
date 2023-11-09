package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPorkchopStack extends ItemStack {
  ItemType<ItemPorkchopStack> PORKCHOP_TYPE = ItemTypeBuilder
          .builder(ItemPorkchopStack.class)
          .vanillaItem(VanillaItemId.PORKCHOP)
          .build();
}
