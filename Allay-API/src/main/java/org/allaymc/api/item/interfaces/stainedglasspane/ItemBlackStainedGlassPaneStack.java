package org.allaymc.api.item.interfaces.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBlackStainedGlassPaneStack extends ItemStack {
  ItemType<ItemBlackStainedGlassPaneStack> BLACK_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemBlackStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.BLACK_STAINED_GLASS_PANE)
          .build();
}
