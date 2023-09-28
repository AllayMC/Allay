package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement95Stack extends ItemStack {
  ItemType<ItemElement95Stack> ELEMENT_95_TYPE = ItemTypeBuilder
          .builder(ItemElement95Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_95)
          .build();
}
