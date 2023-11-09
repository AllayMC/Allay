package org.allaymc.api.item.interfaces.bricks;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBricksStack extends ItemStack {
  ItemType<ItemMudBricksStack> MUD_BRICKS_TYPE = ItemTypeBuilder
          .builder(ItemMudBricksStack.class)
          .vanillaItem(VanillaItemId.MUD_BRICKS)
          .build();
}
