package cn.allay.material;

import cn.allay.blockentity.BlockEntity;

import javax.annotation.Nullable;

public interface BlockMaterial extends Material {
    /**
     * Get whether this block is a full sized block.
     *
     * @return the value of the test
     */
    boolean isFullBlock();

    /**
     * Get whether this block is Transparent.
     *
     * @return the value of the test
     */
    boolean isTransparent();

    /**
     * Get whether this block emits a Redstone signal.
     *
     * @return the value of the test
     */
    boolean isPowerSource();

    /**
     * Get whether this block is a liquid.
     *
     * @return the value of the test
     */
    boolean isLiquid();

    /**
     * Get whether this block is a solid.
     *
     * @return the value of the test
     */
    boolean isSolid();

    /**
     * Get whether this block breaks when it is pushed by a piston.
     *
     * @return true if the block breaks
     */
    boolean isBreaksWhenMoved();

    /**
     * Get whether this block can be pushed by a piston.
     *
     * @return true if the block cannot be pushed
     */
    boolean isUnpushable();

    /**
     * Get whether this block is ticked randomly.
     *
     * @return true if this block is ticked randomly
     */
    boolean isTicksRandomly();

    /**
     * Get whether this block prevents movement.
     *
     * @return true if this block blocks movement
     */
    boolean isMovementBlocker();

    /**
     * Get whether this block will burn.
     *
     * @return true if this block will burn
     */
    boolean isBurnable();

    /**
     * Get whether this block needs to be broken by a tool for maximum
     * speed.
     *
     * @return true if a tool is required
     */
    boolean isToolRequired();

    /**
     * Get whether this block is replaced when a block is placed over it
     * (for example, tall grass).
     *
     * @return true if the block is replaced
     */
    boolean isReplacedDuringPlacement();

    /**
     * Gets whether the block has a container (Item container).
     *
     * @return If it has a container
     */
    boolean hasContainer();

    /**
     * Gets whether the block is a Block Entity.
     *
     * @return If it is a tile entity
     */
    boolean isBlockEntity();

    /**
     * Gets the Block Entity at this block pos
     *
     * @return the Block Entity(can be null)
     */
    @Nullable
    BlockEntity getBlockEntity();

    /**
     * Get the hardness factor for this block.
     *
     * @return the hardness factor
     */
    float getHardness();

    /**
     * Get the resistance factor for this block.
     *
     * @return the resistance factor
     */
    float getResistance();

    /**
     * Get the friction factor for this block.
     *
     * @return the friction factor
     */
    float getFrictionFactor();

    /**
     * Get the light level for this block.
     *
     * @return the light level
     */
    int getLightLevel();

    /**
     * Get the opacity of the block.
     *
     * @return opacity
     */
    int getLightFilter();

    /**
     * Get the map color.
     *
     * @return or 0
     */
    int getMapColor();
}
