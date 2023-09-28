package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemBirchDoorStack extends ItemStack {
  ItemType<ItemItemBirchDoorStack> ITEM_BIRCH_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemBirchDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_BIRCH_DOOR)
          .build();
}
