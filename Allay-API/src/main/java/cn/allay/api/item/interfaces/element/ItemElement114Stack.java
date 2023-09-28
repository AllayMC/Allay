package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement114Stack extends ItemStack {
  ItemType<ItemElement114Stack> ELEMENT_114_TYPE = ItemTypeBuilder
          .builder(ItemElement114Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_114)
          .build();
}
