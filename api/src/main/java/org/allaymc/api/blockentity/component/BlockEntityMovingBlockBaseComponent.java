package org.allaymc.api.blockentity.component;

import org.allaymc.api.block.type.BlockState;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3ic;

/**
 * {@code BlockEntityMovingBlockBaseComponent} exposes the original state and
 * optional block-entity payload carried by a temporary piston-moving block. The
 * server retains the piston position and movement direction alongside that data
 * so the arm can resolve and restore moved contents when animation finishes.
 *
 * @author daoge_cmd
 */
public interface BlockEntityMovingBlockBaseComponent extends BlockEntityBaseComponent {

    /**
     * Returns the original block state retained by the moving placeholder. The
     * server initially leaves it {@code null}, and the piston finalization path
     * treats an absent original state as an air replacement.
     */
    BlockState getMovingBlockState();

    /**
     * Retains the original state used when the piston finalizes this
     * placeholder. The server performs no copy, validation, or viewer update;
     * {@code null} removes the state and selects finalization's air fallback.
     */
    void setMovingBlockState(BlockState blockState);

    /**
     * Returns the retained position reference of the piston moving this block.
     * The server starts with {@code (0, -1, 0)} and exposes the same reference
     * supplied through its setter, including mutable implementations.
     */
    Vector3ic getPistonPos();

    /**
     * Retains the supplied piston coordinate reference without copying or
     * broadcasting. Callers provide a usable non-{@code null} vector for later
     * serialization; mutating a retained vector also changes the stored piston
     * coordinates.
     */
    void setPistonPos(Vector3ic pos);

    /**
     * Returns the retained original block-entity payload, or {@code null} when
     * absent. The server returns the supplied map reference directly; piston
     * creation normally supplies clean-save data suitable for relocation to a
     * new position.
     */
    NbtMap getMovingBlockEntityNBT();

    /**
     * Retains the payload to load into the destination block entity after
     * restoration. {@code null} clears it; callers normally supply clean-save
     * data because base coordinates in ordinary saved NBT can replace the
     * destination's position.
     */
    void setMovingBlockEntityNBT(NbtMap nbt);

    /**
     * Returns whether the moving placeholder records motion away from its
     * piston.
     */
    boolean isExpanding();

    /**
     * Replaces the stored expanding flag without directly starting piston
     * movement.
     */
    void setExpanding(boolean expanding);
}
