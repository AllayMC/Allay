package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntitySmoker;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockSmokerBehaviorImpl extends BlockBehaviorImpl implements BlockSmokerBehavior {

    @Delegate
    protected BlockEntityHolderComponent<BlockEntitySmoker> blockEntityHolderComponent;

    public BlockSmokerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
