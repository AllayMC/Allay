package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTotemOfUndyingStack extends ItemStack {
  ItemType<ItemTotemOfUndyingStack> TOTEM_OF_UNDYING_TYPE = ItemTypeBuilder
          .builder(ItemTotemOfUndyingStack.class)
          .vanillaItem(VanillaItemId.TOTEM_OF_UNDYING)
          .build();
}
