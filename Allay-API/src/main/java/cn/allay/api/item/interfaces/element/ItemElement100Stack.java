package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement100Stack extends ItemStack {
  ItemType<ItemElement100Stack> ELEMENT_100_TYPE = ItemTypeBuilder
          .builder(ItemElement100Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_100)
          .build();
}
