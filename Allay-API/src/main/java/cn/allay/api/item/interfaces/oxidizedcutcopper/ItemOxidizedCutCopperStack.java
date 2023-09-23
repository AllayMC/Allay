package cn.allay.api.item.interfaces.oxidizedcutcopper;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOxidizedCutCopperStack extends ItemStack {
    ItemType<ItemOxidizedCutCopperStack> OXIDIZED_CUT_COPPER_TYPE = ItemTypeBuilder
            .builder(ItemOxidizedCutCopperStack.class)
            .vanillaItem(VanillaItemId.OXIDIZED_CUT_COPPER)
            .build();
}
