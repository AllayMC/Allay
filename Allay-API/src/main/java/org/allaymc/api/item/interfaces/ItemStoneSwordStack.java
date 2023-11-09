package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStoneSwordStack extends ItemStack {
  ItemType<ItemStoneSwordStack> STONE_SWORD_TYPE = ItemTypeBuilder
          .builder(ItemStoneSwordStack.class)
          .vanillaItem(VanillaItemId.STONE_SWORD)
          .build();
}
