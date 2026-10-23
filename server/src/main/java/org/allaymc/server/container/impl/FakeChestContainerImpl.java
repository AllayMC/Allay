package org.allaymc.server.container.impl;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * {@code FakeChestContainerImpl} represents a synthetic twenty-seven-slot chest
 * interface for plugin use. Its block publication sends one chest state and optional
 * title metadata to the player, then records the position for delayed window opening and
 * later world-state restoration.
 *
 * @author daoge_cmd
 */
public class FakeChestContainerImpl extends FakeContainerImpl {
    /**
     * Creates empty synthetic chest storage using the fake chest identity.
     */
    public FakeChestContainerImpl() {
        super(ContainerTypes.FAKE_CHEST);
    }

    /**
     * Publishes one client-side chest and its block-entity coordinates before recording
     * the player's fake position. {@code customName}, when non-{@code null}, becomes the
     * title in that metadata; no world block is replaced.
     */
    @Override
    protected void sendFakeBlocks(Player player) {
        var pos = computeFakeBlockPos(player);
        player.viewBlockUpdate(pos, 0, BlockTypes.CHEST.getDefaultState());

        var nbt = NbtMap.builder()
                .putString("id", BlockEntityId.CHEST)
                .putInt("x", pos.x())
                .putInt("y", pos.y())
                .putInt("z", pos.z());

        if (this.customName != null) {
            nbt.putString("CustomName", this.customName);
        }

        // Publish chest metadata after the block state so the delayed open can
        // identify the fake chest and its optional title.
        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeBlockEntityData(
                pos,
                nbt.build()
        ));

        this.fakeBlockPositions.put(player, new Vector3ic[]{pos});
    }
}
