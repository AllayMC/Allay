package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.block.component.event.CBlockOnPlaceEvent;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.ShulkerBoxContainerImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * @author IWareQ | daoge_cmd
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl {

    protected static final String TAG_FACING = "facing";

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
        ShulkerBoxContainerImpl container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                BlockEventPacket pk = new BlockEventPacket();
                pk.setBlockPosition(position.toNetwork());
                pk.setEventType(1);
                pk.setEventData(2);

                position.dimension().addSound(
                        position.x() + 0.5f,
                        position.y() + 0.5f,
                        position.z() + 0.5f,
                        SimpleSound.SHULKER_BOX_OPEN
                );
                sendPacketToViewers(pk);
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                BlockEventPacket pk = new BlockEventPacket();
                pk.setBlockPosition(position.toNetwork());
                pk.setEventType(1);
                pk.setEventData(0);

                position.dimension().addSound(
                        position.x() + 0.5f,
                        position.y() + 0.5f,
                        position.z() + 0.5f,
                        SimpleSound.SHULKER_BOX_CLOSE
                );
                sendPacketToViewers(pk);
            }
        });
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_FACING, facing -> this.facing = BlockFace.fromIndex(facing));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder()
                .putByte(TAG_FACING, (byte) facing.ordinal())
                .build();
    }

    @Override
    public void onBlockPlace(CBlockOnPlaceEvent event) {
        super.onBlockPlace(event);

        var placementInfo = event.getPlacementInfo();
        if (placementInfo == null) return;

        facing = placementInfo.blockFace();
    }
}
