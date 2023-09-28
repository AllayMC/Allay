package cn.allay.api.item.interfaces.element;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement73Stack extends ItemStack {
  ItemType<ItemElement73Stack> ELEMENT_73_TYPE = ItemTypeBuilder
          .builder(ItemElement73Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_73)
          .build();
}
