package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        ChestContainer container = containerHolderComponent.getContainer();
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
                        "random.chestopen"
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
                        "random.chestclosed"
                );
                sendPacketToViewers(pk);
            }
        });
    }
}
