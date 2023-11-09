package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrackedPolishedBlackstoneBricksStack extends ItemStack {
  ItemType<ItemCrackedPolishedBlackstoneBricksStack> CRACKED_POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemCrackedPolishedBlackstoneBricksStack.class)
          .vanillaItem(VanillaItemId.CRACKED_POLISHED_BLACKSTONE_BRICKS)
          .build();
}
