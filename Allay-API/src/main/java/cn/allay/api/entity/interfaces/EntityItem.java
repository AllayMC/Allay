package cn.allay.api.entity.interfaces;

import cn.allay.api.component.annotation.ComponentIdentifier;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.data.VanillaEntityId;
import cn.allay.api.entity.Entity;
import cn.allay.api.entity.component.base.EntityBaseComponentImpl;
import cn.allay.api.entity.component.base.EntityItemBaseComponent;
import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.type.EntityType;
import cn.allay.api.entity.type.EntityTypeBuilder;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.item.ItemStack;
import cn.allay.api.utils.MathUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import javax.annotation.Nullable;
import java.util.function.Function;

import static cn.allay.api.item.ItemHelper.fromNBT;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface EntityItem extends Entity, EntityItemBaseComponent {
  EntityType<EntityItem> ITEM_TYPE = EntityTypeBuilder
          .builder(EntityItem.class)
          .vanillaEntity(VanillaEntityId.ITEM)
          .addComponent(
                  info -> new EntityItemBaseComponentImpl(
                          info,
                          e -> new AABBf(-0.125f, 0.0f, -0.125f, 0.125f, 0.25f, 0.125f),
                          null
                  ),
                  EntityItemBaseComponentImpl.class
          )
          .build();

    class EntityItemBaseComponentImpl extends EntityBaseComponentImpl<EntityItem> implements EntityItemBaseComponent {

        @ComponentIdentifier
        public static final Identifier IDENTIFIER = EntityBaseComponentImpl.IDENTIFIER;
        public static final int MAX_AGE = 6000;
        protected ItemStack itemStack;
        protected int pickupDelay;
        protected int age = -1;

        public EntityItemBaseComponentImpl(EntityInitInfo<EntityItem> info, Function<EntityItem, AABBfc> aabbGetter, @Nullable ItemStack itemStack) {
            super(info, aabbGetter);
            this.itemStack = itemStack;
        }

        @Override
        @Impl
        public void tick() {
            super.tick();
            if (age != -1) {
                age++;
                if (age >= 6000) {
                    //TODO: disappear
                }
            }
            if (pickupDelay > 0) pickupDelay--;
        }

        @Override
        @Impl
        public NbtMap saveNBT() {
            var nbt = super.saveNBT();
            if (itemStack != null)
                nbt = nbt.toBuilder().putCompound("Item", itemStack.saveNBT()).build();
            return nbt;
        }

        @Override
        @Impl
        public void loadNBT(NbtMap nbt) {
            super.loadNBT(nbt);
            if (nbt.containsKey("Item"))
                itemStack = fromNBT(nbt.getCompound("Item"));
        }

        @Override
        @Impl
        public ItemStack getItemStack() {
            return itemStack;
        }

        @Override
        @Impl
        public void setItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        @Impl
        public int getPickupDelay() {
            return pickupDelay;
        }

        @Override
        @Impl
        public void setPickupDelay(int delay) {
            this.pickupDelay = delay;
        }

        @Override
        @Impl
        public int getAge() {
            return age;
        }

        @Override
        @Impl
        public void setAge(int age) {
            this.age = age;
        }

        @Override
        @Impl
        public BedrockPacket createSpawnPacket() {
            AddItemEntityPacket addItemEntityPacket = new AddItemEntityPacket();
            addItemEntityPacket.setRuntimeEntityId(uniqueId);
            addItemEntityPacket.setUniqueEntityId(uniqueId);
            addItemEntityPacket.setItemInHand(itemStack.toNetworkItemData());
            addItemEntityPacket.setPosition(MathUtils.JOMLVecTocbVec(location));
            addItemEntityPacket.setMotion(MathUtils.JOMLVecTocbVec(motion));
            addItemEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
            return addItemEntityPacket;
        }
    }
}
