package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSpireArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpireArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemSpireArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
