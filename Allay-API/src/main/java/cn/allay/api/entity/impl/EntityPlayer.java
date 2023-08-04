package cn.allay.api.entity.impl;

import cn.allay.api.component.annotation.AutoRegister;
import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.container.impl.*;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponent;
import cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl;
import cn.allay.api.entity.component.impl.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponentImpl;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponentImpl;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponent;
import cn.allay.api.entity.component.impl.playercontroller.EntityPlayerControllerComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.identifier.Identifier;
import org.cloudburstmc.math.vector.Vector2f;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.protocol.bedrock.data.GameType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.function.Function;

import static cn.allay.api.entity.component.impl.attribute.EntityAttributeComponentImpl.basicAttributes;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityPlayer extends
        Entity,
        EntityAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent,
        EntityPlayerControllerComponent {
    @AutoRegister
    ComponentProvider<EntityBaseComponentImpl<EntityPlayer>> BASE_COMPONENT =
            ComponentProvider.of(
                    info -> new EntityPlayerBaseComponentImpl((EntityInitInfo<EntityPlayer>) info, e -> new AABBd(-0.3, 0, -0.3, 0.3, 1.8, 0.3)),
                    EntityPlayerBaseComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityAttributeComponentImpl> ATTRIBUTE_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityAttributeComponentImpl(basicAttributes()),
                    EntityAttributeComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityContainerHolderComponentImpl> CONTAINER_HOLDER_COMPONENT =
            ComponentProvider.of(
                    () -> new EntityContainerHolderComponentImpl(
                            new PlayerInventoryContainer(),
                            new PlayerCursorContainer(),
                            new PlayerCreatedOutputContainer(),
                            new PlayerArmorContainer(),
                            new PlayerOffhandContainer()
                    ),
                    EntityContainerHolderComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityContainerViewerComponentImpl> CONTAINER_VIEWER_COMPONENT =
            ComponentProvider.of(
                    EntityContainerViewerComponentImpl::new,
                    EntityContainerViewerComponentImpl.class
            );

    @AutoRegister
    ComponentProvider<EntityPlayerControllerComponentImpl> PLAYER_CONTROLLER_COMPONENT =
            ComponentProvider.of(
                    EntityPlayerControllerComponentImpl::new,
                    EntityPlayerControllerComponentImpl.class
            );

    class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> {

        @ComponentIdentifier
        public static final Identifier IDENTIFIER = EntityBaseComponentImpl.IDENTIFIER;

        @Dependency
        protected EntityPlayerControllerComponent playerControllerComponent;
        @Dependency
        protected EntityContainerHolderComponent containerHolderComponent;

        public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info, Function<EntityPlayer, AABBdc> aabbGetter) {
            super(info, aabbGetter);
        }

        @Override
        @Impl
        public BedrockPacket createSpawnPacket() {
            var addPlayerPacket = new AddPlayerPacket();
            addPlayerPacket.setRuntimeEntityId(uniqueId);
            addPlayerPacket.setUniqueEntityId(uniqueId);
            var client = playerControllerComponent.getClient();
            addPlayerPacket.setUuid(client.getLoginData().getUuid());
            addPlayerPacket.setUsername(client.getName());
            addPlayerPacket.setPlatformChatId(client.getLoginData().getDeviceInfo().getDeviceId());
            addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y(), location.z()));
            addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
            addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
            addPlayerPacket.setGameType(client.getGameType());
            addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
            addPlayerPacket.setDeviceId(client.getLoginData().getDeviceInfo().getDeviceId());
            addPlayerPacket.setHand(ItemData.AIR);//TODO: itemInHand
            return addPlayerPacket;
        }
    }
}
