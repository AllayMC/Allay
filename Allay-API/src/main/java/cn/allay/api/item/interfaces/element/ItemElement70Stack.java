package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement70Stack extends ItemStack {
  ItemType<ItemElement70Stack> ELEMENT_70_TYPE = ItemTypeBuilder
          .builder(ItemElement70Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_70)
          .build();
}
