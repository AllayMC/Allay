package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockFurnaceBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockFurnaceBehaviorImpl extends BlockBehaviorImpl implements BlockFurnaceBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityFurnace> blockEntityHolderComponent;

    public BlockFurnaceBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
