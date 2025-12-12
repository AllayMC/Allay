package org.allaymc.server.registry.populator;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.item.enchantment.EnchantmentTypes;
import org.allaymc.server.item.enchantment.*;

/**
 * @author daoge_cmd
 */
@Slf4j
public class EnchantmentTypeRegistryPopulator implements Runnable {
    @Override
    public void run() {
        EnchantmentTypes.AQUA_AFFINITY = new EnchantmentAquaAffinityType();
        EnchantmentTypes.BANE_OF_ARTHROPODS = new EnchantmentBaneOfArthropodsType();
        EnchantmentTypes.BINDING = new EnchantmentBindingType();
        EnchantmentTypes.BLAST_PROTECTION = new EnchantmentBlastProtectionType();
        EnchantmentTypes.BREACH = new EnchantmentBreachType();
        EnchantmentTypes.CHANNELING = new EnchantmentChannelingType();
        EnchantmentTypes.DENSITY = new EnchantmentDensityType();
        EnchantmentTypes.DEPTH_STRIDER = new EnchantmentDepthStriderType();
        EnchantmentTypes.EFFICIENCY = new EnchantmentEfficiencyType();
        EnchantmentTypes.FEATHER_FALLING = new EnchantmentFeatherFallingType();
        EnchantmentTypes.FIRE_ASPECT = new EnchantmentFireAspectType();
        EnchantmentTypes.FIRE_PROTECTION = new EnchantmentFireProtectionType();
        EnchantmentTypes.FLAME = new EnchantmentFlameType();
        EnchantmentTypes.FORTUNE = new EnchantmentFortuneType();
        EnchantmentTypes.FROST_WALKER = new EnchantmentFrostWalkerType();
        EnchantmentTypes.IMPALING = new EnchantmentImpalingType();
        EnchantmentTypes.INFINITY = new EnchantmentInfinityType();
        EnchantmentTypes.KNOCKBACK = new EnchantmentKnockbackType();
        EnchantmentTypes.LOOTING = new EnchantmentLootingType();
        EnchantmentTypes.LOYALTY = new EnchantmentLoyaltyType();
        EnchantmentTypes.LUCK_OF_THE_SEA = new EnchantmentLuckOfTheSeaType();
        EnchantmentTypes.LURE = new EnchantmentLureType();
        EnchantmentTypes.MENDING = new EnchantmentMendingType();
        EnchantmentTypes.MULTISHOT = new EnchantmentMultishotType();
        EnchantmentTypes.PIERCING = new EnchantmentPiercingType();
        EnchantmentTypes.POWER = new EnchantmentPowerType();
        EnchantmentTypes.PROJECTILE_PROTECTION = new EnchantmentProjectileProtectionType();
        EnchantmentTypes.PROTECTION = new EnchantmentProtectionType();
        EnchantmentTypes.PUNCH = new EnchantmentPunchType();
        EnchantmentTypes.QUICK_CHARGE = new EnchantmentQuickChargeType();
        EnchantmentTypes.RESPIRATION = new EnchantmentRespirationType();
        EnchantmentTypes.RIPTIDE = new EnchantmentRiptideType();
        EnchantmentTypes.SHARPNESS = new EnchantmentSharpnessType();
        EnchantmentTypes.SILK_TOUCH = new EnchantmentSilkTouchType();
        EnchantmentTypes.SMITE = new EnchantmentSmiteType();
        EnchantmentTypes.SOUL_SPEED = new EnchantmentSoulSpeedType();
        EnchantmentTypes.SWIFT_SNEAK = new EnchantmentSwiftSneakType();
        EnchantmentTypes.THORNS = new EnchantmentThornsType();
        EnchantmentTypes.UNBREAKING = new EnchantmentUnbreakingType();
        EnchantmentTypes.VANISHING = new EnchantmentVanishingType();
        EnchantmentTypes.WIND_BURST = new EnchantmentWindBurstType();
        EnchantmentTypes.LUNGE = new EnchantmentLungeType();
    }
}
