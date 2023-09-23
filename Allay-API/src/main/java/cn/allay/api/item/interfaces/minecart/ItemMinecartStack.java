package cn.allay.api.item.interfaces.minecart;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

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
