package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement69Stack extends ItemStack {
  ItemType<ItemElement69Stack> ELEMENT_69_TYPE = ItemTypeBuilder
          .builder(ItemElement69Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_69)
          .build();
}
