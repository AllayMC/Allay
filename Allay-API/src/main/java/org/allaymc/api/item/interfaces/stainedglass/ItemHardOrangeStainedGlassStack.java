package org.allaymc.api.item.interfaces.stainedglass;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardOrangeStainedGlassStack extends ItemStack {
  ItemType<ItemHardOrangeStainedGlassStack> HARD_ORANGE_STAINED_GLASS_TYPE = ItemTypeBuilder
          .builder(ItemHardOrangeStainedGlassStack.class)
          .vanillaItem(VanillaItemId.HARD_ORANGE_STAINED_GLASS)
          .build();
}
