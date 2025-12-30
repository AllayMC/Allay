package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author IWareQ
 */
public class BlockEntityBrewingStandImpl extends BlockEntityImpl implements BlockEntityBrewingStand {

    @Delegate
    private BlockEntityContainerHolderComponent containerHolderComponent;
    @Delegate
    private BlockEntityBrewingStandBaseComponent brewingStandBaseComponent;

    public BlockEntityBrewingStandImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
