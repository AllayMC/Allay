package cn.allay.api.item.interfaces.elderguardianspawnegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElderGuardianSpawnEggStack extends ItemStack {
    ItemType<ItemElderGuardianSpawnEggStack> ELDER_GUARDIAN_SPAWN_EGG_TYPE = ItemTypeBuilder
            .builder(ItemElderGuardianSpawnEggStack.class)
            .vanillaItem(VanillaItemId.ELDER_GUARDIAN_SPAWN_EGG)
            .build();
}
