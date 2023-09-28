package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement94Stack extends ItemStack {
  ItemType<ItemElement94Stack> ELEMENT_94_TYPE = ItemTypeBuilder
          .builder(ItemElement94Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_94)
          .build();
}
