package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemJungleDoorStack extends ItemStack {
  ItemType<ItemJungleDoorStack> JUNGLE_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemJungleDoorStack.class)
          .vanillaItem(VanillaItemId.JUNGLE_DOOR)
          .build();
}
