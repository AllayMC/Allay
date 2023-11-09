package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement103Stack extends ItemStack {
  ItemType<ItemElement103Stack> ELEMENT_103_TYPE = ItemTypeBuilder
          .builder(ItemElement103Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_103)
          .build();
}
