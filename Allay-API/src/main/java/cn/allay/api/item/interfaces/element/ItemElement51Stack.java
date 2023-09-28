package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement51Stack extends ItemStack {
  ItemType<ItemElement51Stack> ELEMENT_51_TYPE = ItemTypeBuilder
          .builder(ItemElement51Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_51)
          .build();
}
