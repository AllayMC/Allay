package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFloweringAzaleaStack extends ItemStack {
  ItemType<ItemFloweringAzaleaStack> FLOWERING_AZALEA_TYPE = ItemTypeBuilder
          .builder(ItemFloweringAzaleaStack.class)
          .vanillaItem(VanillaItemId.FLOWERING_AZALEA)
          .build();
}
