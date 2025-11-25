package org.allaymc.server.container.impl;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.player.Player;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;
import org.joml.Vector3ic;

import static org.allaymc.server.network.NetworkHelper.toNetwork;

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

        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(toNetwork(pos));
        packet.setData(nbt.build());
        player.sendPacket(packet);

        this.fakeBlockPositions.put(player, new Vector3ic[]{pos});
    }
}
