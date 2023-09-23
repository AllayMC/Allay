package cn.allay.api.entity.interfaces;

import cn.allay.api.component.annotation.ComponentIdentifier;
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
        protected int pickupDelay = 10;
        protected int age = -1;

        public EntityItemBaseComponentImpl(EntityInitInfo<EntityItem> info, Function<EntityItem, AABBfc> aabbGetter, @Nullable ItemStack itemStack) {
            super(info, aabbGetter);
            this.itemStack = itemStack;
        }

        @Override
        public void tick() {
            super.tick();
            if (age != -1) {
                age++;
                if (age >= MAX_AGE) {
                    location.world.removeEntity(manager.getComponentedObject());
                }
            }
            if (pickupDelay > 0) pickupDelay--;
        }

        @Override
        public NbtMap saveNBT() {
            var nbt = super.saveNBT();
            if (itemStack != null)
                nbt = nbt.toBuilder().putCompound("Item", itemStack.saveNBT()).build();
            return nbt;
        }

        @Override
        public void loadNBT(NbtMap nbt) {
            super.loadNBT(nbt);
            if (nbt.containsKey("Item"))
                itemStack = fromNBT(nbt.getCompound("Item"));
        }

        @Override
        public ItemStack getItemStack() {
            return itemStack;
        }

        @Override
        public void setItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
        }

        @Override
        public int getPickupDelay() {
            return pickupDelay;
        }

        @Override
        public void setPickupDelay(int delay) {
            this.pickupDelay = delay;
        }

        @Override
        public int getAge() {
            return age;
        }

        @Override
        public void setAge(int age) {
            this.age = age;
        }

        @Override
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
