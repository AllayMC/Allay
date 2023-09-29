package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadbushStack extends ItemStack {
  ItemType<ItemDeadbushStack> DEADBUSH_TYPE = ItemTypeBuilder
          .builder(ItemDeadbushStack.class)
          .vanillaItem(VanillaItemId.DEADBUSH)
          .build();
}
