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
 * @author daoge_cmd
 */
@Getter
public class BlockEntityBeaconBaseComponentImpl extends BlockEntityBaseComponentImpl implements BlockEntityBeaconBaseComponent {

    protected static final String TAG_LEVELS = "Levels";
    protected static final String TAG_PRIMARY = "Primary";
    protected static final String TAG_SECONDARY = "Secondary";

    protected int level;
    @Setter
    protected EffectType primaryEffect;
    @Setter
    protected EffectType secondaryEffect;

    public BlockEntityBeaconBaseComponentImpl(BlockEntityInitInfo initInfo) {
        super(initInfo);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putInt(TAG_LEVELS, level)
                .putInt(TAG_PRIMARY, primaryEffect != null ? primaryEffect.getId() : 0)
                .putInt(TAG_SECONDARY, secondaryEffect != null ? secondaryEffect.getId() : 0)
                .build();
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForInt(TAG_LEVELS, value -> level = value);
        nbt.listenForInt(TAG_PRIMARY, value -> primaryEffect = Registries.EFFECTS.getByK1(value));
        nbt.listenForInt(TAG_SECONDARY, value -> secondaryEffect = Registries.EFFECTS.getByK1(value));
    }

    @Override
    public void tick(long currentTick) {
        super.tick(currentTick);
        if (currentTick % 80 != 0) {
            // Recalculating pyramid level and powering up players in range once every 4 seconds
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
     * Determine the players in range which could receive the beacon's powers, and determines the powers (effects)
     * that these players could get. Afterward, the players in range that are beaconAffected get their according effect(s).
     */
    protected void broadcastBeaconEffects() {
        var ticks = (9 + level * 2) * 20;
        if (level == 4) {
            ticks -= 20;
        }

        // Establishing what effects are active with the current amount of beacon levels
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
                // Accept all effects for primary, but leave secondary as null
            default:
                validSecondaryEffect = secondaryEffect;
        }

        EffectInstance primaryEffectInstance = null;
        EffectInstance secondaryEffectInstance = null;
        if (validPrimaryEffect != null) {
            primaryEffectInstance = validPrimaryEffect.createInstance(0, ticks);
            // Secondary power can only be set if the primary power is set
            if (validSecondaryEffect != null) {
                // It is possible to select 2 primary powers if the beacon's level is 4. This then means that the effect
                // should get a level of 2
                if (validPrimaryEffect == validSecondaryEffect) {
                    primaryEffectInstance = validPrimaryEffect.createInstance(1, ticks);
                } else {
                    secondaryEffectInstance = validSecondaryEffect.createInstance(0, ticks);
                }
            }
        }

        // Finding entities in range
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
     * Calculate the level of the beacon's pyramid and returns it. The level can be 0-4.
     *
     * @return the level of the beacon's pyramid
     */
    protected int calculateLevel() {
        var lvl = 0;
        var iter = 1;
        // This loop goes over all 4 possible pyramid levels
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
     * Determine whether the beacon is currently obstructed.
     *
     * @return {@code true} if the beacon is obstructed, {@code false} otherwise.
     */
    protected boolean isObstructed() {
        return position.dimension().getLightEngine().getSkyLight(position.x(), position.y() + 1, position.z()) != 15;
    }
}
