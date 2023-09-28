package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement4Stack extends ItemStack {
  ItemType<ItemElement4Stack> ELEMENT_4_TYPE = ItemTypeBuilder
          .builder(ItemElement4Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_4)
          .build();
}
