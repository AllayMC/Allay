package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.*;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;

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
    protected EntityContainerViewerComponent containerViewerComponent;
    @Delegate
    protected EntityDamageComponent damageComponent;
    @Getter
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Delegate
    protected EntityPlayerScoreboardViewerComponent playerScoreboardViewerComponent;
    @Delegate
    protected EntityPlayerDebugShapeViewerComponent playerDebugShapeViewerComponent;
    @Delegate
    protected EntityPlayerEntityViewerComponent playerEntityViewerComponent;
    @Delegate
    protected EntityPlayerChunkLoaderComponent playerChunkLoaderComponent;

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