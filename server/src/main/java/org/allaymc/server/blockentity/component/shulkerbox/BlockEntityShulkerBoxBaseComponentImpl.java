package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.container.impl.ShulkerBoxContainer;
import org.allaymc.api.world.Sound;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * @author IWareQ
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    private BlockFace facing = BlockFace.UP;

    public BlockEntityShulkerBoxBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @Override
    @OnInitFinish
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        ShulkerBoxContainer container = containerHolderComponent.getContainer();
        container.addOnOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                BlockEventPacket pk = new BlockEventPacket();
                pk.setBlockPosition(position.toNetwork());
                pk.setEventType(1);
                pk.setEventData(2);

                position.dimension().addSound(
                        position.x() + 0.5f,
                        position.y() + 0.5f,
                        position.z() + 0.5f,
                        Sound.RANDOM_SHULKERBOXOPEN
                );
                sendPacketToViewers(pk);
            }
        });
        container.addOnCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                BlockEventPacket pk = new BlockEventPacket();
                pk.setBlockPosition(position.toNetwork());
                pk.setEventType(1);
                pk.setEventData(0);

                position.dimension().addSound(
                        position.x() + 0.5f,
                        position.y() + 0.5f,
                        position.z() + 0.5f,
                        Sound.RANDOM_SHULKERBOXCLOSED
                );
                sendPacketToViewers(pk);
            }
        });
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte("facing", facing -> this.facing = BlockFace.fromId(facing));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putByte("facing", (byte) facing.ordinal())
                .build();
    }

    @Override
    public void onPlace(CBlockOnPlaceEvent event) {
        super.onPlace(event);

        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) return;

        facing = placementInfo.blockFace();
    }
}
