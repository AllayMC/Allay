package org.allaymc.server.block.impl.stainedglass;

import java.util.List;
import org.allaymc.api.block.interfaces.stainedglass.BlockHardBlueStainedGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockHardBlueStainedGlassBehaviorImpl extends BlockBehaviorImpl implements BlockHardBlueStainedGlassBehavior {
    public BlockHardBlueStainedGlassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
