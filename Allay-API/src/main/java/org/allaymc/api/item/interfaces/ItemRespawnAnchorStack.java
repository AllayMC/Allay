package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRespawnAnchorStack extends ItemStack {
  ItemType<ItemRespawnAnchorStack> RESPAWN_ANCHOR_TYPE = ItemTypeBuilder
          .builder(ItemRespawnAnchorStack.class)
          .vanillaItem(VanillaItemId.RESPAWN_ANCHOR)
          .build();
}
