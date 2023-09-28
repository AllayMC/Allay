package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement102Stack extends ItemStack {
  ItemType<ItemElement102Stack> ELEMENT_102_TYPE = ItemTypeBuilder
          .builder(ItemElement102Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_102)
          .build();
}
