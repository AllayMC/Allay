package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemInvisibleBedrockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemInvisibleBedrockStackInitializer {
  static void init() {
    ItemTypes.INVISIBLE_BEDROCK_TYPE = ItemTypeBuilder
            .builder(ItemInvisibleBedrockStack.class)
            .vanillaItem(VanillaItemId.INVISIBLE_BEDROCK)
            .build();
  }
}
