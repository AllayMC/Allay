package org.allaymc.api.item.interfaces.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooWallSignStack extends ItemStack {
  ItemType<ItemBambooWallSignStack> BAMBOO_WALL_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemBambooWallSignStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_WALL_SIGN)
          .build();
}
