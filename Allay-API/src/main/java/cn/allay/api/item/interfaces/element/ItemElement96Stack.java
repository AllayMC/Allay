package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement96Stack extends ItemStack {
  ItemType<ItemElement96Stack> ELEMENT_96_TYPE = ItemTypeBuilder
          .builder(ItemElement96Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_96)
          .build();
}
