package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPitcherCropStack extends ItemStack {
  ItemType<ItemPitcherCropStack> PITCHER_CROP_TYPE = ItemTypeBuilder
          .builder(ItemPitcherCropStack.class)
          .vanillaItem(VanillaItemId.PITCHER_CROP)
          .build();
}
