package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockTargetBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityTarget;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTargetBehaviorImpl extends BlockBehaviorImpl implements BlockTargetBehavior {

    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityTarget> blockEntityHolderComponent;

    public BlockTargetBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
