package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItemSpruceDoorStack extends ItemStack {
  ItemType<ItemItemSpruceDoorStack> ITEM_SPRUCE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemItemSpruceDoorStack.class)
          .vanillaItem(VanillaItemId.ITEM_SPRUCE_DOOR)
          .build();
}
