package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemNetheriteUpgradeSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteUpgradeSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.NETHERITE_UPGRADE_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteUpgradeSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_UPGRADE_SMITHING_TEMPLATE)
            .build();
  }
}
