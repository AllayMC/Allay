package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement104Stack extends ItemStack {
  ItemType<ItemElement104Stack> ELEMENT_104_TYPE = ItemTypeBuilder
          .builder(ItemElement104Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_104)
          .build();
}
