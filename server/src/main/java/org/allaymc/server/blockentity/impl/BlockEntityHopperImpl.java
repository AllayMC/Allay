package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityContainerHolderComponent;
import org.allaymc.api.blockentity.component.BlockEntityHopperBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityHopper;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author ClexaGod
 */
public class BlockEntityHopperImpl extends BlockEntityImpl implements BlockEntityHopper {

    @Delegate
    protected BlockEntityContainerHolderComponent containerHolderComponent;
    @Delegate
    protected BlockEntityHopperBaseComponent hopperBaseComponent;

    public BlockEntityHopperImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
