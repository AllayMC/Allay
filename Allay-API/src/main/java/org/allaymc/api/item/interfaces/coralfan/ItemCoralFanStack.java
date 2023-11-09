package org.allaymc.api.item.interfaces.coralfan;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanStack extends ItemStack {
    ItemType<ItemCoralFanStack> CORAL_FAN_TYPE = ItemTypeBuilder
            .builder(ItemCoralFanStack.class)
            .vanillaItem(VanillaItemId.CORAL_FAN)
            .build();
}
