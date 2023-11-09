package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPrismarineStack extends ItemStack {
  ItemType<ItemPrismarineStack> PRISMARINE_TYPE = ItemTypeBuilder
          .builder(ItemPrismarineStack.class)
          .vanillaItem(VanillaItemId.PRISMARINE)
          .build();
}
