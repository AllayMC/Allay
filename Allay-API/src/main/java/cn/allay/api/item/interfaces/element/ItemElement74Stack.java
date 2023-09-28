package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement74Stack extends ItemStack {
  ItemType<ItemElement74Stack> ELEMENT_74_TYPE = ItemTypeBuilder
          .builder(ItemElement74Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_74)
          .build();
}
