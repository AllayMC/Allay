package cn.allay.api.blockentity.interfaces;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponent;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponentImpl;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.container.impl.BarrelContainer;
import org.joml.Vector3i;

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
            .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
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
