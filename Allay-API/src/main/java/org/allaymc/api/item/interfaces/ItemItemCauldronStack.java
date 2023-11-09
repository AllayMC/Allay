package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemCauldronStack extends ItemStack {
  ItemType<ItemItemCauldronStack> ITEM_CAULDRON_TYPE = ItemTypeBuilder
          .builder(ItemItemCauldronStack.class)
          .vanillaItem(VanillaItemId.ITEM_CAULDRON)
          .build();
}
