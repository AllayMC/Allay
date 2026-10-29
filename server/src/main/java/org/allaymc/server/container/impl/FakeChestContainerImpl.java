package org.allaymc.server.container.impl;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.allaymc.server.player.AllayPlayer;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
public class FakeChestContainerImpl extends FakeContainerImpl {
    public FakeChestContainerImpl() {
        super(ContainerTypes.FAKE_CHEST);
    }

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

        var allayPlayer = (AllayPlayer) player;
        allayPlayer.sendPacket(allayPlayer.getProtocol().getEncoder().encodeBlockEntityData(
                pos,
                nbt.build()
        ));

        this.fakeBlockPositions.put(player, new Vector3ic[]{pos});
    }
}
