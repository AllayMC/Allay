package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemFireChargeStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireChargeStackInitializer {
  static void init() {
    ItemTypes.FIRE_CHARGE_TYPE = ItemTypeBuilder
            .builder(ItemFireChargeStack.class)
            .vanillaItem(VanillaItemId.FIRE_CHARGE)
            .build();
  }
}
