package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockEnderChestBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityEnderChest;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockEnderChestBehaviorImpl extends BlockBehaviorImpl implements BlockEnderChestBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityEnderChest> blockEntityHolderComponent;

    public BlockEnderChestBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
