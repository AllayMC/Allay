package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockBrewingStandBehaviorImpl extends BlockBehaviorImpl implements BlockBrewingStandBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityBrewingStand> blockEntityHolderComponent;

    public BlockBrewingStandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
