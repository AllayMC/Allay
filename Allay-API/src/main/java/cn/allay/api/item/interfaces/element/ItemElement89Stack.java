package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement89Stack extends ItemStack {
  ItemType<ItemElement89Stack> ELEMENT_89_TYPE = ItemTypeBuilder
          .builder(ItemElement89Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_89)
          .build();
}
