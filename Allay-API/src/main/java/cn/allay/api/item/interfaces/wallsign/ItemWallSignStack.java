package cn.allay.api.item.interfaces.wallsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWallSignStack extends ItemStack {
  ItemType<ItemWallSignStack> WALL_SIGN_TYPE = ItemTypeBuilder
          .builder(ItemWallSignStack.class)
          .vanillaItem(VanillaItemId.WALL_SIGN)
          .build();
}
