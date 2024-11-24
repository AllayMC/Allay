package org.allaymc.server.block.impl.coral;

import java.util.List;
import org.allaymc.api.block.interfaces.coral.BlockDeadBrainCoralBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadBrainCoralBehaviorImpl extends BlockBehaviorImpl implements BlockDeadBrainCoralBehavior {
    public BlockDeadBrainCoralBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
