package org.allaymc.server.block.impl.coral;

import java.util.List;
import org.allaymc.api.block.interfaces.coral.BlockDeadTubeCoralBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadTubeCoralBehaviorImpl extends BlockBehaviorImpl implements BlockDeadTubeCoralBehavior {
    public BlockDeadTubeCoralBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
