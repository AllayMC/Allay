package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.api.world.sound.Sound;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * @author daoge_cmd
 */
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;
    @Dependency
    private BlockEntityPairableComponent pairableComponent;

    public BlockEntityChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);

        if (containerHolderComponent instanceof BlockEntityChestContainerHolderComponentImpl containerHolder) {
            var doubleChestContainer = containerHolder.getDoubleChestContainer();
            doubleChestContainer.addOpenListener(viewer -> {
                if (doubleChestContainer.getViewers().size() == 1) {
                    addSoundAndSendBlockEvent(getPosition(), SimpleSound.CHEST_OPEN, 2);
                    addSoundAndSendBlockEvent(pairableComponent.getPair().getPosition(), SimpleSound.CHEST_OPEN, 2);
                }
            });
            doubleChestContainer.addCloseListener(viewer -> {
                if (doubleChestContainer.getViewers().isEmpty()) {
                    addSoundAndSendBlockEvent(getPosition(), SimpleSound.CHEST_CLOSE, 0);
                    addSoundAndSendBlockEvent(pairableComponent.getPair().getPosition(), SimpleSound.CHEST_CLOSE, 0);
                }
            });
        }

        var container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                addSoundAndSendBlockEvent(getPosition(), SimpleSound.CHEST_OPEN, 2);
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                addSoundAndSendBlockEvent(getPosition(), SimpleSound.CHEST_CLOSE, 0);
            }
        });
    }

    private void addSoundAndSendBlockEvent(Position3ic pos, Sound sound, int eventData) {
        var packet = new BlockEventPacket();
        packet.setBlockPosition(pos.toNetwork());
        packet.setEventType(1);
        packet.setEventData(eventData);

        pos.dimension().addSound(MathUtils.center(pos), sound);
        sendPacketToViewers(packet);
    }
}
