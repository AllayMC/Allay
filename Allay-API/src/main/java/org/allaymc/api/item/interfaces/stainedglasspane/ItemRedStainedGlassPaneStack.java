package org.allaymc.api.item.interfaces.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedStainedGlassPaneStack extends ItemStack {
  ItemType<ItemRedStainedGlassPaneStack> RED_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemRedStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.RED_STAINED_GLASS_PANE)
          .build();
}