package org.allaymc.server.block.impl.torch;

import java.util.List;
import org.allaymc.api.block.interfaces.torch.BlockUnlitRedstoneTorchBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockUnlitRedstoneTorchBehaviorImpl extends BlockBehaviorImpl implements BlockUnlitRedstoneTorchBehavior {
    public BlockUnlitRedstoneTorchBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
