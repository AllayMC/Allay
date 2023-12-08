package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAndesiteStack extends ItemStack {
  ItemType<ItemAndesiteStack> ANDESITE_TYPE = ItemTypeBuilder
          .builder(ItemAndesiteStack.class)
          .vanillaItem(VanillaItemId.ANDESITE)
          .build();
}
