package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemEndGatewayStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemEndGatewayStackInitializer {
  static void init() {
    ItemTypes.END_GATEWAY_TYPE = ItemTypeBuilder
            .builder(ItemEndGatewayStack.class)
            .vanillaItem(VanillaItemId.END_GATEWAY)
            .build();
  }
}
