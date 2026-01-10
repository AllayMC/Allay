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
 * An immutable data transfer object that represents a block in a world. It contains the block's state,
 * position and layer. All the methods that available in {@link BlockState} are also available in this class.
 *
 * @author daoge_cmd | IWareQ
 */
@Getter
@ToString
public class Block {
    /**
     * The block state of this block.
     */
    @Delegate
    private final BlockState blockState;
    /**
     * The position of the block in the world.
     */
    private final Position3ic position;
    /**
     * The layer of the block.
     */
    private final int layer;

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(Dimension dimension, Vector3ic position) {
        this(dimension, position, 0);
    }

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(Dimension dimension, Vector3ic position, int layer) {
        this(dimension.getBlockState(position), new Position3i(position, dimension), layer);
    }

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(BlockState blockState, Position3ic position) {
        this(blockState, position, 0);
    }

    /**
     * Creates a new {@code Block} instance with the specified block state, position, and layer.
     *
     * @param blockState the state of the block, must not be null
     * @param position   the position of the block in the world, must not be null
     * @param layer      the layer of the block, typically 0 for the main layer
     */
    public Block(BlockState blockState, Position3ic position, int layer) {
        this.blockState = Preconditions.checkNotNull(blockState, "blockState cannot be null");
        this.position = Preconditions.checkNotNull(position, "position cannot be null");
        this.layer = layer;
    }

    /**
     * Sets the property values of the block state and returns a new {@code Block} instance with the updated block state.
     *
     * @param propertyValues the list of property values to set for the block state
     * @return a new {@code Block} instance with the updated block state
     */
    public Block setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return new Block(blockState.setPropertyValues(propertyValues), position, layer);
    }

    /**
     * Sets a property value and returns a new {@code Block} instance with the updated block state.
     *
     * @param propertyValue the property value to set
     * @return a new {@code Block} instance with the updated block state
     */
    public Block setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        return new Block(blockState.setPropertyValue(propertyValue), position, layer);
    }

    /**
     * Sets a property value and returns a new {@code Block} instance with the updated block state.
     *
     * @param property   the property to set
     * @param value      the value to set for the property
     * @param <DATATYPE> the type of the property value
     * @param <PROPERTY> the type of the block property
     * @return a new {@code Block} instance with the updated block state
     */
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> Block setPropertyValue(PROPERTY property, DATATYPE value) {
        return new Block(blockState.setPropertyValue(property, value), position, layer);
    }

    /**
     * @see #offsetPos(BlockFace, int)
     */
    public Block offsetPos(BlockFace blockFace) {
        return offsetPos(blockFace, layer);
    }

    /**
     * Returns a new {@code BlockStateWithPos} offset from the current position in the specified direction.
     *
     * @param blockFace the direction to offset
     * @return a new {@code BlockStateWithPos} at the offset position
     */
    public Block offsetPos(BlockFace blockFace, int layer) {
        var offset = blockFace.getOffset();
        return offsetPos(offset.x(), offset.y(), offset.z(), layer);
    }

    /**
     * @see #offsetPos(int, int, int, int)
     */
    public Block offsetPos(int x, int y, int z) {
        return offsetPos(x, y, z, layer);
    }

    /**
     * Returns a new {@code BlockStateWithPos} offset from the current position by the given deltas.
     *
     * @param x     the x-axis offset
     * @param y     the y-axis offset
     * @param z     the z-axis offset
     * @param layer the layer of the block
     * @return a new {@code BlockStateWithPos} at the offset position
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
     * Updates a property of the block at the current position and layer.
     *
     * @param <DATATYPE>   the type of the property value
     * @param propertyType the type of the block property to update
     * @param value        the new value of the block property
     */
    public <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value) {
        getDimension().updateBlockProperty(propertyType, value, position, layer);
    }

    /**
     * Adds a sound at the center of this block's position.
     *
     * @param sound the sound to add
     */
    public void addSound(Sound sound) {
        getDimension().addSound(MathUtils.center(position), sound);
    }

    /**
     * Adds a particle effect at the center of this block's position.
     *
     * @param particle the particle to spawn
     */
    public void addParticle(Particle particle) {
        getDimension().addParticle(MathUtils.center(position), particle);
    }

    /**
     * Breaks the block at the current position and layer.
     *
     * @return {@code true} if the block was successfully broken, {@code false} otherwise.
     */
    public boolean breakBlock() {
        return breakBlock(null, null);
    }

    /**
     * Breaks the block at the current position and layer using the specified item and entity.
     *
     * @param usedItem the item used to break the block, can be {@code null}
     * @param entity   the entity that broke the block, can be {@code null}
     * @return {@code true} if the block was successfully broken, {@code false} otherwise.
     */
    public boolean breakBlock(ItemStack usedItem, Entity entity) {
        return getDimension().breakBlock(position, usedItem, entity);
    }

    /**
     * Replaces the block state at this block's position with a new block state.
     *
     * @param blockState the new block state to set at this position
     * @return a new {@code Block} instance with the updated block state
     */
    public Block replaceState(BlockState blockState) {
        this.getDimension().setBlockState(this.position, blockState);
        return new Block(blockState, this.position, layer);
    }

    /**
     * Schedule a block update.
     *
     * @param delay the duration to wait before triggering the block update
     */
    public void scheduleUpdateInDelay(Duration delay) {
        this.getDimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(this.position, delay);
    }

    /**
     * Checks whether the current block is air.
     *
     * @return {@code true} if the block type is {@link BlockTypes#AIR}, {@code false} otherwise.
     */
    public boolean isAir() {
        return blockState.getBlockType() == BlockTypes.AIR;
    }

    /**
     * Checks whether this block's position is currently receiving any redstone power.
     *
     * @return {@code true} if this block is receiving redstone power, {@code false} otherwise
     */
    public boolean isReceivingRedstonePower() {
        return this.getDimension().isPoweredAt(this.position);
    }

    /**
     * Calculates the maximum redstone power level received at this block's position from all 6 faces.
     * This considers both direct weak power and strong power through solid blocks.
     *
     * @return the maximum redstone power level (0-15)
     */
    public int getRedstonePower() {
        return this.getDimension().getPowerAt(this.position);
    }

    /**
     * Gets the maximum strong redstone power being received at this block's position from all 6 faces.
     *
     * @return the strong redstone power level (0-15)
     */
    public int getStrongRedstonePower() {
        return this.getDimension().getStrongPowerAt(this.position);
    }

    /**
     * Gets the maximum strong redstone power being received at this block's position.
     *
     * @param excludeFaces the faces to exclude from the check
     * @return the strong redstone power level (0-15)
     */
    public int getStrongRedstonePower(BlockFace... excludeFaces) {
        return this.getDimension().getStrongPowerAt(this.position, excludeFaces);
    }

    /**
     * Gets the maximum weak redstone power being received at this block's position from all 6 faces.
     *
     * @return the weak redstone power level (0-15)
     */
    public int getWeakRedstonePower() {
        return this.getDimension().getWeakPowerAt(this.position);
    }

    /**
     * Gets the maximum weak redstone power being received at this block's position.
     *
     * @param excludeFaces the faces to exclude from the check
     * @return the weak redstone power level (0-15)
     */
    public int getWeakRedstonePower(BlockFace... excludeFaces) {
        return this.getDimension().getWeakPowerAt(this.position, excludeFaces);
    }

    /**
     * Retrieves the dimension in which this block state is located.
     *
     * @return the {@link Dimension} of the block state
     */
    public Dimension getDimension() {
        return position.dimension();
    }

    /**
     * Retrieves the block entity at the current position.
     *
     * @param <T> the type of the block entity
     * @return the block entity at the current position
     */
    @SuppressWarnings("ALL")
    public <T extends BlockEntity> T getBlockEntity() {
        return (T) getDimension().getBlockEntity(position);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block that)) return false;
        return blockState.equals(that.blockState) &&
               position.equals(that.position) &&
               layer == that.layer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockState, position, layer);
    }
}
