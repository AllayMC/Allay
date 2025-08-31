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
import org.cloudburstmc.protocol.bedrock.data.LevelEventType;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.cloudburstmc.protocol.bedrock.data.SoundEvent;
import org.joml.Vector3i;
import org.joml.Vector3ic;

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
    private final Position3ic pos;
    /**
     * The layer of the block.
     */
    private final int layer;

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(Dimension dimension, Vector3ic pos) {
        this(dimension, pos, 0);
    }

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(Dimension dimension, Vector3ic pos, int layer) {
        this(dimension.getBlockState(pos), new Position3i(pos, dimension), layer);
    }

    /**
     * @see #Block(BlockState, Position3ic, int)
     */
    public Block(BlockState blockState, Position3ic pos) {
        this(blockState, pos, 0);
    }

    /**
     * Creates a new {@code Block} instance with the specified block state, position, and layer.
     *
     * @param blockState the state of the block, must not be null
     * @param pos        the position of the block in the world, must not be null
     * @param layer      the layer of the block, typically 0 for the main layer
     */
    public Block(BlockState blockState, Position3ic pos, int layer) {
        this.blockState = Preconditions.checkNotNull(blockState, "blockState cannot be null");
        this.pos = Preconditions.checkNotNull(pos, "pos cannot be null");
        this.layer = layer;
    }

    /**
     * Sets the property values of the block state and returns a new {@code Block} instance with the updated block state.
     *
     * @param propertyValues the list of property values to set for the block state
     *
     * @return a new {@code Block} instance with the updated block state
     */
    public Block setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return new Block(blockState.setPropertyValues(propertyValues), pos, layer);
    }

    /**
     * Sets a property value and returns a new {@code Block} instance with the updated block state.
     *
     * @param propertyValue the property value to set
     *
     * @return a new {@code Block} instance with the updated block state
     */
    public Block setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        return new Block(blockState.setPropertyValue(propertyValue), pos, layer);
    }

    /**
     * Sets a property value and returns a new {@code Block} instance with the updated block state.
     *
     * @param property   the property to set
     * @param value      the value to set for the property
     * @param <DATATYPE> the type of the property value
     * @param <PROPERTY> the type of the block property
     *
     * @return a new {@code Block} instance with the updated block state
     */
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> Block setPropertyValue(PROPERTY property, DATATYPE value) {
        return new Block(blockState.setPropertyValue(property, value), pos, layer);
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
     *
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
     *
     * @return a new {@code BlockStateWithPos} at the offset position
     */
    public Block offsetPos(int x, int y, int z, int layer) {
        var newPos = pos.add(x, y, z, new Vector3i());
        return new Block(
                pos.dimension().getBlockState(newPos, layer),
                new Position3i(newPos, pos.dimension()),
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
        getDimension().updateBlockProperty(propertyType, value, pos, layer);
    }

    /**
     * Adds a sound event at the center of this block's position.
     *
     * @param soundEvent the sound event to play
     */
    public void addLevelSoundEvent(SoundEvent soundEvent) {
        getDimension().addLevelSoundEvent(MathUtils.center(pos), soundEvent);
    }

    /**
     * Adds a level event at the center of this block's position.
     *
     * @param eventType the type of level event
     * @param data      additional event data
     */
    public void addLevelEvent(LevelEventType eventType, int data) {
        getDimension().addLevelEvent(MathUtils.center(pos), eventType, data);
    }

    /**
     * Adds a particle effect at the center of this block's position.
     *
     * @param particleType the type of particle to spawn
     */
    public void addParticle(ParticleType particleType) {
        getDimension().addParticle(MathUtils.center(pos), particleType, 0);
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
     *
     * @return {@code true} if the block was successfully broken, {@code false} otherwise.
     */
    public boolean breakBlock(ItemStack usedItem, Entity entity) {
        return getDimension().breakBlock(pos, usedItem, entity);
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
     * Retrieves the dimension in which this block state is located.
     *
     * @return the {@link Dimension} of the block state
     */
    public Dimension getDimension() {
        return pos.dimension();
    }

    /**
     * Retrieves the block entity at the current position.
     *
     * @param <T> the type of the block entity
     *
     * @return the block entity at the current position
     */
    @SuppressWarnings("ALL")
    public <T extends BlockEntity> T getBlockEntity() {
        return (T) getDimension().getBlockEntity(pos);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Block that)) return false;
        return blockState.equals(that.blockState) &&
               pos.equals(that.pos) &&
               layer == that.layer;
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockState, pos, layer);
    }
}
