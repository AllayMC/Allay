package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement79Stack extends ItemStack {
  ItemType<ItemElement79Stack> ELEMENT_79_TYPE = ItemTypeBuilder
          .builder(ItemElement79Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_79)
          .build();
}
