package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement42Stack extends ItemStack {
  ItemType<ItemElement42Stack> ELEMENT_42_TYPE = ItemTypeBuilder
          .builder(ItemElement42Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_42)
          .build();
}
