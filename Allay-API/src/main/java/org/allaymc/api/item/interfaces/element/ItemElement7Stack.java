package org.allaymc.api.item.interfaces.element;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement7Stack extends ItemStack {
  ItemType<ItemElement7Stack> ELEMENT_7_TYPE = ItemTypeBuilder
          .builder(ItemElement7Stack.class)
          .vanillaItem(VanillaItemId.ELEMENT_7)
          .build();
}
