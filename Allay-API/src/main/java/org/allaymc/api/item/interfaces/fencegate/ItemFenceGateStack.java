package org.allaymc.api.item.interfaces.fencegate;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFenceGateStack extends ItemStack {
    ItemType<ItemFenceGateStack> FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemFenceGateStack.class)
            .vanillaItem(VanillaItemId.FENCE_GATE)
            .build();
}
