package cn.allay.api.item.interfaces.crimsonfungus;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCrimsonFungusStack extends ItemStack {
    ItemType<ItemCrimsonFungusStack> CRIMSON_FUNGUS_TYPE = ItemTypeBuilder
            .builder(ItemCrimsonFungusStack.class)
            .vanillaItem(VanillaItemId.CRIMSON_FUNGUS)
            .build();
}
