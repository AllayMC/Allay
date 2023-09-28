package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement24Stack extends ItemStack {
  ItemType<ItemElement24Stack> ELEMENT_24_TYPE = ItemTypeBuilder
          .builder(ItemElement24Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_24)
          .build();
}
