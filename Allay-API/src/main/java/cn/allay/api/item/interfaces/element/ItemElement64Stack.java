package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement64Stack extends ItemStack {
  ItemType<ItemElement64Stack> ELEMENT_64_TYPE = ItemTypeBuilder
          .builder(ItemElement64Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_64)
          .build();
}
