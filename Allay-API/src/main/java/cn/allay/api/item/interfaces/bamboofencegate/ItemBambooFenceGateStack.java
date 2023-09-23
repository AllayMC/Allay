package cn.allay.api.item.interfaces.bamboofencegate;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooFenceGateStack extends ItemStack {
    ItemType<ItemBambooFenceGateStack> BAMBOO_FENCE_GATE_TYPE = ItemTypeBuilder
            .builder(ItemBambooFenceGateStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_FENCE_GATE)
            .build();
}
