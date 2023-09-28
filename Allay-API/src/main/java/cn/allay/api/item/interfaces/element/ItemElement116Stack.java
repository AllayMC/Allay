package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement116Stack extends ItemStack {
  ItemType<ItemElement116Stack> ELEMENT_116_TYPE = ItemTypeBuilder
          .builder(ItemElement116Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_116)
          .build();
}
