package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement106Stack extends ItemStack {
  ItemType<ItemElement106Stack> ELEMENT_106_TYPE = ItemTypeBuilder
          .builder(ItemElement106Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_106)
          .build();
}
