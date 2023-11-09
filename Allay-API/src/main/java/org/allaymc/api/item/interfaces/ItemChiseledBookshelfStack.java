package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledBookshelfStack extends ItemStack {
  ItemType<ItemChiseledBookshelfStack> CHISELED_BOOKSHELF_TYPE = ItemTypeBuilder
          .builder(ItemChiseledBookshelfStack.class)
          .vanillaItem(VanillaItemId.CHISELED_BOOKSHELF)
          .build();
}
