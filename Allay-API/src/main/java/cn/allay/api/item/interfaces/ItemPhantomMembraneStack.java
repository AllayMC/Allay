package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPhantomMembraneStack extends ItemStack {
    ItemType<ItemPhantomMembraneStack> PHANTOM_MEMBRANE_TYPE = ItemTypeBuilder
            .builder(ItemPhantomMembraneStack.class)
            .vanillaItem(VanillaItemId.PHANTOM_MEMBRANE)
            .build();
}
