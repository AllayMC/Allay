package org.allaymc.server.block.impl.stainedglass;

import java.util.List;
import org.allaymc.api.block.interfaces.stainedglass.BlockBlueStainedGlassBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockBlueStainedGlassBehaviorImpl extends BlockBehaviorImpl implements BlockBlueStainedGlassBehavior {
    public BlockBlueStainedGlassBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
