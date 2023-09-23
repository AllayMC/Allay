package cn.allay.api.item.interfaces.chainmailchestplate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailChestplateStack extends ItemStack {
    ItemType<ItemChainmailChestplateStack> CHAINMAIL_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemChainmailChestplateStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_CHESTPLATE)
            .build();
}
