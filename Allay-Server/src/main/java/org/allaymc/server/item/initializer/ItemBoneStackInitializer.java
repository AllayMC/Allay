package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBoneStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneStackInitializer {
  static void init() {
    ItemTypes.BONE_TYPE = ItemTypeBuilder
            .builder(ItemBoneStack.class)
            .vanillaItem(VanillaItemId.BONE)
            .build();
  }
}
