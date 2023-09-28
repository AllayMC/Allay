package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement78Stack extends ItemStack {
  ItemType<ItemElement78Stack> ELEMENT_78_TYPE = ItemTypeBuilder
          .builder(ItemElement78Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_78)
          .build();
}
