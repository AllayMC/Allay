package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPoweredRepeaterStack extends ItemStack {
  ItemType<ItemPoweredRepeaterStack> POWERED_REPEATER_TYPE = ItemTypeBuilder
          .builder(ItemPoweredRepeaterStack.class)
          .vanillaItem(VanillaItemId.POWERED_REPEATER)
          .build();
}
