package org.allaymc.api.item.interfaces.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaStainedGlassPaneStack extends ItemStack {
  ItemType<ItemMagentaStainedGlassPaneStack> MAGENTA_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemMagentaStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_STAINED_GLASS_PANE)
          .build();
}
