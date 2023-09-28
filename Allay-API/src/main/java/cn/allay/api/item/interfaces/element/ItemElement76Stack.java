package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement76Stack extends ItemStack {
  ItemType<ItemElement76Stack> ELEMENT_76_TYPE = ItemTypeBuilder
          .builder(ItemElement76Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_76)
          .build();
}
