package org.allaymc.api.item.interfaces.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedMangroveWoodStack extends ItemStack {
  ItemType<ItemStrippedMangroveWoodStack> STRIPPED_MANGROVE_WOOD_TYPE = ItemTypeBuilder
          .builder(ItemStrippedMangroveWoodStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_MANGROVE_WOOD)
          .build();
}
