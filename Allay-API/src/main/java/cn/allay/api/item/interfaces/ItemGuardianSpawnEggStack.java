package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGuardianSpawnEggStack extends ItemStack {
    ItemType<ItemGuardianSpawnEggStack> GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemGuardianSpawnEggStack.class)
            .vanillaItem(VanillaItemId.GUARDIAN_SPAWN_EGG)
            .build();
}
