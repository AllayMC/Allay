package org.allaymc.server.block.impl.door;

import java.util.List;
import org.allaymc.api.block.interfaces.door.BlockIronDoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockIronDoorBehaviorImpl extends BlockBehaviorImpl implements BlockIronDoorBehavior {
    public BlockIronDoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
