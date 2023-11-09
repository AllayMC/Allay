package org.allaymc.api.item.interfaces.wallsign;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkoakWallSignStack extends ItemStack {
  ItemType<ItemDarkoakWallSignStack> DARKOAK_WALL_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemDarkoakWallSignStack.class)
          .vanillaItem(VanillaItemId.DARKOAK_WALL_SIGN)
          .build();
}
