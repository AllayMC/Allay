package org.allaymc.api.block.dto;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Delegate;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.property.type.BlockPropertyType;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.math.location.Location3i;
import org.allaymc.api.math.position.Position3i;
import org.allaymc.api.math.position.Position3ic;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 * {@code Block} represents a stored block state together with a dimension-bearing
 * position and layer. State-property operations create another snapshot, while
 * world helpers act on the current dimension contents. Constructors accepting
 * {@code Position3ic} retain that reference, so callers must preserve its
 * coordinates to keep equality and hashing stable.
 *
 * @author daoge_cmd | IWareQ
 */
@Getter
@ToString
public class Block {
    /**
     * Stored snapshot state reference.
     */
    @Delegate
    private final BlockState blockState;
    /**
     * {@code position} supplies coordinates and dimension for world operations.
     * Constructors accepting a position retain it directly, so later mutation
     * changes this snapshot's location and hash.
     */
    private final Position3ic position;
    /**
     * Stored block storage layer.
     */
    private final int layer;

    /**
     * Creates a snapshot of the dimension's main-layer state at the supplied
     * coordinates. The coordinates are copied into a dimension-bearing position,
     * and the stored layer defaults to {@code 0}.
     */
    public Block(Dimension dimension, Vector3ic position) {
        this(dimension, position, 0);
    }

    /**
     * Creates a snapshot with copied coordinates and the supplied layer label. This
     * constructor currently reads the dimension's main-layer state regardless of
     * {@code layer}, so the stored state can differ from that layer's contents.
     */
    public Block(Dimension dimension, Vector3ic position, int layer) {
        this(dimension.getBlockState(position), new Position3i(position, dimension), layer);
    }

    /**
     * Creates a snapshot from the supplied state and retained dimension-bearing
     * position. Both references must be non-{@code null}, and this overload stores
     * main layer {@code 0} without reading the world.
     */
    public Block(BlockState blockState, Position3ic position) {
        this(blockState, position, 0);
    }

    /**
     * Creates a snapshot retaining the supplied state and position references. Both
     * must be non-{@code null}; this constructor stores the layer without
     * validation or any check that the state matches current world contents.
     */
    public Block(BlockState blockState, Position3ic position, int layer) {
        this.blockState = Preconditions.checkNotNull(blockState, "blockState cannot be null");
        this.position = Preconditions.checkNotNull(position, "position cannot be null");
        this.layer = layer;
    }

    /**
     * Returns a new snapshot with the requested state properties and the same
     * position reference and layer. The dimension remains unchanged, and
     * unsupported or duplicate properties follow the state's validation failures.
     */
    public Block setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return new Block(blockState.setPropertyValues(propertyValues), position, layer);
    }

    /**
     * Returns a new snapshot with the supplied property wrapper applied to its
     * state. The position and layer are retained, and this operation does not write
     * the selected state into the dimension.
     */
    public Block setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        return new Block(blockState.setPropertyValue(propertyValue), position, layer);
    }

    /**
     * Returns a new snapshot after the property's raw-value conversion and state
     * selection. The dimension remains unchanged, and the property domain
     * determines failures for unsupported or invalid values.
     */
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> Block setPropertyValue(PROPERTY property, DATATYPE value) {
        return new Block(blockState.setPropertyValue(property, value), position, layer);
    }

    /**
     * Reads a new snapshot one block along the supplied face in the current layer.
     * The destination state comes from current world contents rather than this
     * snapshot's stored state.
     */
    public Block offsetPos(BlockFace blockFace) {
        return offsetPos(blockFace, layer);
    }

    /**
     * Reads a new snapshot one block along the supplied face in the selected layer.
     * The destination receives copied coordinates in the same dimension, and this
     * snapshot remains unchanged.
     */
    public Block offsetPos(BlockFace blockFace, int layer) {
        var offset = blockFace.getOffset();
        return offsetPos(offset.x(), offset.y(), offset.z(), layer);
    }

    /**
     * Reads a new snapshot at the supplied coordinate displacement in the current
     * layer. The destination state is read from the dimension, and this snapshot's
     * position reference remains unchanged.
     */
    public Block offsetPos(int x, int y, int z) {
        return offsetPos(x, y, z, layer);
    }

    /**
     * Reads a new snapshot at the displaced coordinates in the selected layer. The
     * destination position is newly allocated in the same dimension, preserving the
     * source snapshot's coordinates.
     */
    public Block offsetPos(int x, int y, int z, int layer) {
        var newPos = position.add(x, y, z, new Vector3i());
        return new Block(
                position.dimension().getBlockState(newPos, layer),
                new Position3i(newPos, position.dimension()),
                layer
        );
    }

    /**
     * Updates the live dimension state at this position and stored layer. This
     * snapshot retains its original state reference; the dimension helper skips
     * unloaded chunks and applies the property's normal state-conversion rules.
     */
    public <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value) {
        getDimension().updateBlockProperty(propertyType, value, position, layer);
    }

    /**
     * Emits the supplied sound through the dimension at the center of this block
     * position.
     */
    public void addSound(Sound sound) {
        getDimension().addSound(MathUtils.center(position), sound);
    }

    /**
     * Emits the supplied particle through the dimension at the center of this block
     * position.
     */
    public void addParticle(Particle particle) {
        getDimension().addParticle(MathUtils.center(position), particle);
    }

    /**
     * Attempts to break the live main-layer block at this position without a
     * supplied tool or entity. This snapshot remains unchanged, and the dimension
     * reports whether the break succeeds.
     */
    public boolean breakBlock() {
        return breakBlock(null, null);
    }

    /**
     * Attempts to break the live main-layer block using the supplied item and
     * entity, either of which may be {@code null}. This helper does not pass the
     * stored layer to the dimension.
     */
    public boolean breakBlock(ItemStack usedItem, Entity entity) {
        return getDimension().breakBlock(position, usedItem, entity);
    }

    /**
     * Writes the supplied state to the dimension's main layer and returns a
     * snapshot retaining this position and stored layer. The write does not use the
     * stored layer, and this snapshot's state reference remains unchanged.
     */
    public Block replaceState(BlockState blockState) {
        this.getDimension().setBlockState(this.position, blockState);
        return new Block(blockState, this.position, layer);
    }

    /**
     * Requests a future block update at this position through the dimension's
     * update manager. The helper passes the delay and position without the stored
     * layer, and scheduling does not change this snapshot.
     */
    public void scheduleUpdateInDelay(Duration delay) {
        this.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(this.position, delay);
    }

    /**
     * Returns whether the stored snapshot state belongs to the air block type.
     */
    public boolean isAir() {
        return blockState.getBlockType() == BlockTypes.AIR;
    }

    /**
     * Returns whether current world power at this position exceeds zero.
     */
    public boolean isPowered() {
        return this.getDimension().isPoweredAt(this.position);
    }

    /**
     * Returns the dimension's current maximum received redstone power at this
     * position.
     */
    public int getPower() {
        return this.getDimension().getPowerAt(this.position);
    }

    /**
     * Returns the current strong redstone power received from all faces at this
     * position.
     */
    public int getStrongPower() {
        return this.getDimension().getStrongPowerAt(this.position);
    }

    /**
     * Returns the current strong redstone power while excluding the supplied
     * neighboring faces.
     */
    public int getStrongPower(BlockFace... excludeFaces) {
        return this.getDimension().getStrongPowerAt(this.position, excludeFaces);
    }

    /**
     * Returns the current weak redstone power received from all faces at this
     * position.
     */
    public int getWeakPower() {
        return this.getDimension().getWeakPowerAt(this.position);
    }

    /**
     * Returns the current weak redstone power while excluding the supplied
     * neighboring faces.
     */
    public int getWeakPower(BlockFace... excludeFaces) {
        return this.getDimension().getWeakPowerAt(this.position, excludeFaces);
    }

    /**
     * Returns the dimension retained by this snapshot's current position reference.
     */
    public Dimension getDimension() {
        return position.dimension();
    }

    /**
     * Returns the current block entity at this position, or {@code null} when
     * absent. The unchecked generic cast leaves callers responsible for choosing a
     * type compatible with the entity actually stored there.
     */
    @SuppressWarnings("ALL")
    public <T extends BlockEntity> T getBlockEntity() {
        return (T) getDimension().getBlockEntity(position);
    }

    /**
     * Returns a new location copying this position's coordinates and dimension.
     */
    public Location3i getLocation() {
        return new Location3i(this.position.x(), this.position.y(), this.position.z(), this.getDimension());
    }

    /**
     * Returns whether another block has equal stored state, position, and layer
     * values.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block that)) return false;
        return blockState.equals(that.blockState) &&
               position.equals(that.position) &&
               layer == that.layer;
    }

    /**
     * Returns a hash derived from the stored state, position, and layer.
     */
    @Override
    public int hashCode() {
        return Objects.hash(blockState, position, layer);
    }
}
