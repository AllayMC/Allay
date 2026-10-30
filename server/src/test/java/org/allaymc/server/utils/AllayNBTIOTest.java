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
import org.allaymc.server.network.ProtocolInfo;
import org.allaymc.testutils.AllayTestExtension;
import org.allaymc.updater.block.BlockStateUpdaters;
import org.cloudburstmc.nbt.NbtMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * {@code AllayNBTIOTest} verifies game-object NBT round trips and compatibility updates using
 * initialized runtime registries.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayNBTIOTest {
    /**
     * Saves the default bamboo state and reconstructs it through the installed NBT decoder. The
     * resulting state must equal the original registered block state.
     */
    @Test
    void testFromBlockStateNBT() {
        var block1 = BlockTypes.BAMBOO.getDefaultState();
        var nbt = block1.getBlockStateNBT();
        var block2 = NBTIO.getAPI().fromBlockStateNBT(nbt);
        assertEquals(block1, block2);
    }

    /**
     * Updates old sulfur NBT whose state compound lacks the current property. The updater must
     * supply the dry value, and full decoding must select the default sulfur state.
     */
    @Test
    void upgradesPotentSulfurWithoutAStateProperty() {
        var oldState = NbtMap.builder()
                .putString("name", "minecraft:potent_sulfur")
                .putCompound("states", NbtMap.EMPTY)
                .build();

        var updated = BlockStateUpdaters.updateBlockState(oldState, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());

        assertEquals("dry", updated.getCompound("states").getString("potent_sulfur_state"));
        assertEquals(BlockTypes.POTENT_SULFUR.getDefaultState(), NBTIO.getAPI().fromBlockStateNBT(oldState));
    }

    /**
     * Marks every sulfur state as old-version NBT and applies the updater. Existing serialized
     * properties must remain equal, and decoding must recover the original state for each case.
     */
    @Test
    void preservesExistingPotentSulfurStatesDuringUpgrade() {
        for (var state : BlockTypes.POTENT_SULFUR.getAllStates()) {
            var oldState = state.getBlockStateNBT().toBuilder().putInt("version", 0).build();
            var updated = BlockStateUpdaters.updateBlockState(oldState, ProtocolInfo.BLOCK_STATE_UPDATER.getVersion());

            assertEquals(oldState.getCompound("states"), updated.getCompound("states"));
            assertEquals(state, NBTIO.getAPI().fromBlockStateNBT(oldState));
        }
    }

    /**
     * Saves a diamond item with metadata, enchantment, lore, and custom name. The decoded stack
     * must remain merge-compatible with the original item after its NBT round trip.
     */
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

    /**
     * Reads the legacy photo identifier with a nondefault stack count. Item
     * migration must select the renamed photo item and preserve the saved count.
     */
    @Test
    void upgradesLegacyPhotoItems() {
        var oldItem = NbtMap.builder()
                .putString("Name", "minecraft:photo")
                .putByte("Count", (byte) 3)
                .putShort("Damage", (short) 0)
                .build();

        var item = NBTIO.getAPI().fromItemStackNBT(oldItem);

        assertEquals(ItemTypes.PHOTO_ITEM, item.getItemType());
        assertEquals(3, item.getCount());
    }

    /**
     * Saves a villager and reconstructs it in the spawn dimension. Saving the reconstructed entity
     * must produce NBT equal to the original entity's persisted representation.
     */
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

    /**
     * Saves a chest block entity and reconstructs it in the same dimension. The reconstructed
     * entity must save the same NBT data as the original chest.
     */
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
