package org.allaymc.server.utils;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.type.BlockEntityTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayNBTIOTest {
    @Test
    void testFromBlockStateNBT() {
        var block1 = BlockTypes.BAMBOO.getDefaultState();
        var nbt = block1.getBlockStateNBT();
        var block2 = NBTIO.getAPI().fromBlockStateNBT(nbt);
        assertEquals(block1, block2);
    }

    @Test
    void testFromItemStackNBT() {
        var item1 = ItemTypes.DIAMOND.createItemStack(1, 1);
        item1.addEnchantment(EnchantmentTypes.SHARPNESS, 1);
        item1.setLore(List.of("test"));
        item1.setCustomName("test");
        var nbt = item1.saveNBT();
        var item2 = NBTIO.getAPI().fromItemStackNBT(nbt);
        assertTrue(item1.canMerge(item2));
    }

    @Test
    void testFromEntityNBT() {
        var entity1 = EntityTypes.VILLAGER_V2.createEntity(
                EntityInitInfo
                        .builder()
                        .loc(Server.getInstance().getWorldPool().getGlobalSpawnPoint())
                        .build()
        );
        var nbt1 = entity1.saveNBT();
        var entity2 = NBTIO.getAPI().fromEntityNBT(Server.getInstance().getWorldPool().getGlobalSpawnPoint().dimension(), nbt1);
        var nbt2 = entity2.saveNBT();
        assertEquals(nbt1, nbt2);
    }

    @Test
    void testFromBlockEntityNBT() {
        var blockEntity1 = BlockEntityTypes.CHEST.createBlockEntity(
                BlockEntityInitInfo
                        .builder()
                        .dimension(Server.getInstance().getWorldPool().getDefaultWorld().getOverWorld())
                        .pos(0, 0, 0)
                        .build()
        );
        var nbt1 = blockEntity1.saveNBT();
        var blockEntity2 = NBTIO.getAPI().fromBlockEntityNBT(Server.getInstance().getWorldPool().getDefaultWorld().getOverWorld(), nbt1);
        var nbt2 = blockEntity2.saveNBT();
        assertEquals(nbt1, nbt2);
    }
}