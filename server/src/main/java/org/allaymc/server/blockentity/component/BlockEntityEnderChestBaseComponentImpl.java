package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.component.BlockEntityEnderChestBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.world.sound.SoundNames;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * @author IWareQ
 */
public class BlockEntityEnderChestBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityEnderChestBaseComponent {
    @Getter
    protected int viewersCount = 0;

    public BlockEntityEnderChestBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @Override
    public void setViewersCount(int count) {
        this.viewersCount = count;

        if (viewersCount == 1) {
            BlockEventPacket packet = new BlockEventPacket();
            packet.setBlockPosition(position.toNetwork());
            packet.setEventType(1);
            packet.setEventData(2);
            sendPacketToViewers(packet);

            position.dimension().addSound(MathUtils.center(position), SoundNames.RANDOM_ENDERCHESTOPEN);
        } else if (viewersCount == 0) {
            BlockEventPacket packet = new BlockEventPacket();
            packet.setBlockPosition(position.toNetwork());
            packet.setEventType(1);
            packet.setEventData(0);
            sendPacketToViewers(packet);

            position.dimension().addSound(MathUtils.center(position), SoundNames.RANDOM_ENDERCHESTCLOSED);
        }
    }
}
