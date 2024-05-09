package org.allaymc.server.blockentity.component.chest;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.ChestContainer;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.math.vector.Vector3i;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.packet.BlockEventPacket;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BlockEntityChestBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityChest> {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityChestBaseComponentImpl(BlockEntityInitInfo<BlockEntityChest> info) {
        super(info);
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        super.onInitFinish(initInfo);
        var container = containerHolderComponent.<ChestContainer>getContainer();
        container.addOnOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                BlockEventPacket pk = new BlockEventPacket();
                pk.setBlockPosition(Vector3i.from(
                        position.x(),
                        position.y(),
                        position.z()
                ));
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
                pk.setBlockPosition(Vector3i.from(
                        position.x(),
                        position.y(),
                        position.z()
                ));
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

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForList("Items", NbtType.COMPOUND, items ->
                containerHolderComponent.getContainer(FullContainerType.CHEST).loadNBT(nbt.getList("Items", NbtType.COMPOUND))
        );
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putList(
                "Items",
                NbtType.COMPOUND,
                containerHolderComponent.getContainer(FullContainerType.CHEST).saveNBT()
        ).build();
    }
}
