package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement46Stack extends ItemStack {
  ItemType<ItemElement46Stack> ELEMENT_46_TYPE = ItemTypeBuilder
          .builder(ItemElement46Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_46)
          .build();
}
