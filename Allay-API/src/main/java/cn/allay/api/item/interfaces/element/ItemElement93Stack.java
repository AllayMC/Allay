package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement93Stack extends ItemStack {
  ItemType<ItemElement93Stack> ELEMENT_93_TYPE = ItemTypeBuilder
          .builder(ItemElement93Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_93)
          .build();
}
