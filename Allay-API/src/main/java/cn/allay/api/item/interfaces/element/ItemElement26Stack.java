package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement26Stack extends ItemStack {
  ItemType<ItemElement26Stack> ELEMENT_26_TYPE = ItemTypeBuilder
          .builder(ItemElement26Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_26)
          .build();
}
