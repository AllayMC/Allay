package org.allaymc.api.item.interfaces.wall;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateWallStack extends ItemStack {
  ItemType<ItemCobbledDeepslateWallStack> COBBLED_DEEPSLATE_WALL_TYPE = ItemTypeBuilder
          .builder(ItemCobbledDeepslateWallStack.class)
          .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE_WALL)
          .build();
}
