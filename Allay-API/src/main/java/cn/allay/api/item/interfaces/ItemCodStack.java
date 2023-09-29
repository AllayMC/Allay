package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCodStack extends ItemStack {
  ItemType<ItemCodStack> COD_TYPE = ItemTypeBuilder
          .builder(ItemCodStack.class)
          .vanillaItem(VanillaItemId.COD)
          .build();
}
