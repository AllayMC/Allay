package org.allaymc.server.block.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadBrainCoralWallFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadBrainCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockDeadBrainCoralWallFanBehavior {
    public BlockDeadBrainCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
