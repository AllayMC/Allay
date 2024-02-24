package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBoneBlockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneBlockStackInitializer {
  static void init() {
    ItemTypes.BONE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBoneBlockStack.class)
            .vanillaItem(VanillaItemId.BONE_BLOCK)
            .build();
  }
}
