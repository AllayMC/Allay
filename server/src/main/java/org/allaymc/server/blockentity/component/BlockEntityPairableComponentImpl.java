package org.allaymc.server.blockentity.component;

import lombok.Getter;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityPairableComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.block.component.event.CBlockOnReplaceEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntityLoadNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveCleanNBTEvent;
import org.allaymc.server.blockentity.component.event.CBlockEntitySaveNBTEvent;
import org.allaymc.server.component.annotation.Dependency;

/**
 * {@code BlockEntityPairableComponentImpl} stores horizontal partner
 * coordinates and a lead flag for a block entity. {@code getPair} resolves the
 * record using the owner's current height and dimension, so callers must choose
 * a compatible partner in that same level and dimension. Coordinate presence
 * defines {@code isPaired}; it does not prove that a live partner remains
 * there.
 *
 * <p>
 * {@code tryPairWith} records and broadcasts this side before choosing a lead
 * from the resolved partner's state. Chest placement calls it on the neighbor
 * before recording the reciprocal link, allowing that neighbor to become the
 * lead. Pairing and unpairing do not automatically update both sides, and
 * failures after coordinate assignment leave earlier state in place. Clean NBT
 * saving strips the relationship so relocation does not retain former
 * neighbors.
 *
 * @author IWareQ
 */
public class BlockEntityPairableComponentImpl implements BlockEntityPairableComponent {

    /**
     * {@code IDENTIFIER} selects the pairable component provider during
     * component assembly, keeping its metadata and event hooks available to
     * paired block entities.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:block_entity_pairable_component");

    /**
     * {@code TAG_PAIR_LEAD} names the retained lead flag. The pairing tag
     * constants identify the lead and horizontal partner coordinates in
     * ordinary or clean saves.
     */
    protected static final String TAG_PAIR_LEAD = "allay:pairlead";
    /**
     * Persisted partner block x.
     */
    protected static final String TAG_PAIR_X = "pairx";
    /**
     * Persisted partner block z.
     */
    protected static final String TAG_PAIR_Z = "pairz";

    /**
     * Injected owner identity component.
     */
    @Dependency
    protected BlockEntityBaseComponentImpl baseComponent;

    /**
     * {@code lead} identifies the pairing side used first by combined chest
     * views. Pair creation sets it when the resolved partner has no recorded
     * pair coordinates.
     */
    @Getter
    protected boolean lead;
    // pairX and pairZ jointly define a recorded relationship only when both are
    // present. getPair resolves them at the owner's current height and
    // dimension.
    /**
     * Optional partner x coordinate.
     */
    protected Integer pairX;
    /**
     * Optional partner z coordinate.
     */
    protected Integer pairZ;

    /**
     * Records and broadcasts a partner before selecting this side's lead flag.
     * An existing coordinate pair returns {@code false} without inspecting
     * {@code other}. Otherwise the target must resolve to a live pairable
     * entity at this owner's height and dimension; this method records only its
     * horizontal coordinates. The lead flag becomes {@code true} when that
     * resolved partner is not paired, and otherwise remains unchanged. Failures
     * during delivery, lookup, or casting leave the recorded coordinates in
     * place without rollback.
     */
    @Override
    public boolean tryPairWith(BlockEntity other) {
        if (isPaired()) {
            return false;
        }

        pairX = other.getPosition().x();
        pairZ = other.getPosition().z();

        baseComponent.sendBlockEntityToViewers();

        // Read the partner after recording this side so sequential reciprocal
        // pairing elects the first unpaired side as lead without updating both
        // entities here.
        var pair = (BlockEntityPairableComponent) getPair();
        if (!pair.isPaired()) {
            lead = true;
        }

        return true;
    }

    /**
     * Clears this side's lead flag and partner coordinates, then updates
     * viewers. The partner is not changed by this method, and a delivery
     * failure leaves this side's cleared state in place.
     */
    @Override
    public void unpair() {
        lead = false;

        pairX = null;
        pairZ = null;

        baseComponent.sendBlockEntityToViewers();
    }

    /**
     * Loads supplied lead and horizontal partner entries from the component
     * event. Absent entries retain their current values; the fields are
     * assigned independently without resolving a partner or requiring both
     * coordinates together.
     */
    @EventHandler
    protected void onLoadNBT(CBlockEntityLoadNBTEvent event) {
        var nbt = event.getNbt();
        nbt.listenForBoolean(TAG_PAIR_LEAD, value -> lead = value);
        nbt.listenForInt(TAG_PAIR_X, value -> pairX = value);
        nbt.listenForInt(TAG_PAIR_Z, value -> pairZ = value);
    }

    /**
     * Adds lead and coordinate tags only when both partner coordinates exist.
     * The method uses the recorded metadata without looking up the live
     * partner, so a stale but complete pair record is still serialized.
     */
    @EventHandler
    protected void onSaveNBT(CBlockEntitySaveNBTEvent event) {
        var builder = event.getNbt();
        if (isPaired()) {
            builder.putBoolean(TAG_PAIR_LEAD, lead)
                    .putInt(TAG_PAIR_X, pairX)
                    .putInt(TAG_PAIR_Z, pairZ);
        }
    }

    /**
     * Removes lead and partner coordinates from a relocation-save builder. The
     * live pairing fields remain unchanged; only the saved payload loses its
     * location-dependent relationship to the original neighbor.
     */
    @EventHandler
    protected void onSaveCleanNBT(CBlockEntitySaveCleanNBTEvent event) {
        var builder = event.getNbt();
        // Pair coordinates refer to the original neighborhood, so strip the
        // entire relationship from relocated NBT without modifying the live
        // entity's pairing state.
        builder.remove(TAG_PAIR_LEAD);
        builder.remove(TAG_PAIR_X);
        builder.remove(TAG_PAIR_Z);
    }

    /**
     * Unpairs a recorded live partner before clearing the replaced entity's
     * side. Both lookups must yield compatible pairable entities. A missing or
     * incompatible partner can fail before this entity is cleared, leaving
     * partial cleanup.
     */
    @EventHandler
    protected void onReplace(CBlockOnReplaceEvent event) {
        var blockEntity = event.getCurrentBlock().getBlockEntity();
        if (blockEntity instanceof BlockEntityPairableComponent pairableComponent) {
            if (pairableComponent.isPaired()) {
                // Clear the other side before discarding this side's lookup
                // coordinates; a failure while clearing that partner leaves
                // this side's relationship recorded.
                ((BlockEntityPairableComponent) pairableComponent.getPair()).unpair();
                pairableComponent.unpair();
            }
        }
    }

    /**
     * Resolves stored horizontal coordinates at the owner's current height and
     * dimension. Both coordinates must be present before unboxing; a complete
     * record can still resolve to {@code null} when no block entity occupies
     * that location.
     */
    @Override
    public BlockEntity getPair() {
        return baseComponent.getDimension().getBlockEntity(pairX, baseComponent.getPosition().y(), pairZ);
    }

    /**
     * Returns whether both horizontal partner-coordinate fields are present.
     * The result does not inspect {@code lead} or look up a partner, so callers
     * requiring a live relationship must resolve it separately.
     */
    @Override
    public boolean isPaired() {
        return pairX != null && pairZ != null;
    }
}
