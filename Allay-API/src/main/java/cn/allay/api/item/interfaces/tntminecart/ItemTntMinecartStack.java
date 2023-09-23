package cn.allay.api.item.interfaces.tntminecart;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTntMinecartStack extends ItemStack {
    ItemType<ItemTntMinecartStack> TNT_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemTntMinecartStack.class)
            .vanillaItem(VanillaItemId.TNT_MINECART)
            .build();
}
