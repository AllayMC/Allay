package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement83Stack extends ItemStack {
  ItemType<ItemElement83Stack> ELEMENT_83_TYPE = ItemTypeBuilder
          .builder(ItemElement83Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_83)
          .build();
}
