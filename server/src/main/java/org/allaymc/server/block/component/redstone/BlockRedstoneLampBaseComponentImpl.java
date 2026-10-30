package org.allaymc.server.block.component.redstone;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.server.block.component.BlockBaseComponentImpl;

import java.time.Duration;

/**
 * {@code BlockRedstoneLampBaseComponentImpl} switches between lit and unlit lamp types
 * according to redstone input. An unlit lamp reacts to power immediately, while a lit
 * lamp requests a delayed check when power disappears. The component's fixed
 * {@code lit} flag identifies which registered variant owns each callback.
 *
 * <p>
 * The delayed callback samples power again before selecting the unlit type. A returning
 * input therefore keeps the lamp lit even when a turn-off update is already pending.
 * Variant switches use each type's default state and do not inspect the replacement
 * result. The timing policy separates the immediate turn-on path from loss-of-power
 * checking without retaining another per-position timer map in this component.
 *
 * @author daoge_cmd
 */
public class BlockRedstoneLampBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code TURN_OFF_DELAY} postpones the loss-of-power check by four game ticks,
     * allowing a returning input to keep a lamp lit.
     */
    protected static final Duration TURN_OFF_DELAY = Duration.ofMillis(200);

    /**
     * {@code lit} identifies this component's registered lamp variant. Callbacks use
     * that fixed identity when deciding whether a state replacement is necessary.
     */
    protected final boolean lit;

    /**
     * Creates a lamp component with the supplied fixed lit-variant identity.
     */
    public BlockRedstoneLampBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, boolean lit) {
        super(blockType);
        this.lit = lit;
    }

    /**
     * Turns a newly placed unlit lamp on when its position receives power. Inherited
     * placement handling runs first, and the input is sampled at the old block
     * wrapper's position.
     */
    @Override
    public void afterPlaced(Block oldBlock, BlockState newBlockState, PlayerInteractInfo placementInfo) {
        super.afterPlaced(oldBlock, newBlockState, placementInfo);

        if (!lit && oldBlock.isPowered()) {
            switchToLit(oldBlock);
        }
    }

    /**
     * Rechecks redstone input after inherited neighbor handling and chooses immediate
     * lighting or delayed release. An unlit lamp receiving power switches on in this
     * callback. A lit lamp losing power requests a later input check instead of
     * switching off immediately.
     *
     * <p>
     * The position-based scheduler holds one deadline per position, so another
     * loss-of-power notification can replace that pending check. The delayed callback
     * samples input again, allowing returning power to preserve the lit state even
     * after a release has been requested.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);

        var powered = block.isPowered();

        if (lit && !powered) {
            // Delay the loss-of-power decision so the scheduled callback can
            // observe returning input before changing a lamp that is still visually lit.
            block.scheduleUpdateInDelay(TURN_OFF_DELAY);
        } else if (!lit && powered) {
            switchToLit(block);
        }
    }

    /**
     * Turns off a lit lamp only if its position still has no input power. Returning
     * power makes the delayed update harmless, while the unlit variant ignores this
     * callback.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        if (lit && !block.isPowered()) {
            switchToUnlit(block);
        }
    }

    /**
     * Requests replacement with the default lit lamp state at this position. The helper
     * ignores the replacement result, so callers do not receive confirmation that the
     * dimension accepted the change.
     */
    protected void switchToLit(Block block) {
        block.replaceState(BlockTypes.LIT_REDSTONE_LAMP.getDefaultState());
    }

    /**
     * Requests replacement with the default unlit lamp state at this position. The
     * helper ignores the replacement result, so callers do not receive confirmation
     * that the dimension accepted the change.
     */
    protected void switchToUnlit(Block block) {
        block.replaceState(BlockTypes.REDSTONE_LAMP.getDefaultState());
    }
}
