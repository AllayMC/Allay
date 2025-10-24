package org.allaymc.server.container.impl;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.container.ContainerTypes;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.server.blockentity.data.BlockEntityId;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEntityDataPacket;
import org.joml.RoundingMode;
import org.joml.Vector3i;
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
    protected void sendFakeBlock(ContainerViewer viewer) {
        if (viewer instanceof EntityPlayer player) {
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

            this.fakeBlockPositions.put(viewer, new Vector3ic[]{pos});
        }
    }

    @Override
    protected void removeFakeBlock(ContainerViewer viewer) {
        if (viewer instanceof EntityPlayer player) {
            var dimension = player.getDimension();
            for (var pos : this.fakeBlockPositions.getOrDefault(viewer, new Vector3ic[0])) {
                player.viewBlockUpdate(pos, 0, dimension.getBlockState(pos, 0));
            }
        }
    }

    protected Vector3ic computeFakeBlockPos(EntityPlayer player) {
        var pos = MathUtils.getDirectionVector(player.getLocation());
        var aabb = player.getAABB();
        pos.x *= -(1 + aabb.lengthX());
        pos.y *= -(1 + aabb.lengthY());
        pos.z *= -(1 + aabb.lengthZ());
        return pos.get(RoundingMode.FLOOR, new Vector3i());
    }
}
