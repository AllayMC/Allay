package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement88Stack extends ItemStack {
  ItemType<ItemElement88Stack> ELEMENT_88_TYPE = ItemTypeBuilder
          .builder(ItemElement88Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_88)
          .build();
}
