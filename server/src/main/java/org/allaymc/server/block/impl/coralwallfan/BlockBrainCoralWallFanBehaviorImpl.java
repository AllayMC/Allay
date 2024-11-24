package org.allaymc.server.block.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralwallfan.BlockBrainCoralWallFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBrainCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockBrainCoralWallFanBehavior {
    public BlockBrainCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
