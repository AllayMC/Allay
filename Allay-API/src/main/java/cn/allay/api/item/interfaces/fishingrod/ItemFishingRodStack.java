package cn.allay.api.item.interfaces.fishingrod;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFishingRodStack extends ItemStack {
    ItemType<ItemFishingRodStack> FISHING_ROD_TYPE = ItemTypeBuilder
            .builder(ItemFishingRodStack.class)
            .vanillaItem(VanillaItemId.FISHING_ROD)
            .build();
}
