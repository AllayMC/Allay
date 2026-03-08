package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.entity.effect.EffectTypes;
import org.allaymc.server.entity.effect.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EffectTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        EffectTypes.ABSORPTION = new EffectAbsorptionType();
        EffectTypes.BAD_OMEN = new EffectBadOmenType();
        EffectTypes.BLINDNESS = new EffectBlindnessType();
        EffectTypes.CONDUIT_POWER = new EffectConduitPowerType();
        EffectTypes.DARKNESS = new EffectDarknessType();
        EffectTypes.FATAL_POISON = new EffectFatalPoisonType();
        EffectTypes.FIRE_RESISTANCE = new EffectFireResistanceType();
        EffectTypes.HASTE = new EffectHasteType();
        EffectTypes.HEALTH_BOOST = new EffectHealthBoostType();
        EffectTypes.HUNGER = new EffectHungerType();
        EffectTypes.INFESTED = new EffectInfestedType();
        EffectTypes.INSTANT_DAMAGE = new EffectInstantDamageType();
        EffectTypes.INSTANT_HEALTH = new EffectInstantHealthType();
        EffectTypes.INVISIBILITY = new EffectInvisibilityType();
        EffectTypes.JUMP_BOOST = new EffectJumpBoostType();
        EffectTypes.LEVITATION = new EffectLevitationType();
        EffectTypes.MINING_FATIGUE = new EffectMiningFatigueType();
        EffectTypes.NAUSEA = new EffectNauseaType();
        EffectTypes.NIGHT_VISION = new EffectNightVisionType();
        EffectTypes.OOZING = new EffectOozingType();
        EffectTypes.POISON = new EffectPoisonType();
        EffectTypes.RAID_OMEN = new EffectRaidOmenType();
        EffectTypes.REGENERATION = new EffectRegenerationType();
        EffectTypes.RESISTANCE = new EffectResistanceType();
        EffectTypes.SATURATION = new EffectSaturationType();
        EffectTypes.SLOW_FALLING = new EffectSlowFallingType();
        EffectTypes.SLOWNESS = new EffectSlownessType();
        EffectTypes.SPEED = new EffectSpeedType();
        EffectTypes.STRENGTH = new EffectStrengthType();
        EffectTypes.TRIAL_OMEN = new EffectTrialOmenType();
        EffectTypes.VILLAGE_HERO = new EffectVillageHeroType();
        EffectTypes.WATER_BREATHING = new EffectWaterBreathingType();
        EffectTypes.WEAKNESS = new EffectWeaknessType();
        EffectTypes.WEAVING = new EffectWeavingType();
        EffectTypes.WIND_CHARGED = new EffectWindChargedType();
        EffectTypes.WITHER = new EffectWitherType();
    }
}
