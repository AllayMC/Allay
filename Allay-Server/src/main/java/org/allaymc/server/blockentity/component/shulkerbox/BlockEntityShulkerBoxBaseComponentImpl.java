package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.block.component.event.BlockOnPlaceEvent;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.impl.ShulkerBoxContainer;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * Allay Project 2024/06/18
 *
 * @author IWareQ
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityShulkerBox> {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    private BlockFace facing = BlockFace.UP;

    public BlockEntityShulkerBoxBaseComponentImpl(BlockEntityInitInfo<BlockEntityShulkerBox> info) {
        super(info);
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        super.onInitFinish(initInfo);
        var container = containerHolderComponent.<ShulkerBoxContainer>getContainer();
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
                        "random.shulkerboxopen"
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
                        "random.shulkerboxclosed"
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

    @EventHandler
    public void onPlace(BlockOnPlaceEvent event) {
        var player = event.getPlayer();
        if (player == null) return;

        if (Math.abs(player.getLocation().x() - position.x()) < 2 && Math.abs(player.getLocation().z() - position.z()) < 2) {
            var y = player.getLocation().y() + player.getEyeHeight();
            if (y - position.y() > 2) {
                facing = BlockFace.UP;
            } else if (position.y() - y > 0) {
                facing = BlockFace.DOWN;
            } else {
                facing = player.getHorizontalFace().opposite();
            }
        } else {
            facing = player.getHorizontalFace().opposite();
        }
    }
}
