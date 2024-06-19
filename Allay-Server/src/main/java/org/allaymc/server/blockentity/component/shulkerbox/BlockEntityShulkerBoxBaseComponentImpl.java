package org.allaymc.server.blockentity.component.shulkerbox;

import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityShulkerBox;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.ShulkerBoxContainer;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

import java.util.Objects;

/*
 * Allay Project 2024/06/18
 *
 * @author IWareQ
 */
public class BlockEntityShulkerBoxBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityShulkerBox> {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

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
        if (nbt.containsKey("Items"))
            Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.SHULKER_BOX)).loadNBT(nbt.getList("Items", NbtType.COMPOUND));
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putList(
                "Items",
                NbtType.COMPOUND,
                Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.SHULKER_BOX)).saveNBT()
        ).build();
    }
}
