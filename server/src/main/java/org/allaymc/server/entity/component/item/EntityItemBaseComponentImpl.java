package org.allaymc.server.entity.component.item;

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
import org.allaymc.server.entity.component.EntityPickableBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityItemBaseComponentImpl} represents the mutable stack carried by
 * a dropped item and merges eligible resting item entities. Assigning a stack
 * clears its unique network identifier on the supplied object, because a
 * dropped stack is retained separately from inventory network identity. A
 * {@code null} stack represents an empty entity and is omitted during saving.
 *
 * <p>
 * Merge callbacks consider grounded participants only once per twenty age
 * ticks. A successful merge adds the other stack count to this stack, clears
 * the other entity stack reference, and then requests its removal. Clearing
 * that reference prevents the removed entity from continuing to expose the
 * transferred stack through pickup or persistence handling.
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public class EntityItemBaseComponentImpl extends EntityPickableBaseComponentImpl implements EntityItemBaseComponent {

    /**
     * Injected grounded-state component for merge eligibility.
     */
    @Dependency
    protected EntityPhysicsComponent physicsComponent;
    /**
     * Injected age counter for merge-check scheduling.
     */
    @Dependency
    protected EntityAgeComponent ageComponent;

    /**
     * {@code itemStack} retains the mutable carried stack or {@code null}. The
     * generated getter exposes this same object to pickup, merge, and
     * serialization consumers.
     */
    protected ItemStack itemStack;

    /**
     * Creates the dropped item base with the supplied entity initialization
     * context.
     */
    public EntityItemBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    /**
     * Retains the supplied stack after clearing its unique network identifier.
     * Passing {@code null} empties the entity, and passing a stack mutates
     * that same caller-supplied object before storing it.
     */
    public void setItemStack(ItemStack itemStack) {
        // A world item retains stack contents without the inventory network
        // identity, so clear the supplied stack identifier before storing it.
        if (itemStack != null) {
            itemStack.clearUniqueId();
        }
        this.itemStack = itemStack;
    }

    /**
     * Merges a compatible live item entity into this item during a grounded
     * merge-check tick. This item must be on ground, and another entity
     * exposing physics must also be grounded. The receiving age must be
     * divisible by twenty before stack compatibility is checked.
     *
     * <p>
     * A compatible pair transfers the full other count into this retained
     * stack. The other stack reference is cleared before its entity is
     * removed, preventing a later consumer from finding that transferred stack
     * there. The method relies on the stack compatibility check and does not
     * independently split the combined count.
     */
    @Override
    public void onCollideWithEntity(Entity other) {
        if (!physicsComponent.isOnGround() || (other instanceof EntityPhysicsComponent c && !c.isOnGround()) || ageComponent.getAge() % 20 != 0) {
            // Restrict merging to resting stacks and one age-aligned check per
            // second so overlap callbacks do not merge on every tick.
            return;
        }

        if (itemStack != null && other instanceof EntityItem otherEntityItem && other.isAlive()) {
            var otherItemStack = otherEntityItem.getItemStack();
            if (otherItemStack != null && otherItemStack.canMerge(itemStack)) {
                itemStack.setCount(itemStack.getCount() + otherItemStack.getCount());
                // Clear the transferred stack reference before queuing removal so
                // later pickup or persistence cannot consume the donor contents
                // again.
                otherEntityItem.setItemStack(null);
                otherEntityItem.remove();
            }
        }
    }

    /**
     * Returns inherited entity data and includes the carried stack when
     * present. A {@code null} stack omits the item compound, so an entity
     * emptied during merging saves no transferred contents.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        if (itemStack != null) {
            builder.putCompound("Item", itemStack.saveNBT()).build();
        }

        return builder.build();
    }

    /**
     * Loads inherited entity data and decodes a present item compound into
     * retained stack storage. A missing compound preserves the current stack,
     * and decoding assigns directly without calling the
     * unique-identifier-clearing setter.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound("Item", itemNbt -> this.itemStack = NBTIO.getAPI().fromItemStackNBT(itemNbt));
    }

    /**
     * Returns the dropped item local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.125, 0.0, -0.125, 0.125, 0.25, 0.125);
    }

}
