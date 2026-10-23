package org.allaymc.server.block.component.plant;

import it.unimi.dsi.fastutil.longs.Long2LongMap;
import it.unimi.dsi.fastutil.longs.Long2LongOpenHashMap;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.event.block.BlockFadeEvent;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.server.block.FortuneDropHelper;
import org.allaymc.server.block.component.BlockBaseComponentImpl;
import org.allaymc.server.item.data.ItemId;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import static org.allaymc.api.block.property.type.BlockPropertyTypes.UPDATE_BIT;

/**
 * {@code BlockLeavesBaseComponentImpl} implements delayed leaf-update propagation, bounded
 * support search, decay, and leaf drops. Neighbor and scheduled callbacks mark leaves for
 * later random evaluation and enqueue neighboring unmarked leaves rather than evaluating an
 * entire canopy immediately.
 *
 * <p>
 * Random evaluation keeps persistent leaves or leaves connected through a bounded chain to a
 * supporter; unsupported leaves propose fading before ordinary breaking. The support search
 * stores the greatest remaining distance seen at each packed position, allowing a later
 * shorter route to revisit a position without repeating weaker searches. Drop configuration
 * supplies optional saplings, apples, and divisor choices, while shears bypass those random
 * rewards and use the inherited block drop.
 *
 * @author daoge_cmd
 */
public class BlockLeavesBaseComponentImpl extends BlockBaseComponentImpl {

    /**
     * {@code LOG_FINDING_ORDER} fixes the six-direction traversal sequence used by the bounded
     * leaf-support search when exploring neighboring positions.
     */
    protected static final BlockFace[] LOG_FINDING_ORDER = new BlockFace[]{
            BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST, BlockFace.DOWN, BlockFace.UP
    };

    /**
     * {@code saplingType} lazily supplies the sapling drop type. {@code null} disables sapling
     * drops, and a supplier returning {@code null} also skips that reward.
     */
    protected final Supplier<ItemType<?>> saplingType; // Nullable
    /**
     * Optional apple-drop permission flag.
     */
    protected final boolean canDropApple;
    /**
     * {@code dropMoreSaplings} selects a sapling divisor of forty instead of twenty. Despite
     * the name, this choice lowers the unenchanted drop probability in the current
     * calculation.
     */
    protected final boolean dropMoreSaplings;

    /**
     * Creates leaf behavior with a deferred optional sapling type and drop flags. A {@code
     * null} sapling supplier disables that reward; the booleans control apple eligibility and
     * the sapling chance divisor.
     */
    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, Supplier<ItemType<?>> saplingTypeSupplier, boolean canDropApple, boolean dropMoreSaplings) {
        super(blockType);
        this.saplingType = saplingTypeSupplier;
        this.canDropApple = canDropApple;
        this.dropMoreSaplings = dropMoreSaplings;
    }

    /**
     * Creates leaf behavior using an optional identifier-backed sapling supplier. A {@code
     * null} {@code saplingId} disables saplings; a present identifier defers registry lookup
     * until ordinary drops are calculated.
     */
    public BlockLeavesBaseComponentImpl(BlockType<? extends BlockBehavior> blockType, ItemId saplingId, boolean canDropApple, boolean dropMoreSaplings) {
        this(blockType, saplingId == null ? null : saplingId::getItemType, canDropApple, dropMoreSaplings);
    }

    /**
     * Packs a position into the support search's visited-map key. Each horizontal coordinate
     * occupies twenty-six bits and the high twelve bits hold the vertical coordinate, so the
     * caller must stay within those represented ranges to avoid collisions.
     *
     * <p>
     * Horizontal masking prevents a negative coordinate's sign extension from occupying the
     * other coordinate slots. The same packed identity is used when comparing remaining search
     * budgets, so an accidental collision could prune a distinct branch as if it had already
     * been explored.
     */
    protected static long hashBlockXYZ(int x, int y, int z) {
        // Keep each horizontal coordinate in twenty-six bits so the visited map
        // can distinguish valid-world positions while the high bits retain the vertical coordinate.
        return ((long) y << 52) + (((long) z & 0x3ffffff) << 26) + ((long) x & 0x3ffffff);
    }

    /**
     * Dispatches the inherited neighbor hook and marks this leaf for support evaluation. The
     * shared helper propagates delayed updates to adjacent unmarked leaves rather than
     * performing the recursive support search in this callback.
     */
    @Override
    public void onNeighborUpdate(Block block, Block neighbor, BlockFace face, BlockState oldNeighborState) {
        super.onNeighborUpdate(block, neighbor, face, oldNeighborState);
        onNeighborOrScheduledUpdate(block);
    }

    /**
     * Marks this leaf and propagates delayed neighbor evaluations through the shared helper.
     * Scheduled handling does not itself decay leaves; the later random-update callback
     * performs the support search and fade proposal.
     */
    @Override
    public void onScheduledUpdate(Block block) {
        onNeighborOrScheduledUpdate(block);
    }

    /**
     * Sets the current leaf's update flag and requests two-tick updates for adjacent unmarked
     * leaves. The flag write suppresses neighbor-update propagation, so the explicit delayed
     * requests control how the canopy is marked for later evaluation.
     */
    protected void onNeighborOrScheduledUpdate(Block current) {
        var pos = current.getPosition();
        if (!current.getPropertyValue(UPDATE_BIT)) {
            current = current.setPropertyValue(UPDATE_BIT, true);
            pos.dimension().setBlockState(pos, current.getBlockState(), 0, true, false, false);
        }

        // Spread evaluation requests through delayed neighbor updates instead of recursively
        // checking an entire connected canopy during one neighbor or scheduled callback.
        for (var face : BlockFace.VALUES) {
            var sideBlockState = current.offsetPos(face);
            if (sideBlockState.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
                if (!sideBlockState.getPropertyValue(UPDATE_BIT)) {
                    pos.dimension().getBlockUpdateManager().scheduleBlockUpdateInDelay(sideBlockState.getPosition(), 2);
                }
            }
        }
    }

    /**
     * Evaluates marked leaves after inherited random-update dispatch. Persistence or a
     * supporter found within seven search steps clears the flag; otherwise an accepted fade
     * event breaks the block through normal breaking rather than writing the event's state.
     */
    @Override
    public void onRandomUpdate(Block block) {
        super.onRandomUpdate(block);
        if (!block.getPropertyValue(UPDATE_BIT)) {
            return;
        }

        var pos = block.getPosition();
        // Bound natural support reach to seven steps while persistent leaves bypass
        // the search and remain available even without a nearby supporting block.
        if (block.getPropertyValue(BlockPropertyTypes.PERSISTENT_BIT) || findLog(block, 7, null)) {
            block = block.setPropertyValue(UPDATE_BIT, false);
            pos.dimension().setBlockState(pos, block.getBlockState(), 0, true, false, false);
        } else {
            if (new BlockFadeEvent(block, BlockTypes.AIR.getDefaultState()).call()) {
                block.breakBlock();
            }
        }
    }

    /**
     * Returns whether a supporter is reachable through leaves within the supplied distance
     * budget. A {@code null} {@code visited} allocates a map with an unseen sentinel of {@code
     * -1}; a supplied map is mutated and shared by recursive calls.
     *
     * <p>
     * Supporter recognition precedes the distance cutoff, allowing a supporter exactly at the
     * boundary. Each position stores its greatest remaining budget, so a revisit with no
     * greater reach is pruned while a shorter path can still explore farther. Nonleaf
     * intermediate blocks and exhausted nonsupporter positions terminate that branch.
     */
    protected boolean findLog(Block current, int distance, Long2LongMap visited) {
        if (visited == null) {
            visited = new Long2LongOpenHashMap();
            visited.defaultReturnValue(-1);
        }

        if (current.getBlockType().hasBlockTag(BlockTags.LEAVES_SUPPORTER)) {
            return true;
        }
        if (distance == 0 || !current.getBlockType().hasBlockTag(BlockTags.LEAVES)) {
            return false;
        }

        var pos = current.getPosition();
        long hash = hashBlockXYZ(pos.x(), pos.y(), pos.z());
        // A previous visit with at least this remaining reach already explored
        // every continuation this branch could reach; only a stronger budget warrants revisiting.
        if (visited.get(hash) >= distance) {
            return false;
        }

        visited.put(hash, distance);
        for (BlockFace face : LOG_FINDING_ORDER) {
            var nextBlockStateWithPos = current.offsetPos(face);
            if (findLog(nextBlockStateWithPos, distance - 1, visited)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns whether leaves receive random updates for deferred support evaluation.
     */
    @Override
    public boolean canRandomUpdate() {
        return true;
    }

    /**
     * Returns the inherited leaf-block drop for shears or samples ordinary leaf rewards.
     * Apples require configuration permission, sticks use their own Fortune-adjusted divisor,
     * and saplings require a nonnull supplier result. Each reward roll is independent, so
     * several item types can be returned together.
     */
    @Override
    public Set<ItemStack> getDrops(Block block, ItemStack usedItem, Entity entity) {
        if (usedItem != null && usedItem.getItemType() == ItemTypes.SHEARS) {
            return super.getDrops(block, usedItem, entity);
        }

        Set<ItemStack> drops = new HashSet<>();
        if (canDropApple && FortuneDropHelper.bonusChanceDivisor(usedItem, 200, 20)) {
            drops.add(ItemTypes.APPLE.createItemStack());
        }

        if (FortuneDropHelper.bonusChanceDivisor(usedItem, 50, 5)) {
            drops.add(ItemTypes.STICK.createItemStack(ThreadLocalRandom.current().nextInt(1, 3)));
        }

        var saplingItemType = saplingType == null ? null : saplingType.get();
        if (saplingItemType != null && FortuneDropHelper.bonusChanceDivisor(usedItem, dropMoreSaplings ? 40 : 20, 4)) {
            drops.add(saplingItemType.createItemStack());
        }

        return drops;
    }
}
