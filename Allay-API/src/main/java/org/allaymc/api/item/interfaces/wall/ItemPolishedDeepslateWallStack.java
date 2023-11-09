package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedDeepslateWallStack extends ItemStack {
  ItemType<ItemPolishedDeepslateWallStack> POLISHED_DEEPSLATE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemPolishedDeepslateWallStack.class)
          .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_WALL)
          .build();
}
