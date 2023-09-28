package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement59Stack extends ItemStack {
  ItemType<ItemElement59Stack> ELEMENT_59_TYPE = ItemTypeBuilder
          .builder(ItemElement59Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_59)
          .build();
}
