package org.allaymc.api.block.dto;

import com.google.common.base.Preconditions;
import lombok.AccessLevel;
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

@Getter
@ToString
public class BlockStateWithPos implements BlockState {
    @Delegate
    @Getter(AccessLevel.NONE)
    private final BlockState blockState;
    private final Position3ic pos;
    private final int layer;

    public BlockStateWithPos(Vector3ic pos, Dimension dimension) {
        this(dimension.getBlockState(pos), new Position3i(pos, dimension), 0);
    }

    public BlockStateWithPos(BlockState blockState, Position3ic pos) {
        this(blockState, pos, 0);
    }

    public BlockStateWithPos(BlockState blockState, Position3ic pos, int layer) {
        this.blockState = Preconditions.checkNotNull(blockState, "blockState cannot be null");
        this.pos = pos;
        this.layer = layer;
    }

    @Override
    public BlockStateWithPos setPropertyValues(List<BlockPropertyType.BlockPropertyValue<?, ?, ?>> propertyValues) {
        return new BlockStateWithPos(blockState.setPropertyValues(propertyValues), pos, layer);
    }

    @Override
    public BlockStateWithPos setPropertyValue(BlockPropertyType.BlockPropertyValue<?, ?, ?> propertyValue) {
        return new BlockStateWithPos(blockState.setPropertyValue(propertyValue), pos, layer);
    }

    @Override
    public <DATATYPE, PROPERTY extends BlockPropertyType<DATATYPE>> BlockStateWithPos setPropertyValue(PROPERTY property, DATATYPE value) {
        return new BlockStateWithPos(blockState.setPropertyValue(property, value), pos, layer);
    }

    /**
     * Returns a new {@code BlockStateWithPos} offset from the current position in the specified direction.
     *
     * @param blockFace the direction to offset.
     *
     * @return a new {@code BlockStateWithPos} at the offset position.
     */
    public BlockStateWithPos offsetPos(BlockFace blockFace) {
        var offset = blockFace.getOffset();
        return offsetPos(offset.x(), offset.y(), offset.z());
    }

    /**
     * Returns a new {@code BlockStateWithPos} offset from the current position by the given deltas.
     *
     * @param x the x-axis offset.
     * @param y the y-axis offset.
     * @param z the z-axis offset.
     *
     * @return a new {@code BlockStateWithPos} at the offset position.
     */
    public BlockStateWithPos offsetPos(int x, int y, int z) {
        var newPos = pos.add(x, y, z, new Vector3i());
        return new BlockStateWithPos(
                pos.dimension().getBlockState(newPos, layer),
                new Position3i(newPos, pos.dimension()),
                layer
        );
    }

    /**
     * Updates a property of the block at the current position and layer.
     *
     * @param <DATATYPE>   the type of the property value.
     * @param propertyType the type of the block property to update.
     * @param value        the new value of the block property.
     */
    public <DATATYPE> void updateBlockProperty(BlockPropertyType<DATATYPE> propertyType, DATATYPE value) {
        getDimension().updateBlockProperty(propertyType, value, pos, layer);
    }

    /**
     * Adds a sound event at the center of this block's position.
     *
     * @param soundEvent the sound event to play.
     */
    public void addLevelSoundEvent(SoundEvent soundEvent) {
        getDimension().addLevelSoundEvent(MathUtils.center(pos), soundEvent);
    }

    /**
     * Adds a level event at the center of this block's position.
     *
     * @param eventType the type of level event.
     * @param data      additional event data.
     */
    public void addLevelEvent(LevelEventType eventType, int data) {
        getDimension().addLevelEvent(MathUtils.center(pos), eventType, data);
    }

    /**
     * Adds a particle effect at the center of this block's position.
     *
     * @param particleType the type of particle to spawn.
     */
    public void addParticle(ParticleType particleType) {
        getDimension().addParticle(MathUtils.center(pos), particleType, 0);
    }

    public boolean breakBlock() {
        return breakBlock(null, null);
    }

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
     * @return the {@link Dimension} of the block state.
     */
    public Dimension getDimension() {
        return pos.dimension();
    }

    /**
     * Retrieves the block entity at the current position.
     *
     * @param <T> the type of the block entity.
     *
     * @return the block entity at the current position.
     */
    public <T extends BlockEntity> T getBlockEntity() {
        return (T) getDimension().getBlockEntity(pos);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BlockStateWithPos that)) return false;
        return Objects.equals(blockState, that.blockState);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(blockState);
    }
}
