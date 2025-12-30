package org.allaymc.server.block.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.block.component.BlockBlockEntityHolderComponent;
import org.allaymc.api.block.interfaces.BlockHangingSignBehavior;
import org.allaymc.api.blockentity.interfaces.BlockEntityHangingSign;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockHangingSignBehaviorImpl extends BlockBehaviorImpl implements BlockHangingSignBehavior {
    @Delegate
    private BlockBlockEntityHolderComponent<BlockEntityHangingSign> blockEntityHolderComponent;

    public BlockHangingSignBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
