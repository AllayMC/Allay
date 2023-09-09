package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCommandBlockMinecartStack extends ItemStack {
    ItemType<ItemCommandBlockMinecartStack> COMMAND_BLOCK_MINECART_TYPE = ItemTypeBuilder
            .builder(ItemCommandBlockMinecartStack.class)
            .vanillaItem(VanillaItemId.COMMAND_BLOCK_MINECART)
            .build();
}
