package org.allaymc.api.item.interfaces.piston;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPistonStack extends ItemStack {
    ItemType<ItemPistonStack> PISTON_TYPE = ItemTypeBuilder
            .builder(ItemPistonStack.class)
            .vanillaItem(VanillaItemId.PISTON)
            .build();
}
