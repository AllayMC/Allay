package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRabbitFootStack extends ItemStack {
  ItemType<ItemRabbitFootStack> RABBIT_FOOT_TYPE = ItemTypeBuilder
          .builder(ItemRabbitFootStack.class)
          .vanillaItem(VanillaItemId.RABBIT_FOOT)
          .build();
}
