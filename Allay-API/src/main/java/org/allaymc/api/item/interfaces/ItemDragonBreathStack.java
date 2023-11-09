package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonBreathStack extends ItemStack {
  ItemType<ItemDragonBreathStack> DRAGON_BREATH_TYPE = ItemTypeBuilder
          .builder(ItemDragonBreathStack.class)
          .vanillaItem(VanillaItemId.DRAGON_BREATH)
          .build();
}
