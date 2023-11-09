package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemNetherSproutsStack extends ItemStack {
  ItemType<ItemItemNetherSproutsStack> ITEM_NETHER_SPROUTS_TYPE = ItemTypeBuilder
          .builder(ItemItemNetherSproutsStack.class)
          .vanillaItem(VanillaItemId.ITEM_NETHER_SPROUTS)
          .build();
}
