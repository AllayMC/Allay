package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement30Stack extends ItemStack {
  ItemType<ItemElement30Stack> ELEMENT_30_TYPE = ItemTypeBuilder
          .builder(ItemElement30Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_30)
          .build();
}
