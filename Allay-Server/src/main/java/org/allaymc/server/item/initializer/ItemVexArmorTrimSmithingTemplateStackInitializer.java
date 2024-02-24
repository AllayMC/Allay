package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemVexArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVexArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.VEX_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemVexArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.VEX_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
