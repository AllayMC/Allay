package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneBricksStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneBricksStack> POLISHED_BLACKSTONE_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneBricksStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BRICKS)
          .build();
}
