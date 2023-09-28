package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement11Stack extends ItemStack {
  ItemType<ItemElement11Stack> ELEMENT_11_TYPE = ItemTypeBuilder
          .builder(ItemElement11Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_11)
          .build();
}
