package org.allaymc.api.blockentity.interfaces.barrel;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.base.BlockEntityBaseComponentImpl;
import org.allaymc.api.blockentity.component.container.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.container.BlockEntityContainerHolderComponentImpl;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.blockentity.type.BlockEntityTypeBuilder;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.impl.BarrelContainer;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3i;

import java.util.Objects;

/**
 * Allay Project 2023/9/15
 *
 * @author daoge_cmd
 */
public interface BlockEntityBarrel extends BlockEntity, BlockEntityContainerHolderComponent {
    String BLOCK_ENTITY_ID = "Barrel";
    BlockEntityType<BlockEntityBarrel> BARREL_TYPE = BlockEntityTypeBuilder
            .builder(BlockEntityBarrel.class)
            .blockEntityId(BLOCK_ENTITY_ID)
            .addComponent(info -> new BlockEntityBaseComponentImpl(info) {
                @Dependency
                private BlockEntityContainerHolderComponent containerHolderComponent;

                @Override
                public void loadNBT(NbtMap nbt) {
                    super.loadNBT(nbt);
                    if (nbt.containsKey("Items"))
                        Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.BARREL)).loadNBT(nbt.getList("Items", NbtType.COMPOUND));
                }

                @Override
                public NbtMap saveNBT() {
                    return super.saveNBT().toBuilder().putList(
                            "Items",
                            NbtType.COMPOUND,
                            Objects.requireNonNull(containerHolderComponent.getContainer(FullContainerType.BARREL)).saveNBT()
                    ).build();
                }
            }, BlockEntityBaseComponentImpl.class)
            .addComponent(
                    initInfo -> new BlockEntityContainerHolderComponentImpl(
                            new BarrelContainer(() -> {
                                var nbt = initInfo.nbt();
                                var vec = new Vector3i(0, 0, 0);
                                vec.x = nbt.getInt("x");
                                vec.y = nbt.getInt("y");
                                vec.z = nbt.getInt("z");
                                return vec;
                            })
                    ),
                    BlockEntityContainerHolderComponentImpl.class
            )
            .build();
}
