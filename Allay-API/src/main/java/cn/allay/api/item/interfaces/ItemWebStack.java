package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWebStack extends ItemStack {
  ItemType<ItemWebStack> WEB_TYPE = ItemTypeBuilder
          .builder(ItemWebStack.class)
          .vanillaItem(VanillaItemId.WEB)
          .build();
}
