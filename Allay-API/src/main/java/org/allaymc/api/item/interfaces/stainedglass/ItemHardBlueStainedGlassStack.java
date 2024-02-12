package org.allaymc.api.item.interfaces.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardBlueStainedGlassStack extends ItemStack {
  ItemType<ItemHardBlueStainedGlassStack> HARD_BLUE_STAINED_GLASS_TYPE = ItemTypeBuilder
          .builder(ItemHardBlueStainedGlassStack.class)
          .vanillaItem(VanillaItemId.HARD_BLUE_STAINED_GLASS)
          .build();
}
