package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockSmokerBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntitySmoker;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSmokerBehaviorImpl extends BlockBehaviorImpl implements BlockSmokerBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntitySmoker> blockEntityHolderComponent;

    public BlockSmokerBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
