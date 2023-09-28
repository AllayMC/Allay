package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement6Stack extends ItemStack {
  ItemType<ItemElement6Stack> ELEMENT_6_TYPE = ItemTypeBuilder
          .builder(ItemElement6Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_6)
          .build();
}
