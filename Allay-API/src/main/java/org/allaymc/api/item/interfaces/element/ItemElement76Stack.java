package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement76Stack extends ItemStack {
  ItemType<ItemElement76Stack> ELEMENT_76_TYPE = ItemTypeBuilder
          .builder(ItemElement76Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_76)
          .build();
}
