package cn.allay.api.item.interfaces.wallsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCherryWallSignStack extends ItemStack {
  ItemType<ItemCherryWallSignStack> CHERRY_WALL_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemCherryWallSignStack.class)
          .vanillaItem(VanillaItemId.CHERRY_WALL_SIGN)
          .build();
}
