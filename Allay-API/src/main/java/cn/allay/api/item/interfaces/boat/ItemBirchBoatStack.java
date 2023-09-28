package cn.allay.api.item.interfaces.boat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchBoatStack extends ItemStack {
  ItemType<ItemBirchBoatStack> BIRCH_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemBirchBoatStack.class)
          .vanillaItem(VanillaItemId.BIRCH_BOAT)
          .build();
}
