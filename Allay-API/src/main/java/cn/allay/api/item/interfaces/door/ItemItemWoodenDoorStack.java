package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemWoodenDoorStack extends ItemStack {
  ItemType<ItemItemWoodenDoorStack> ITEM_WOODEN_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemWoodenDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_WOODEN_DOOR)
          .build();
}
