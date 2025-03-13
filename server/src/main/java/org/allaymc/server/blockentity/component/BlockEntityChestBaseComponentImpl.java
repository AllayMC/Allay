package org.allaymc.server.blockentity.component;

import org.allaymc.api.blockentity.component.BlockEntityChestBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.api.world.Sound;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * @author daoge_cmd
 */
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityChestBaseComponent {
    protected static final String TAG_PAIR_X = "pairx";
    protected static final String TAG_PAIR_Z = "pairz";

    protected int pairX;
    protected int pairZ;

    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    @ComponentObject
    private BlockEntityChest thisBlockEntity;

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
                        Sound.RANDOM_CHESTOPEN
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
                        Sound.RANDOM_CHESTCLOSED
                );
                sendPacketToViewers(pk);
            }
        });
    }

    @Override
    public NbtMap saveNBT() {
        var savedNbt = super.saveNBT();
        if (isPaired()) {
            savedNbt = savedNbt.toBuilder()
                    .putInt(TAG_PAIR_X, pairX)
                    .putInt(TAG_PAIR_Z, pairZ)
                    .build();
        }

        return savedNbt;
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_PAIR_X, value -> pairX = value);
        nbt.listenForInt(TAG_PAIR_Z, value -> pairZ = value);
    }

    @Override
    public boolean tryPairWith(BlockEntityChest other) {
        if (isPaired() || other.isPaired()) {
            return false;
        }

        pairX = other.getPosition().x();
        pairZ = other.getPosition().z();

        sendBlockEntityDataPacketToViewers();
        return true;
    }

    @Override
    public void unpair() {
        if (!isPaired()) {
            return;
        }

        pairX = Integer.MIN_VALUE;
        pairZ = Integer.MIN_VALUE;

        getPair().unpair();
        sendBlockEntityDataPacketToViewers();
    }

    @Override
    public BlockEntityChest getPair() {
        return (BlockEntityChest) getDimension().getBlockEntity(pairX, position.y(), pairZ);
    }
}
