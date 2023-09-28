package cn.allay.api.item.interfaces.boat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestBoatStack extends ItemStack {
  ItemType<ItemChestBoatStack> CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemChestBoatStack.class)
          .vanillaItem(VanillaItemId.CHEST_BOAT)
          .build();
}
