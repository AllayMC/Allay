package org.allaymc.server.blockentity.component.piston;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityMovingBlockBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityMovingBlock;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.hash.HashUtils;
import org.allaymc.server.blockentity.component.BlockEntityBaseComponentImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code BlockEntityMovingBlockBaseComponentImpl} carries a moved block's
 * original state, optional block-entity NBT, piston coordinates, and expanding
 * flag while a temporary moving block occupies its destination. Its generated
 * state and coordinate setters retain references directly; this component does
 * not perform the arm's restoration or animation itself.
 *
 * <p>
 * NBT stores the original block as a name and states compound. Loading rebuilds
 * that representation and hashes it to find a registered palette state; an
 * unrecognized hash preserves the previous state rather than selecting another
 * block. The original block-entity payload is retained separately from its
 * block state. Missing piston-coordinate entries select {@code (0, -1, 0)}, and
 * a missing expanding flag selects {@code true}, even during repeated loads.
 *
 * @author daoge_cmd
 */
public class BlockEntityMovingBlockBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityMovingBlockBaseComponent {

    /**
     * {@code TAG_MOVING_BLOCK} names the original-state compound. These
     * moving-block tag constants identify its name/properties, carried entity
     * data, piston coordinates, and direction.
     */
    protected static final String TAG_MOVING_BLOCK = "movingBlock";
    /**
     * Original block identifier entry.
     */
    protected static final String TAG_MOVING_BLOCK_NAME = "name";
    /**
     * Original block property compound.
     */
    protected static final String TAG_MOVING_BLOCK_STATES = "states";
    /**
     * Carried block-entity payload compound.
     */
    protected static final String TAG_MOVING_ENTITY = "movingEntity";
    /**
     * Persisted piston x coordinate.
     */
    protected static final String TAG_PISTON_POS_X = "pistonPosX";
    /**
     * Persisted piston y coordinate.
     */
    protected static final String TAG_PISTON_POS_Y = "pistonPosY";
    /**
     * Persisted piston z coordinate.
     */
    protected static final String TAG_PISTON_POS_Z = "pistonPosZ";
    /**
     * Persisted outward movement flag.
     */
    protected static final String TAG_EXPANDING = "expanding";

    /**
     * Injected owning moving-block entity.
     */
    @ComponentObject
    protected BlockEntityMovingBlock thisMovingBlock;

    /**
     * Optional original block state.
     */
    @Getter
    @Setter
    protected BlockState movingBlockState;
    /**
     * {@code pistonPos} holds the shared piston coordinate reference. The
     * generated setter retains supplied vectors directly, and saving
     * dereferences the current value without a {@code null} guard.
     */
    @Getter
    @Setter
    protected Vector3ic pistonPos = new Vector3i(0, -1, 0);
    /**
     * {@code movingBlockEntityNbt} holds the original entity's optional
     * relocation payload. Its accessor and setter share the supplied map with
     * the finalization path.
     */
    protected NbtMap movingBlockEntityNbt;
    /**
     * Current outward movement selection.
     */
    @Getter
    @Setter
    protected boolean expanding = true;

    /**
     * Creates common moving-block state with an unset original block. Piston
     * coordinates initially use {@code (0, -1, 0)}, and the expanding flag
     * starts {@code true} before initialization NBT is loaded.
     */
    public BlockEntityMovingBlockBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Builds common NBT and the original block's name/property representation.
     * An absent original state omits its compound, while a present state
     * contributes its identifier and property compound without unrelated
     * block-state metadata. The carried entity payload is serialized separately
     * when present, keeping it associated with this temporary block. Piston
     * coordinates and expanding state are always written, so the retained
     * piston vector must be non-{@code null} even when no original block state
     * has been assigned.
     */
    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        if (movingBlockState != null) {
            var movingBlockBuilder = NbtMap.builder();
            movingBlockBuilder.putString(TAG_MOVING_BLOCK_NAME, movingBlockState.getBlockType().getIdentifier().toString());
            // Moving-block NBT carries only the original identifier and states,
            // so retain the property compound without copying unrelated
            // block-state metadata into this payload.
            var blockStateNbt = movingBlockState.getBlockStateNBT();
            var states = blockStateNbt.getCompound("states");
            if (states != null) {
                movingBlockBuilder.putCompound(TAG_MOVING_BLOCK_STATES, states);
            }
            builder.putCompound(TAG_MOVING_BLOCK, movingBlockBuilder.build());
        }

        if (movingBlockEntityNbt != null) {
            builder.putCompound(TAG_MOVING_ENTITY, movingBlockEntityNbt);
        }

        builder.putInt(TAG_PISTON_POS_X, pistonPos.x());
        builder.putInt(TAG_PISTON_POS_Y, pistonPos.y());
        builder.putInt(TAG_PISTON_POS_Z, pistonPos.z());
        builder.putBoolean(TAG_EXPANDING, expanding);

        return builder.build();
    }

    /**
     * Loads common data and resolves a supplied original block through the
     * palette. The name and states are rebuilt into hash input, with missing
     * states replaced by an empty compound. A hash miss leaves the previous
     * original state intact. An absent entity compound also preserves its
     * current payload, while missing piston coordinates reset to
     * {@code (0, -1, 0)} and missing expanding state resets to {@code true};
     * these defaults do not preserve earlier values during a repeated partial
     * load.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForCompound(TAG_MOVING_BLOCK, movingBlock -> {
            var blockName = movingBlock.getString(TAG_MOVING_BLOCK_NAME);
            var states = movingBlock.getCompound(TAG_MOVING_BLOCK_STATES);

            // Palette lookup hashes the identifier and property compound
            // together; an absent states value therefore contributes an empty
            // compound to that representation.
            var blockStateNbt = NbtMap.builder()
                    .putString("name", blockName)
                    .putCompound("states", states != null ? states : NbtMap.EMPTY)
                    .build();

            int blockStateHash = HashUtils.fnv1a_32_nbt(blockStateNbt);
            var blockState = Registries.BLOCK_STATE_PALETTE.get(blockStateHash);
            // A missing palette entry cannot identify a replacement state, so
            // keep the previous original state rather than substituting an
            // unrelated block during loading.
            if (blockState != null) {
                movingBlockState = blockState;
            }
        });

        nbt.listenForCompound(TAG_MOVING_ENTITY, entity -> movingBlockEntityNbt = entity);

        var pistonX = nbt.getInt(TAG_PISTON_POS_X, 0);
        var pistonY = nbt.getInt(TAG_PISTON_POS_Y, -1);
        var pistonZ = nbt.getInt(TAG_PISTON_POS_Z, 0);
        pistonPos = new Vector3i(pistonX, pistonY, pistonZ);

        expanding = nbt.getBoolean(TAG_EXPANDING, true);
    }

    /**
     * Returns the retained relocation payload without constructing a new map.
     * {@code null} means this placeholder carries no original block entity; a
     * present map is shared with the later restoration path.
     */
    @Override
    public NbtMap getMovingBlockEntityNBT() {
        return this.movingBlockEntityNbt;
    }

    /**
     * Retains a relocation payload for destination block-entity loading.
     * {@code null} clears the payload; this setter neither copies the map nor
     * removes position tags, so callers normally provide clean-save NBT.
     */
    @Override
    public void setMovingBlockEntityNBT(NbtMap nbt) {
        this.movingBlockEntityNbt = nbt;
    }
}
