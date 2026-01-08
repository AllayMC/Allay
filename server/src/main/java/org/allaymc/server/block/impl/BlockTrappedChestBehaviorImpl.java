package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockTrappedChestBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTrappedChestBehaviorImpl extends BlockBehaviorImpl implements BlockTrappedChestBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    public BlockTrappedChestBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
