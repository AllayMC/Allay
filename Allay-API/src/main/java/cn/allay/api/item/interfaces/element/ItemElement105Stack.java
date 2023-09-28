package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement105Stack extends ItemStack {
  ItemType<ItemElement105Stack> ELEMENT_105_TYPE = ItemTypeBuilder
          .builder(ItemElement105Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_105)
          .build();
}
