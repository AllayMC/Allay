package cn.allay.api.item.interfaces.netheriteshovel;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteShovelStack extends ItemStack {
    ItemType<ItemNetheriteShovelStack> NETHERITE_SHOVEL_TYPE = ItemTypeBuilder
            .builder(ItemNetheriteShovelStack.class)
            .vanillaItem(VanillaItemId.NETHERITE_SHOVEL)
            .build();
}
