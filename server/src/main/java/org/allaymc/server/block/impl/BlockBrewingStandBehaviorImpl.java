package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockBrewingStandBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockBrewingStandBehaviorImpl extends BlockBehaviorImpl implements BlockBrewingStandBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityBrewingStand> blockEntityHolderComponent;

    public BlockBrewingStandBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
