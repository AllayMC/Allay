package org.allaymc.api.item.interfaces.minecart;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMinecartStack extends ItemStack {
    ItemType<ItemMinecartStack> MINECART_TYPE = ItemTypeBuilder
            .builder(ItemMinecartStack.class)
            .vanillaItem(VanillaItemId.MINECART)
            .build();
}
