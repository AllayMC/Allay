package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement111Stack extends ItemStack {
  ItemType<ItemElement111Stack> ELEMENT_111_TYPE = ItemTypeBuilder
          .builder(ItemElement111Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_111)
          .build();
}
