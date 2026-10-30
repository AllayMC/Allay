package org.allaymc.server.blockentity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.data.BlockTags;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBeaconBaseComponent;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.api.player.Player;
import org.allaymc.api.registry.Registries;
import org.cloudburstmc.nbt.NbtMap;

/**
 * {@code BlockEntityBeaconBaseComponentImpl} stores beacon selections and
 * applies effects according to the observed pyramid and overhead light. Every
 * eighty game ticks, {@code tick} recalculates complete mineral layers, sends a
 * level change to viewers, and applies powers only when the level is nonzero
 * and the block above receives maximum sky light. Stored NBT levels remain
 * visible until that recalculation replaces them.
 *
 * <p>
 * {@code broadcastBeaconEffects} filters specific primary effects by level,
 * combines equal primary and secondary selections into an increased amplifier,
 * and selects players within an axis-aligned X/Z square regardless of height.
 * The current switch assigns the selected secondary effect for every level, so
 * it does not independently restrict secondary selections to a four-layer
 * pyramid. NBT uses effect IDs, with zero representing an absent selection
 * during saving.
 *
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBeaconBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBeaconBaseComponent {

    /**
     * {@code TAG_LEVELS} names the stored pyramid level. The beacon tag
     * constants select the level and effect-ID entries used during NBT loading
     * and saving.
     */
    protected static final String TAG_LEVELS = "Levels";
    /**
     * Persisted primary effect ID.
     */
    protected static final String TAG_PRIMARY = "Primary";
    /**
     * Persisted secondary effect ID.
     */
    protected static final String TAG_SECONDARY = "Secondary";

    /**
     * {@code level} holds the current pyramid-level value. NBT can assign it
     * directly; the next scheduled recalculation replaces it with an observed
     * complete-layer count.
     */
    protected int level;
    /**
     * {@code primaryEffect} stores the primary selection, with {@code null}
     * meaning none. The generated setter only replaces the reference for later
     * effect processing.
     */
    @Setter
    protected EffectType primaryEffect;
    /**
     * {@code secondaryEffect} stores the secondary selection, with {@code null}
     * meaning none. The generated setter only replaces the reference for later
     * processing.
     */
    @Setter
    protected EffectType secondaryEffect;

    /**
     * Creates a beacon component with common identity and empty effect
     * selections.
     */
    public BlockEntityBeaconBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    /**
     * Builds common NBT with the current level and selected effect IDs. An
     * absent primary or secondary selection is encoded as zero; the method does
     * not recalculate the pyramid before serialization.
     */
    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_LEVELS, level)
                .putInt(TAG_PRIMARY, primaryEffect != null ? primaryEffect.getId() : 0)
                .putInt(TAG_SECONDARY, secondaryEffect != null ? secondaryEffect.getId() : 0)
                .build();
    }

    /**
     * Loads common state and supplied beacon level or effect-ID entries.
     * Missing tags retain existing values, while unknown effect IDs resolve
     * through the registry and can leave the corresponding selection
     * {@code null}.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_LEVELS, value -> level = value);
        nbt.listenForInt(TAG_PRIMARY, value -> primaryEffect = Registries.EFFECTS.getByK1(value));
        nbt.listenForInt(TAG_SECONDARY, value -> secondaryEffect = Registries.EFFECTS.getByK1(value));
    }

    /**
     * Recalculates the pyramid and applies eligible beacon effects every eighty
     * ticks. Other ticks leave beacon state unchanged. A changed level is sent
     * to viewers before effect processing, and a zero level or obstructed sky
     * light suppresses effect application. The current tick supplies the
     * cadence rather than an elapsed timer, so callers must invoke this hook
     * with the dimension's game-tick counter for the expected update schedule.
     */
    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        // Throttle pyramid scans and player refreshes to eighty-tick intervals.
        if (currentTick % 80 != 0) {
            return;
        }

        var before = level;
        this.level = calculateLevel();
        if (this.level != before) {
            sendBlockEntityToViewers();
        }
        if (this.level != 0 && !isObstructed()) {
            broadcastBeaconEffects();
        }
    }

    /**
     * Applies the selected beacon powers to players within an axis-aligned X/Z
     * square. The current {@code level} determines duration and range and
     * filters resistance, jump boost, and strength from lower-tier primary
     * selections. A secondary selection is considered only when the primary
     * selection survives filtering. Equal selections increase the primary
     * amplifier; different selections create two effect instances. The player
     * selection checks each horizontal axis independently and ignores height,
     * and this method does not recheck the pyramid or overhead sky light.
     */
    protected void broadcastBeaconEffects() {
        // Convert the level-dependent duration from seconds into game ticks.
        var ticks = (9 + level * 2) * 20;
        // Four-layer beacons use one second less than the general duration.
        if (level == 4) {
            ticks -= 20;
        }

        // Fall through the level cases so lower tiers accumulate the primary
        // exclusions; the default assignment still retains secondary selections
        // at every level.
        EffectType validPrimaryEffect = primaryEffect;
        EffectType validSecondaryEffect;
        switch (level) {
            case 0:
                validPrimaryEffect = null;
            case 1:
                if (validPrimaryEffect == EffectTypes.RESISTANCE ||
                    validPrimaryEffect == EffectTypes.JUMP_BOOST ||
                    validPrimaryEffect == EffectTypes.STRENGTH) {
                    validPrimaryEffect = null;
                }
            case 2:
                if (validPrimaryEffect == EffectTypes.STRENGTH) {
                    validPrimaryEffect = null;
                }
            case 3:
            default:
                validSecondaryEffect = secondaryEffect;
        }

        EffectInstance primaryEffectInstance = null;
        EffectInstance secondaryEffectInstance = null;
        if (validPrimaryEffect != null) {
            primaryEffectInstance = validPrimaryEffect.createInstance(0, ticks);
            // The secondary selection has no independent application path, so
            // an unusable primary selection suppresses both powers for this
            // refresh.
            if (validSecondaryEffect != null) {
                // Matching selections strengthen one primary instance instead
                // of creating a second instance of that same effect for each
                // recipient.
                if (validPrimaryEffect == validSecondaryEffect) {
                    primaryEffectInstance = validPrimaryEffect.createInstance(1, ticks);
                } else {
                    secondaryEffectInstance = validSecondaryEffect.createInstance(0, ticks);
                }
            }
        }

        // Increase horizontal eligibility by ten blocks per complete pyramid
        // layer.
        var r = 10 + (level * 10);
        var playersInRange = position.dimension()
                .getPlayers()
                .stream()
                .map(Player::getControlledEntity)
                .filter(entity -> {
                    var ex = entity.getLocation().x();
                    var ez = entity.getLocation().z();
                    return Math.abs(ex - position.x()) <= r && Math.abs(ez - position.z()) <= r;
                })
                .toList();

        for (var player : playersInRange) {
            if (primaryEffectInstance != null) {
                player.addEffect(primaryEffectInstance);
            }
            if (secondaryEffectInstance != null) {
                player.addEffect(secondaryEffectInstance);
            }
        }
    }

    /**
     * Returns the number of consecutive complete mineral layers below this
     * beacon. The scan checks up to four layers, beginning immediately below
     * the beacon and increasing the square radius for each lower layer. The
     * first non-base block ends the scan and returns the count of already
     * completed layers, so a valid lower layer cannot compensate for an
     * incomplete layer above it. This method reads world blocks without
     * modifying the stored {@code level}.
     */
    protected int calculateLevel() {
        var lvl = 0;
        var iter = 1;
        // Stop at four complete layers, the maximum supported beacon pyramid.
        for (var y = position.y() - 1; y >= position.y() - 4; y--) {
            for (var x = position.x() - iter; x <= position.x() + iter; x++) {
                for (var z = position.z() - iter; z <= position.z() + iter; z++) {
                    if (!position.dimension().getBlockState(x, y, z).getBlockType().hasBlockTag(BlockTags.BEACON_BASE)) {
                        return lvl;
                    }
                }
            }
            iter++;
            lvl++;
        }
        return lvl;
    }

    /**
     * Returns whether the block above this beacon lacks maximum sky light.
     */
    protected boolean isObstructed() {
        // Maximum sky light above the beacon permits power application.
        return position.dimension().getLightEngine().getSkyLight(position.x(), position.y() + 1, position.z()) != 15;
    }
}
