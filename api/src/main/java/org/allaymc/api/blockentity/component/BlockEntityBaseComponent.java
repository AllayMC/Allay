package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.type.BlockEntityType;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.pdc.PersistentDataHolder;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.World;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityBaseComponent} exposes a block entity's identity, location,
 * naming, and persistence state. Specialized components contribute additional
 * NBT alongside these shared fields. Position belongs to a dimension, and
 * serialization can retain that position for storage or omit location-dependent
 * data when moving the entity.
 *
 * @author daoge_cmd
 */
public interface BlockEntityBaseComponent extends BlockEntityComponent, PersistentDataHolder {

    /**
     * Returns the registered type responsible for constructing this block
     * entity.
     */
    BlockEntityType<? extends BlockEntity> getBlockEntityType();

    /**
     * Returns this block entity's block coordinates and associated dimension.
     */
    Position3ic getPosition();

    /**
     * Returns the dimension associated with this block entity's current
     * position.
     */
    default Dimension getDimension() {
        return getPosition().dimension();
    }

    /**
     * Returns the world containing this block entity's associated dimension.
     */
    default World getWorld() {
        return getDimension().getWorld();
    }

    /**
     * Builds the block entity's persisted NBT including component-contributed
     * data. The server writes base identity and coordinates after synchronous
     * component save handlers, so those base values take precedence over
     * conflicting handler tags. Asynchronous handler work is not awaited before
     * the map is built.
     */
    NbtMap saveNBT();

    /**
     * Builds NBT suitable for relocation by removing base position tags.
     * Components remove their own location-dependent data through the
     * clean-save event; the standard pairable component removes
     * {@code "pairx"}, {@code "pairz"}, and {@code "allay:pairlead"} rather
     * than carrying a former pairing into the new location.
     */
    NbtMap saveCleanNBT();

    /**
     * Applies supplied NBT to this block entity and its components. The server
     * retains base fields whose expected tags are absent, updates coordinates
     * within the existing dimension, and replaces persistent custom data only
     * when a {@code "PDC"} compound is supplied.
     */
    void loadNBT(NbtMap nbt);

    /**
     * Returns the current block state at this block entity's dimension
     * position.
     */
    default BlockState getBlockState() {
        var pos = getPosition();
        return pos.dimension().getBlockState(pos);
    }

    /**
     * Returns the optional custom display name associated with this block
     * entity. {@code null} means no custom name is set; an empty string is
     * still a present name for {@link #hasCustomName()}.
     */
    String getCustomName();

    /**
     * Assigns the custom name stored by the base component. {@code null}
     * removes the name, while an empty string remains present; assignment alone
     * does not broadcast a block-entity update to viewers.
     */
    void setCustomName(String customName);

    /**
     * Returns whether a non-{@code null} custom name is currently assigned.
     * Empty text still counts as a name because this check represents presence,
     * rather than testing whether the name has visible characters.
     */
    default boolean hasCustomName() {
        return getCustomName() != null;
    }
}
