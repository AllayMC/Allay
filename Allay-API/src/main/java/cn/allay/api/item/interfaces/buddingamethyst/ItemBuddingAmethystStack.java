package cn.allay.api.item.interfaces.buddingamethyst;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBuddingAmethystStack extends ItemStack {
    ItemType<ItemBuddingAmethystStack> BUDDING_AMETHYST_TYPE = ItemTypeBuilder
            .builder(ItemBuddingAmethystStack.class)
            .vanillaItem(VanillaItemId.BUDDING_AMETHYST)
            .build();
}
