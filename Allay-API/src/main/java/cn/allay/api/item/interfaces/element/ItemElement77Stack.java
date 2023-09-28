package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement77Stack extends ItemStack {
  ItemType<ItemElement77Stack> ELEMENT_77_TYPE = ItemTypeBuilder
          .builder(ItemElement77Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_77)
          .build();
}
