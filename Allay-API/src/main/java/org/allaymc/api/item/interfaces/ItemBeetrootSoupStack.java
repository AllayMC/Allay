package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSoupStack extends ItemStack {
  ItemType<ItemBeetrootSoupStack> BEETROOT_SOUP_TYPE = ItemTypeBuilder
          .builder(ItemBeetrootSoupStack.class)
          .vanillaItem(VanillaItemId.BEETROOT_SOUP)
          .build();
}
