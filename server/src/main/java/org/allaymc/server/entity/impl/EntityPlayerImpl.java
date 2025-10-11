package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.*;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.entity.component.player.EntityPlayerClientComponentImpl;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

import java.util.List;

public class EntityPlayerImpl extends EntityImpl implements EntityPlayer {

    @Getter
    @Delegate
    protected EntityContainerHolderComponent containerHolderComponent;
    @Getter
    @Delegate
    protected EntityContainerViewerComponent containerViewerComponent;
    @Delegate
    protected EntityLivingComponent livingComponent;
    @Getter
    @Delegate
    protected EntityPhysicsComponent physicsComponent;
    @Getter
    @Delegate
    protected EntityPlayerClientComponent playerClientComponent;
    @Delegate
    protected EntityPlayerScoreboardViewerComponent playerScoreboardViewerComponent;
    @Delegate
    protected EntityPlayerChunkLoaderComponent playerChunkLoaderComponent;
    @Delegate
    protected EntityPlayerBossBarViewerComponent playerBossBarViewerComponent;
    @Delegate
    protected EntityPlayerFormViewerComponent playerFormViewerComponent;

    public EntityPlayerImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityPlayerBaseComponent getBaseComponent() {
        return (EntityPlayerBaseComponent) super.getBaseComponent();
    }

    public BedrockServerSession getClientSession() {
        return ((EntityPlayerClientComponentImpl) (this.playerClientComponent)).getClientSession();
    }
}