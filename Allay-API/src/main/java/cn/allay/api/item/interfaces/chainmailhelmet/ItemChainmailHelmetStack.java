package cn.allay.api.item.interfaces.chainmailhelmet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemChainmailHelmetStack extends ItemStack {
    ItemType<ItemChainmailHelmetStack> CHAINMAIL_HELMET_TYPE = ItemTypeBuilder
            .builder(ItemChainmailHelmetStack.class)
            .vanillaItem(VanillaItemId.CHAINMAIL_HELMET)
            .build();
}
