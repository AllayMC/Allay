package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.packet.AddItemEntityPacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

import static org.allaymc.api.item.ItemHelper.fromNBT;

/**
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityItemBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityItemBaseComponent {
    protected ItemStack itemStack;

    public EntityItemBaseComponentImpl(EntityInitInfo info) {
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
    public void onCollideWith(Entity other) {
        // check can merge
        if (itemStack != null && other.getEntityType() == EntityTypes.ITEM && other.isAlive()) {
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
        var builder = super.saveNBT().toBuilder();

        if (itemStack != null) {
            builder.putCompound("Item", itemStack.saveNBT()).build();
        }

        return builder.build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound("Item", itemNbt -> this.itemStack = fromNBT(itemNbt));
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(-0.125f, 0.0f, -0.125f, 0.125f, 0.25f, 0.125f);
    }

    @Override
    public float getNetworkOffset() {
        return 0.125f;
    }

    @Override
    public BedrockPacket createSpawnPacket() {
        var packet = new AddItemEntityPacket();
        packet.setRuntimeEntityId(runtimeId);
        packet.setUniqueEntityId(runtimeId);
        packet.setItemInHand(itemStack.toNetworkItemData());
        packet.setPosition(MathUtils.JOMLVecToCBVec(location));
        packet.setMotion(MathUtils.JOMLVecToCBVec(motion));
        packet.getMetadata().putAll(metadata.getEntityDataMap());
        return packet;
    }
}
