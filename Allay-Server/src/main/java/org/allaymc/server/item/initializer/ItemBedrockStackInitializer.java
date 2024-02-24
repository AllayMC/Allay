package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemBedrockStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBedrockStackInitializer {
  static void init() {
    ItemTypes.BEDROCK_TYPE = ItemTypeBuilder
            .builder(ItemBedrockStack.class)
            .vanillaItem(VanillaItemId.BEDROCK)
            .build();
  }
}
