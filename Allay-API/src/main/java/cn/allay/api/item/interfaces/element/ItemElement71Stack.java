package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement71Stack extends ItemStack {
  ItemType<ItemElement71Stack> ELEMENT_71_TYPE = ItemTypeBuilder
          .builder(ItemElement71Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_71)
          .build();
}
