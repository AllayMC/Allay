package org.allaymc.server.item.initializer;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.interfaces.ItemSentryArmorTrimSmithingTemplateStack;
import org.allaymc.api.item.type.ItemTypeBuilder;
import org.allaymc.api.item.type.ItemTypes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSentryArmorTrimSmithingTemplateStackInitializer {
  static void init() {
    ItemTypes.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE_TYPE = ItemTypeBuilder
            .builder(ItemSentryArmorTrimSmithingTemplateStack.class)
            .vanillaItem(VanillaItemId.SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE)
            .build();
  }
}
