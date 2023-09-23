package cn.allay.api.item.interfaces.torchflowerseeds;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTorchflowerSeedsStack extends ItemStack {
    ItemType<ItemTorchflowerSeedsStack> TORCHFLOWER_SEEDS_TYPE = ItemTypeBuilder
            .builder(ItemTorchflowerSeedsStack.class)
            .vanillaItem(VanillaItemId.TORCHFLOWER_SEEDS)
            .build();
}
