package cn.allay.api.item.interfaces.boat;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceChestBoatStack extends ItemStack {
  ItemType<ItemSpruceChestBoatStack> SPRUCE_CHEST_BOAT_TYPE = ItemTypeBuilder
          .builder(ItemSpruceChestBoatStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_CHEST_BOAT)
          .build();
}
