package org.allaymc.api.item.interfaces.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceFenceGateStack extends ItemStack {
  ItemType<ItemSpruceFenceGateStack> SPRUCE_FENCE_GATE_TYPE = ItemTypeBuilder
          .builder(ItemSpruceFenceGateStack.class)
          .vanillaItem(VanillaItemId.SPRUCE_FENCE_GATE)
          .build();
}
