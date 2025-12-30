package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockSignBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntitySign;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockSignBehaviorImpl extends BlockBehaviorImpl implements BlockSignBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntitySign> blockEntityHolderComponent;

    public BlockSignBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
