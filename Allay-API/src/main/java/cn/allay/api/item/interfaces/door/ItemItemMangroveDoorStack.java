package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemMangroveDoorStack extends ItemStack {
  ItemType<ItemItemMangroveDoorStack> ITEM_MANGROVE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemMangroveDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_MANGROVE_DOOR)
          .build();
}
