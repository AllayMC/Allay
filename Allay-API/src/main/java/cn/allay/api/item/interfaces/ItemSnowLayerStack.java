package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSnowLayerStack extends ItemStack {
    ItemType<ItemSnowLayerStack> SNOW_LAYER_TYPE = ItemTypeBuilder
            .builder(ItemSnowLayerStack.class)
            .vanillaItem(VanillaItemId.SNOW_LAYER)
            .build();
}
