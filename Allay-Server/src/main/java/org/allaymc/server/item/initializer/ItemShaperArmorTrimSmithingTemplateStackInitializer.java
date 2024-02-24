package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemShaperArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShaperArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemShaperArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
