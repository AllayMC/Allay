package cn.allay.api.blockentity.interfaces.barrel;

import cn.allay.api.blockentity.BlockEntity;
import cn.allay.api.blockentity.component.base.BlockEntityBaseComponentImpl;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponent;
import cn.allay.api.blockentity.component.container.BlockEntityContainerHolderComponentImpl;
import cn.allay.api.blockentity.init.BlockEntityInitInfo;
import cn.allay.api.blockentity.type.BlockEntityType;
import cn.allay.api.blockentity.type.BlockEntityTypeBuilder;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.impl.BarrelContainer;
import cn.allay.api.identifier.Identifier;
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
            .addComponent(BlockEntityBarrelBaseComponentImpl::new, BlockEntityBarrelBaseComponentImpl.class)
            .addComponent(
                    initInfo -> new BlockEntityContainerHolderComponentImpl(new BarrelContainer(() -> {
                        var nbt = initInfo.nbt();
                        var vec = new Vector3i(0, 0, 0);
                        vec.x = nbt.getInt("x");
                        vec.y = nbt.getInt("y");
                        vec.z = nbt.getInt("z");
                        return vec;
                    })),
                    BlockEntityContainerHolderComponentImpl.class
            )
            .build();

    class BlockEntityBarrelBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityBarrel> {

        @ComponentIdentifier
        public static final Identifier IDENTIFIER = BlockEntityBaseComponentImpl.IDENTIFIER;

        @Dependency
        protected BlockEntityContainerHolderComponent containerHolderComponent;

        public BlockEntityBarrelBaseComponentImpl(BlockEntityInitInfo<BlockEntityBarrel> initInfo) {
            super(initInfo);
        }

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
    }
}
