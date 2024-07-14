package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.item.EntityItemBaseComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.MathUtils;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import static org.allaymc.api.item.ItemHelper.fromNBT;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityItemBaseComponentImpl extends EntityBaseComponentImpl<EntityItem> implements EntityItemBaseComponent {

    public static final int MAX_AGE = 6000;

    protected ItemStack itemStack;
    protected int pickupDelay = 10;
    protected int age;

    public EntityItemBaseComponentImpl(EntityInitInfo<EntityItem> info) {
        super(info);
    }

    public void setItemStack(ItemStack itemStack) {
        // Stack network id shouldn't be kept
        if (itemStack != null) {
            itemStack.clearStackNetworkId();
        }
        this.itemStack = itemStack;
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(-0.125f, 0.0f, -0.125f, 0.125f, 0.25f, 0.125f);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();
        metadata.set(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (age != -1) {
            age++;
            if (age >= MAX_AGE) despawn();
        }

        if (pickupDelay > 0) pickupDelay--;
    }

    @Override
    public void onCollideWith(Entity other) {
        // check can merge
        if (itemStack != null && other.getEntityType() == EntityTypes.ITEM_TYPE) {
            var otherEntityItem = (EntityItem) other;
            var otherItemStack = otherEntityItem.getItemStack();
            if (otherItemStack != null && otherItemStack.canMerge(itemStack)) {
                itemStack.setCount(itemStack.getCount() + otherItemStack.getCount());
                otherEntityItem.setItemStack(null);
                otherEntityItem.despawn();
            }
        }
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
        nbt.listenForCompound("Item", itemNbt -> this.itemStack = fromNBT(itemNbt));
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        AddItemEntityPacket addItemEntityPacket = new AddItemEntityPacket();
        addItemEntityPacket.setRuntimeEntityId(runtimeId);
        addItemEntityPacket.setUniqueEntityId(runtimeId);
        addItemEntityPacket.setItemInHand(itemStack.toNetworkItemData());
        addItemEntityPacket.setPosition(MathUtils.JOMLVecToCBVec(location));
        addItemEntityPacket.setMotion(MathUtils.JOMLVecToCBVec(motion));
        addItemEntityPacket.getMetadata().putAll(metadata.getEntityDataMap());
        return addItemEntityPacket;
    }
}
