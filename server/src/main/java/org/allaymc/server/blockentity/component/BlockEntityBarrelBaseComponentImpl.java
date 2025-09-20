package org.allaymc.server.blockentity.component;

import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.container.impl.BarrelContainerImpl;

/**
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
        BarrelContainerImpl container = containerHolderComponent.getContainer();
        container.addOpenListener(viewer -> {
            if (container.getViewers().size() == 1) {
                position.dimension().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        true,
                        position.x(),
                        position.y(),
                        position.z()
                );
            }
        });
        container.addCloseListener(viewer -> {
            if (container.getViewers().isEmpty()) {
                position.dimension().updateBlockProperty(
                        BlockPropertyTypes.OPEN_BIT,
                        false,
                        position.x(),
                        position.y(),
                        position.z()
                );
            }
        });
    }
}
