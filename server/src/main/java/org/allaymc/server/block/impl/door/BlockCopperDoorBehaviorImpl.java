package org.allaymc.server.block.impl.door;

import java.util.List;
import org.allaymc.api.block.interfaces.door.BlockCopperDoorBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCopperDoorBehaviorImpl extends BlockBehaviorImpl implements BlockCopperDoorBehavior {
    public BlockCopperDoorBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
