package org.allaymc.api.item.interfaces.glasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassPaneStack extends ItemStack {
  ItemType<ItemGlassPaneStack> GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemGlassPaneStack.class)
          .vanillaItem(VanillaItemId.GLASS_PANE)
          .build();
}
