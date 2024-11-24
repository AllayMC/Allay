package org.allaymc.server.block.impl.coralwallfan;

import java.util.List;
import org.allaymc.api.block.interfaces.coralwallfan.BlockDeadHornCoralWallFanBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockDeadHornCoralWallFanBehaviorImpl extends BlockBehaviorImpl implements BlockDeadHornCoralWallFanBehavior {
    public BlockDeadHornCoralWallFanBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
