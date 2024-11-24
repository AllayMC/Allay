package org.allaymc.server.block.impl.torch;

import org.allaymc.api.block.interfaces.torch.BlockColoredTorchBlueBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchGreenBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchPurpleBehavior;
import org.allaymc.api.block.interfaces.torch.BlockColoredTorchRedBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockColoredTorchBehaviorImpl extends BlockBehaviorImpl implements
        BlockColoredTorchRedBehavior, BlockColoredTorchBlueBehavior, BlockColoredTorchGreenBehavior, BlockColoredTorchPurpleBehavior {
    public BlockColoredTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
        ;
    }
}
