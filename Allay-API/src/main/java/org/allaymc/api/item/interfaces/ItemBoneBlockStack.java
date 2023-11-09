package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneBlockStack extends ItemStack {
  ItemType<ItemBoneBlockStack> BONE_BLOCK_TYPE = ItemTypeBuilder
          .builder(ItemBoneBlockStack.class)
          .vanillaItem(VanillaItemId.BONE_BLOCK)
          .build();
}
