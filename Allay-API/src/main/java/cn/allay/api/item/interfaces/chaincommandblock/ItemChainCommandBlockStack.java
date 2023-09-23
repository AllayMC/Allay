package cn.allay.api.item.interfaces.chaincommandblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainCommandBlockStack extends ItemStack {
    ItemType<ItemChainCommandBlockStack> CHAIN_COMMAND_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemChainCommandBlockStack.class)
            .vanillaItem(VanillaItemId.CHAIN_COMMAND_BLOCK)
            .build();
}
