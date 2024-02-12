package org.allaymc.api.item.interfaces.stainedglasspane;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardOrangeStainedGlassPaneStack extends ItemStack {
  ItemType<ItemHardOrangeStainedGlassPaneStack> HARD_ORANGE_STAINED_GLASS_PANE_TYPE = ItemTypeBuilder
          .builder(ItemHardOrangeStainedGlassPaneStack.class)
          .vanillaItem(VanillaItemId.HARD_ORANGE_STAINED_GLASS_PANE)
          .build();
}
