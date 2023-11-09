package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWrittenBookStack extends ItemStack {
  ItemType<ItemWrittenBookStack> WRITTEN_BOOK_TYPE = ItemTypeBuilder
          .builder(ItemWrittenBookStack.class)
          .vanillaItem(VanillaItemId.WRITTEN_BOOK)
          .build();
}
