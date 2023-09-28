package cn.allay.api.item.interfaces.door;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooDoorStack extends ItemStack {
  ItemType<ItemBambooDoorStack> BAMBOO_DOOR_TYPE = ItemTypeBuilder
          .builder(ItemBambooDoorStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_DOOR)
          .build();
}
