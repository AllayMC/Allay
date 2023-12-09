package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledTuffBricksStack extends ItemStack {
  ItemType<ItemChiseledTuffBricksStack> CHISELED_TUFF_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemChiseledTuffBricksStack.class)
          .vanillaItem(VanillaItemId.CHISELED_TUFF_BRICKS)
          .build();
}
