package cn.allay.api.item.interfaces.strippedwarpedstem;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStrippedWarpedStemStack extends ItemStack {
    ItemType<ItemStrippedWarpedStemStack> STRIPPED_WARPED_STEM_TYPE = ItemTypeBuilder
            .builder(ItemStrippedWarpedStemStack.class)
            .vanillaItem(VanillaItemId.STRIPPED_WARPED_STEM)
            .build();
}
