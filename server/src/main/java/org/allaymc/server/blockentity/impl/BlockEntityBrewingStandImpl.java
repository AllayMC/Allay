package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityBrewingStandBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author IWareQ
 */
public class BlockEntityBrewingStandImpl extends BlockEntityImpl implements BlockEntityBrewingStand {
    public BlockEntityBrewingStandImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityBrewingStandBaseComponent getBaseComponent() {
        return (BlockEntityBrewingStandBaseComponent) super.getBaseComponent();
    }
}
