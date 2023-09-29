package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitStewStack extends ItemStack {
  ItemType<ItemRabbitStewStack> RABBIT_STEW_TYPE = ItemTypeBuilder
          .builder(ItemRabbitStewStack.class)
          .vanillaItem(VanillaItemId.RABBIT_STEW)
          .build();
}
