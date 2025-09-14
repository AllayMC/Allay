package org.allaymc.server.entity.impl;

import lombok.Getter;
import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityContainerViewerComponent;
import org.allaymc.api.entity.component.EntityDamageComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.component.player.*;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.server.component.ComponentProvider;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import java.util.List;

public class EntityPlayerImpl extends EntityImpl implements EntityPlayer {

    @Getter
    @Delegate
    protected EntityPlayerNetworkComponent playerNetworkComponent;
    @Delegate
    protected EntityPlayerAttributeComponent playerAttributeComponent;
    @Getter
    @Delegate
    protected EntityContainerHolderComponent containerHolderComponent;
    @Getter
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

    /**
     * A tiny wrapper method facilitates the sending of packet.
     */
    public void sendPacket(BedrockPacket packet) {
        ((EntityPlayerNetworkComponentImpl) (this.playerNetworkComponent)).sendPacket(packet);
    }

    /**
     * A tiny wrapper method facilitates the sending of packet.
     */
    public void sendPacketImmediately(BedrockPacket packet) {
        ((EntityPlayerNetworkComponentImpl) (this.playerNetworkComponent)).sendPacketImmediately(packet);
    }
}