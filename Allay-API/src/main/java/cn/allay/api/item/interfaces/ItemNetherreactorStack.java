package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherreactorStack extends ItemStack {
    ItemType<ItemNetherreactorStack> NETHERREACTOR_TYPE = ItemTypeBuilder
            .builder(ItemNetherreactorStack.class)
            .vanillaItem(VanillaItemId.NETHERREACTOR)
            .build();
}
