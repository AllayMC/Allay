package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChiseledTuffStack extends ItemStack {
  ItemType<ItemChiseledTuffStack> CHISELED_TUFF_TYPE = ItemTypeBuilder
          .builder(ItemChiseledTuffStack.class)
          .vanillaItem(VanillaItemId.CHISELED_TUFF)
          .build();
}
