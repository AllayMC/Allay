package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityLivingComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.*;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

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
    protected EntityPlayerNetworkComponent playerNetworkComponent;
    @Delegate
    protected EntityPlayerAttributeComponent playerAttributeComponent;
    @Delegate
    protected EntityPlayerScoreboardViewerComponent playerScoreboardViewerComponent;
    @Delegate
    protected EntityPlayerChunkLoaderComponent playerChunkLoaderComponent;
    @Delegate
    protected EntityPlayerBossBarViewerComponent playerBossBarViewerComponent;

    public EntityPlayerImpl(EntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public EntityPlayerBaseComponent getBaseComponent() {
        return (EntityPlayerBaseComponent) super.getBaseComponent();
    }

    public void sendPacket(BedrockPacket packet) {
        ((EntityPlayerNetworkComponentImpl) (this.playerNetworkComponent)).sendPacket(packet);
    }

    public void sendPacketImmediately(BedrockPacket packet) {
        ((EntityPlayerNetworkComponentImpl) (this.playerNetworkComponent)).sendPacketImmediately(packet);
    }

    public BedrockServerSession getClientSession() {
        return ((EntityPlayerNetworkComponentImpl) (this.playerNetworkComponent)).getClientSession();
    }
}