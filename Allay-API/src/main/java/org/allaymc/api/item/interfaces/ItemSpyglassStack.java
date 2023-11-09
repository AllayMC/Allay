package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpyglassStack extends ItemStack {
  ItemType<ItemSpyglassStack> SPYGLASS_TYPE = ItemTypeBuilder
          .builder(ItemSpyglassStack.class)
          .vanillaItem(VanillaItemId.SPYGLASS)
          .build();
}
