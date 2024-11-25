package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockChestBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityChest;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockChestBehaviorImpl extends BlockBehaviorImpl implements BlockChestBehavior {
    @Delegate
    protected BlockEntityHolderComponent<BlockEntityChest> blockEntityHolderComponent;

    public BlockChestBehaviorImpl(List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
