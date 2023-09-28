package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement3Stack extends ItemStack {
  ItemType<ItemElement3Stack> ELEMENT_3_TYPE = ItemTypeBuilder
          .builder(ItemElement3Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_3)
          .build();
}
