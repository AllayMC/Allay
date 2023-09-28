package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement9Stack extends ItemStack {
  ItemType<ItemElement9Stack> ELEMENT_9_TYPE = ItemTypeBuilder
          .builder(ItemElement9Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_9)
          .build();
}
