package cn.allay.api.item.interfaces.chestminecart;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChestMinecartStack extends ItemStack {
    ItemType<ItemChestMinecartStack> CHEST_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemChestMinecartStack.class)
            .vanillaItem(VanillaItemId.CHEST_MINECART)
            .build();
}
