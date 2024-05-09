package org.allaymc.server.blockentity.component.barrel;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BlockEntityBarrelBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityBarrel> {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityBarrelBaseComponentImpl(BlockEntityInitInfo<BlockEntityBarrel> info) {
        super(info);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForList("Items", NbtType.COMPOUND, items ->
                containerHolderComponent.getContainer(FullContainerType.BARREL).loadNBT(nbt.getList("Items", NbtType.COMPOUND))
        );
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT().toBuilder().putList(
                "Items",
                NbtType.COMPOUND,
                containerHolderComponent.getContainer(FullContainerType.BARREL).saveNBT()
        ).build();
    }
}
