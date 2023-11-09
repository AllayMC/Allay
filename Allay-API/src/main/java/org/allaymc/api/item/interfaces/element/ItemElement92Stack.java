package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
