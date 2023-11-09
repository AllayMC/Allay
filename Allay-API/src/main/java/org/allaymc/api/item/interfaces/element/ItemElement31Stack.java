package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement31Stack extends ItemStack {
  ItemType<ItemElement31Stack> ELEMENT_31_TYPE = ItemTypeBuilder
          .builder(ItemElement31Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_31)
          .build();
}
