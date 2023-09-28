package cn.allay.api.item.interfaces.boat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAcaciaChestBoatStack extends ItemStack {
  ItemType<ItemAcaciaChestBoatStack> ACACIA_CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemAcaciaChestBoatStack.class)
          .vanillaItem(VanillaItemId.ACACIA_CHEST_BOAT)
          .build();
}
