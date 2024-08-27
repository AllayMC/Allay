package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class BlockEntityBarrelBaseComponentImpl extends BlockEntityBaseComponentImpl {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityBarrelBaseComponentImpl(BlockEntityInitInfo info) {
        super(info);
    }

    @OnInitFinish
    @Override
    public void onInitFinish(BlockEntityInitInfo initInfo) {
        super.onInitFinish(initInfo);
        BarrelContainer container = containerHolderComponent.getContainer();
        container.addOnOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                BlockTypes.BARREL.getBlockBehavior().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        true,
                        position.x(),
                        position.y(),
                        position.z(),
                        position.dimension()
                );
            }
        });
        container.addOnCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                BlockTypes.BARREL.getBlockBehavior().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        false,
                        position.x(),
                        position.y(),
                        position.z(),
                        position.dimension()
                );
            }
        });
    }
}
