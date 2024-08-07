package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.component.common.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.init.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBarrel;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.container.impl.BarrelContainer;
import org.allaymc.api.data.VanillaBlockPropertyTypes;
import org.allaymc.server.blockentity.component.common.BlockEntityBaseComponentImpl;

/**
 * Allay Project 2023/12/6
 *
 * @author daoge_cmd
 */
public class BlockEntityBarrelBaseComponentImpl extends BlockEntityBaseComponentImpl<BlockEntityBarrel> {
    @Dependency
    private BlockEntityContainerHolderComponent containerHolderComponent;

    public BlockEntityBarrelBaseComponentImpl(BlockEntityInitInfo<BlockEntityBarrel> info) {
        super(info);
    }

    @Override
    public void onInitFinish(ComponentInitInfo initInfo) {
        super.onInitFinish(initInfo);
        var container = containerHolderComponent.<BarrelContainer>getContainer();
        container.addOnOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                BlockTypes.BARREL.getBlockBehavior().updateBlockProperty(
                        VanillaBlockPropertyTypes.OPEN_BIT,
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
                        VanillaBlockPropertyTypes.OPEN_BIT,
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
