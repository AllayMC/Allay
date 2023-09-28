package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement31Stack extends ItemStack {
  ItemType<ItemElement31Stack> ELEMENT_31_TYPE = ItemTypeBuilder
          .builder(ItemElement31Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_31)
          .build();
}
