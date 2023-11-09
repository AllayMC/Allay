package org.allaymc.api.item.interfaces.wood;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedCherryWoodStack extends ItemStack {
  ItemType<ItemStrippedCherryWoodStack> STRIPPED_CHERRY_WOOD_TYPE = ItemTypeBuilder
          .builder(ItemStrippedCherryWoodStack.class)
          .vanillaItem(VanillaItemId.STRIPPED_CHERRY_WOOD)
          .build();
}
