package cn.allay.api.item.interfaces.log;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLog2Stack extends ItemStack {
  ItemType<ItemLog2Stack> LOG2_TYPE = ItemTypeBuilder
          .builder(ItemLog2Stack.class)
          .vanillaItem(VanillaItemId.LOG2)
          .build();
}
