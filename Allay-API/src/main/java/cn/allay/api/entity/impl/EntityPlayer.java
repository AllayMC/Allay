package cn.allay.api.entity.impl;

import cn.allay.api.client.Client;
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
import cn.allay.api.entity.component.impl.base.EntityPlayerBaseComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerHolderComponentImpl;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponent;
import cn.allay.api.entity.component.impl.container.EntityContainerViewerComponentImpl;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.math.location.Location3d;
import lombok.Getter;
import org.cloudburstmc.math.vector.Vector3f;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemData;
import org.cloudburstmc.protocol.bedrock.packet.AddPlayerPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.MobEquipmentPacket;
import org.jetbrains.annotations.Range;
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
        EntityPlayerBaseComponent,
        EntityAttributeComponent,
        EntityContainerHolderComponent,
        EntityContainerViewerComponent {
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

    interface EntityPlayerInitInfo extends EntityInitInfo<EntityPlayer> {

        Client getClient();

        @Getter
        class Simple extends EntityInitInfo.Simple<EntityPlayer> implements EntityPlayerInitInfo {
            private final Client client;
            public Simple(Client client, Location3d location) {
                super(location);
                this.client = client;
            }
        }
    }

    class EntityPlayerBaseComponentImpl extends EntityBaseComponentImpl<EntityPlayer> implements EntityPlayerBaseComponent {

        @ComponentIdentifier
        public static final Identifier IDENTIFIER = EntityBaseComponentImpl.IDENTIFIER;

        @Dependency
        protected EntityContainerHolderComponent containerHolderComponent;
        protected Client client;
        protected boolean sprinting;
        protected boolean sneaking;
        protected boolean swimming;
        protected boolean gliding;
        protected boolean crawling;

        public EntityPlayerBaseComponentImpl(EntityInitInfo<EntityPlayer> info, Function<EntityPlayer, AABBdc> aabbGetter) {
            super(info, aabbGetter);
            if (info instanceof EntityPlayerInitInfo playerInitInfo) {
                client = playerInitInfo.getClient();
            } else {
                throw new IllegalArgumentException("EntityPlayerInitInfo is required for EntityPlayer");
            }
        }

        @Override
        @Impl
        public void spawnTo(Client client) {
            if (this.client != client)
                super.spawnTo(client);
        }

        @Override
        @Impl
        public void despawnFrom(Client client) {
            if (this.client != client)
                super.despawnFrom(client);
        }

        @Override
        @Impl
        public BedrockPacket createSpawnPacket() {
            var addPlayerPacket = new AddPlayerPacket();
            addPlayerPacket.setRuntimeEntityId(uniqueId);
            addPlayerPacket.setUniqueEntityId(uniqueId);
            addPlayerPacket.setUuid(client.getLoginData().getUuid());
            addPlayerPacket.setUsername(client.getName());
            addPlayerPacket.setPlatformChatId(client.getLoginData().getDeviceInfo().getDeviceId());
            addPlayerPacket.setPosition(Vector3f.from(location.x(), location.y() + getBaseOffset(), location.z()));
            addPlayerPacket.setMotion(Vector3f.from(motion.x(), motion.y(), motion.z()));
            addPlayerPacket.setRotation(Vector3f.from(location.pitch(), location.yaw(), location.headYaw()));
            addPlayerPacket.setGameType(client.getGameType());
            addPlayerPacket.getMetadata().putAll(this.metadata.getEntityDataMap());
            addPlayerPacket.setDeviceId(client.getLoginData().getDeviceInfo().getDeviceId());
            addPlayerPacket.setHand(ItemData.AIR);//TODO: itemInHand
            return addPlayerPacket;
        }

        @Override
        @Impl
        public Client getClient() {
            return client;
        }

        @Override
        @Impl
        public void setSprinting(boolean sprinting) {
            this.sprinting = sprinting;
        }

        @Override
        @Impl
        public boolean isSprinting() {
            return sprinting;
        }

        @Override
        @Impl
        public void setSneaking(boolean sneaking) {
            this.sneaking = sneaking;
        }

        @Override
        @Impl
        public boolean isSneaking() {
            return sneaking;
        }

        @Override
        @Impl
        public void setSwimming(boolean swimming) {
            this.swimming = swimming;
        }

        @Override
        @Impl
        public boolean isSwimming() {
            return swimming;
        }

        @Override
        @Impl
        public void setGliding(boolean gliding) {
            this.gliding = gliding;
        }

        @Override
        @Impl
        public boolean isGliding() {
            return gliding;
        }

        @Override
        @Impl
        public void setCrawling(boolean crawling) {
            this.crawling = crawling;
        }

        @Override
        @Impl
        public boolean isCrawling() {
            return crawling;
        }

        @Override
        @Impl
        public int getHandSlot() {
            return containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).getHandSlot();
        }

        @Override
        @Impl
        public void setHandSlot(@Range(from = 0, to = 8) int handSlot) {
            var inv = containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY);
            inv.setHandSlot(handSlot);
            var itemStack = inv.getItemStack(handSlot);

            var mobEquipmentPacket = new MobEquipmentPacket();
            mobEquipmentPacket.setRuntimeEntityId(uniqueId);
            mobEquipmentPacket.setItem(itemStack.toNetworkItemData());
            mobEquipmentPacket.setInventorySlot(handSlot);
            mobEquipmentPacket.setHotbarSlot(handSlot);

            sendPacketToViewers(mobEquipmentPacket);
        }

        @Override
        @Impl
        public NbtMap save() {
            return super.save().toBuilder()
                    .putList(
                            "Offhand",
                            NbtType.COMPOUND,
                            containerHolderComponent.getContainer(FullContainerType.OFFHAND).save())
                    .putList(
                            "Inventory",
                            NbtType.COMPOUND,
                            containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).save())
                    .putList(
                            "Armor",
                            NbtType.COMPOUND,
                            containerHolderComponent.getContainer(FullContainerType.ARMOR).save())
                    .build();
        }

        @Override
        @Impl
        public void load(NbtMap nbt) {
            super.load(nbt);
            if (nbt.containsKey("Offhand")) {
                containerHolderComponent.getContainer(FullContainerType.OFFHAND).load(nbt.getList("Offhand", NbtType.COMPOUND));
            }
            if (nbt.containsKey("Inventory")) {
                containerHolderComponent.getContainer(FullContainerType.PLAYER_INVENTORY).load(nbt.getList("Inventory", NbtType.COMPOUND));
            }
            if (nbt.containsKey("Armor")) {
                containerHolderComponent.getContainer(FullContainerType.ARMOR).load(nbt.getList("Armor", NbtType.COMPOUND));
            }
        }
    }
}

