package org.allaymc.server.container.impl;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;
import org.joml.Vector3ic;

import static org.allaymc.server.network.NetworkHelper.toNetwork;

/**
 * @author daoge_cmd
 */
public class FakeDoubleChestContainerImpl extends FakeContainerImpl {
    public FakeDoubleChestContainerImpl() {
        super(ContainerTypes.FAKE_DOUBLE_CHEST);
    }

    @Override
    protected void sendFakeBlocks(EntityPlayer player) {
        var pos1 = computeFakeBlockPos(player);
        var pos2 = BlockFace.EAST.offsetPos(pos1);

        player.viewBlockUpdate(pos1, 0, BlockTypes.CHEST.getDefaultState());
        player.viewBlockUpdate(pos2, 0, BlockTypes.CHEST.getDefaultState());
        sendFakeBlockEntities(player, pos1, pos2);

        this.fakeBlockPositions.put(player, new Vector3ic[]{pos1, pos2});
    }

    protected void sendFakeBlockEntities(EntityPlayer player, Vector3ic pos1, Vector3ic pos2) {
        sendFakeBlockEntity(player, pos1, pos2);
        sendFakeBlockEntity(player, pos2, pos1);
    }

    protected void sendFakeBlockEntity(EntityPlayer player, Vector3ic pos1, Vector3ic pos2) {
        var nbt = NbtMap.builder()
                .putString("id", BlockEntityId.CHEST)
                .putInt("x", pos1.x())
                .putInt("y", pos1.y())
                .putInt("z", pos1.z())
                .putInt("pairx", pos2.x())
                .putInt("pairz", pos2.z());

        if (this.customName != null) {
            nbt.putString("CustomName", this.customName);
        }

        var packet = new BlockEntityDataPacket();
        packet.setBlockPosition(toNetwork(pos1));
        packet.setData(nbt.build());
        player.sendPacket(packet);
    }
}
