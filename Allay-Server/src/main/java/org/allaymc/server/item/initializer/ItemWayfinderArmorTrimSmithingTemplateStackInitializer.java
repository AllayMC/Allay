package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemWayfinderArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemWayfinderArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemWayfinderArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
