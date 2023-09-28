package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement27Stack extends ItemStack {
  ItemType<ItemElement27Stack> ELEMENT_27_TYPE = ItemTypeBuilder
          .builder(ItemElement27Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_27)
          .build();
}
