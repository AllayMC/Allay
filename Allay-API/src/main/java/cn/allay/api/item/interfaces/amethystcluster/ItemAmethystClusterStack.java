package cn.allay.api.item.interfaces.amethystcluster;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAmethystClusterStack extends ItemStack {
    ItemType<ItemAmethystClusterStack> AMETHYST_CLUSTER_TYPE = ItemTypeBuilder
            .builder(ItemAmethystClusterStack.class)
            .vanillaItem(VanillaItemId.AMETHYST_CLUSTER)
            .build();
}
