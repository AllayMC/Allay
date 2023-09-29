package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemQuartzOreStack extends ItemStack {
  ItemType<ItemQuartzOreStack> QUARTZ_ORE_TYPE = ItemTypeBuilder
          .builder(ItemQuartzOreStack.class)
          .vanillaItem(VanillaItemId.QUARTZ_ORE)
          .build();
}
