package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDriedKelpBlockStack extends ItemStack {
  ItemType<ItemDriedKelpBlockStack> DRIED_KELP_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemDriedKelpBlockStack.class)
          .vanillaItem(VanillaItemId.DRIED_KELP_BLOCK)
          .build();
}
