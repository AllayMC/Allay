package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAzaleaStack extends ItemStack {
  ItemType<ItemAzaleaStack> AZALEA_TYPE = ItemTypeBuilder
          .builder(ItemAzaleaStack.class)
          .vanillaItem(VanillaItemId.AZALEA)
          .build();
}
