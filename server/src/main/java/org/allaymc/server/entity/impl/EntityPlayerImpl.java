package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.player.EntityPlayerAttributeComponent;
import org.allaymc.api.entity.component.player.EntityPlayerBaseComponent;
import org.allaymc.api.entity.component.player.EntityPlayerContainerHolderComponent;
import org.allaymc.api.entity.component.player.EntityPlayerNetworkComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class EntityPlayerImpl extends EntityImpl implements EntityPlayer {

    @Getter
    @Delegate
    protected EntityPlayerNetworkComponent playerNetworkComponent;
    @Delegate
    protected EntityPlayerAttributeComponent playerAttributeComponent;
    @Delegate
    protected EntityPlayerContainerHolderComponent playerContainerHolderComponent;
    @Delegate
    protected EntityContainerViewerComponent viewerComponent;
    @Delegate
    protected EntityDamageComponent damageComponent;

    public EntityPlayerImpl(EntityInitInfo initInfo,
                            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityPlayerBaseComponent getBaseComponent() {
        return (EntityPlayerBaseComponent) super.getBaseComponent();
    }
}