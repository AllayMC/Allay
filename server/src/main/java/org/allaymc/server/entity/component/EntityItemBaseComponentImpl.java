package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.entity.component.EntityItemBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.interfaces.EntityItem;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.utils.NBTIO;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityItemBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityItemBaseComponent {

    @Dependency
    protected EntityPhysicsComponent physicsComponent;
    @Dependency
    protected EntityAgeComponent ageComponent;

    protected ItemStack itemStack;

    public EntityItemBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    public void setItemStack(ItemStack itemStack) {
        // Stack network id shouldn't be kept
        if (itemStack != null) {
            itemStack.clearUniqueId();
        }
        this.itemStack = itemStack;
    }

    @Override
    public void onCollideWithEntity(Entity other) {
        if (!physicsComponent.isOnGround() || (other instanceof EntityPhysicsComponent c && !c.isOnGround()) || ageComponent.getAge() % 20 != 0) {
            // Check for merge every second, and only when both entities are on the ground
            return;
        }

        // check can merge
        if (itemStack != null && other instanceof EntityItem otherEntityItem && other.isAlive()) {
            var otherItemStack = otherEntityItem.getItemStack();
            if (otherItemStack != null && otherItemStack.canMerge(itemStack)) {
                itemStack.setCount(itemStack.getCount() + otherItemStack.getCount());
                otherEntityItem.setItemStack(null);
                otherEntityItem.remove();
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

        nbt.listenForCompound("Item", itemNbt -> this.itemStack = NBTIO.getAPI().fromItemStackNBT(itemNbt));
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }

}
