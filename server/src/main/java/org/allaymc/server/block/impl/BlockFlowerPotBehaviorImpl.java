package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockFlowerPotBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityFlowerPot;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockFlowerPotBehaviorImpl extends BlockBehaviorImpl implements BlockFlowerPotBehavior {

    @Delegate
    protected BlockEntityHolderComponent<BlockEntityFlowerPot> blockEntityHolderComponent;

    public BlockFlowerPotBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
