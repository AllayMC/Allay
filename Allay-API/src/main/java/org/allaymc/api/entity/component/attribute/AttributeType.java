package org.allaymc.api.entity.component.attribute;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/7/9
 *
 * @author JukeboxMC | daoge_cmd
 */
@Getter
@AllArgsConstructor
public enum AttributeType {

    // Base Entity
    ABSORPTION("minecraft:absorption", 0, Float.MAX_VALUE, 0),
    KNOCKBACK_RESISTANCE("minecraft:knockback_resistance", 0, 1, 0),
    HEALTH("minecraft:health", 0, 20, 20),
    MOVEMENT_SPEED("minecraft:movement", 0.1f, 1, 0.1f),
    UNDER_WATER_MOVEMENT_SPEED("minecraft:underwater_movement", 0, Float.MAX_VALUE, 0.02f),
    LAVA_MOVEMENT_SPEED("minecraft:lava_movement", 0, Float.MAX_VALUE, 0.02f),
    FOLLOW_RANGE("minecraft:follow_range", 0, 2048, 16),
    ATTACK_DAMAGE("minecraft:attack_damage", 1, Float.MAX_VALUE, 1),
    LUCK("minecraft:luck", -1024, 1024, 0),

    // Horse
    HORSE_JUMP_STRENGTH("minecraft:horse.jump_strength", 0, 20, 0.7f),

    // Zombie
    ZOMBIE_SPAWN_REINFORCEMENTS("minecraft:zombie.spawn_reinforcements", 0, 1, 0),

    // Player
    PLAYER_HUNGER("minecraft:player.hunger", 0, 20, 20),
    PLAYER_SATURATION("minecraft:player.saturation", 0, 20, 20),
    PLAYER_EXHAUSTION("minecraft:player.exhaustion", 0, 5, 0.41f),
    PLAYER_EXPERIENCE_LEVEL("minecraft:player.level", 0, 24791, 0),
    PLAYER_EXPERIENCE_PROGRESS("minecraft:player.experience", 0, 1, 0);

    private static final Map<String, AttributeType> KEY_LOOK_UP = new HashMap<>();

    static {
        for (var attributeType : values()) {
            KEY_LOOK_UP.put(attributeType.key, attributeType);
        }
    }

    private final String key;
    private final float minValue;
    private final float maxValue;
    private final float defaultValue;

    public static AttributeType byKey(String key) {
        return KEY_LOOK_UP.get(key);
    }

    public Attribute newAttributeInstance() {
        return new Attribute(this.key, this.minValue, this.maxValue, this.defaultValue, this.defaultValue);
    }
}
