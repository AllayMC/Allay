package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement92Stack extends ItemStack {
  ItemType<ItemElement92Stack> ELEMENT_92_TYPE = ItemTypeBuilder
          .builder(ItemElement92Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_92)
          .build();
}
