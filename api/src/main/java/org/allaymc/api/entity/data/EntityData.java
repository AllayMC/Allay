package org.allaymc.api.entity.data;

import lombok.Getter;
import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * EntityData contains all the available data types in {@link Entity}.
 *
 * @author daoge_cmd
 */
@SuppressWarnings("ALL")
@MinecraftVersionSensitive
public final class EntityData<T> {
    public static final EntityData<Integer> STRUCTURAL_INTEGRITY = new EntityData<>(Integer.class);
    public static final EntityData<Integer> VARIANT = new EntityData<>(Integer.class);
    public static final EntityData<BlockState> BLOCK = new EntityData<>(BlockState.class);
    public static final EntityData<Byte> COLOR = new EntityData<>(Byte.class);
    public static final EntityData<String> NAME = new EntityData<>(String.class);
    public static final EntityData<Long> OWNER_EID = new EntityData<>(Long.class);
    public static final EntityData<Long> TARGET_EID = new EntityData<>(Long.class);
    public static final EntityData<Short> AIR_SUPPLY = new EntityData<>(Short.class);
    public static final EntityData<Integer> EFFECT_COLOR = new EntityData<>(Integer.class);
    public static final EntityData<Byte> JUMP_DURATION = new EntityData<>(Byte.class);
    public static final EntityData<Integer> HURT_TICKS = new EntityData<>(Integer.class);
    public static final EntityData<Integer> HURT_DIRECTION = new EntityData<>(Integer.class);
    public static final EntityData<Float> ROW_TIME_LEFT = new EntityData<>(Float.class);
    public static final EntityData<Float> ROW_TIME_RIGHT = new EntityData<>(Float.class);
    public static final EntityData<Integer> VALUE = new EntityData<>(Integer.class);
    public static final EntityData<Byte> WITHER_SKULL_DANGEROUS = new EntityData<>(Byte.class);
    public static final EntityData<Integer> HORSE_FLAGS = new EntityData<>(Integer.class);
    public static final EntityData<NbtMap> DISPLAY_FIREWORK = new EntityData<>(NbtMap.class);
    public static final EntityData<BlockState> DISPLAY_BLOCK_STATE = new EntityData<>(BlockState.class);
    public static final EntityData<Integer> DISPLAY_OFFSET = new EntityData<>(Integer.class);
    public static final EntityData<Byte> CUSTOM_DISPLAY = new EntityData<>(Byte.class);
    public static final EntityData<Byte> HORSE_TYPE = new EntityData<>(Byte.class);
    public static final EntityData<Integer> OLD_SWELL = new EntityData<>(Integer.class);
    public static final EntityData<Integer> SWELL_DIRECTION = new EntityData<>(Integer.class);
    public static final EntityData<Byte> CHARGE_AMOUNT = new EntityData<>(Byte.class);
    public static final EntityData<Byte> CLIENT_EVENT = new EntityData<>(Byte.class);
    public static final EntityData<Boolean> USING_ITEM = new EntityData<>(Boolean.class);
    public static final EntityData<Byte> PLAYER_FLAGS = new EntityData<>(Byte.class);
    public static final EntityData<Integer> PLAYER_INDEX = new EntityData<>(Integer.class);
    public static final EntityData<Vector3ic> BED_POSITION = new EntityData<>(Vector3ic.class);
    public static final EntityData<Float> FIREBALL_POWER_X = new EntityData<>(Float.class);
    public static final EntityData<Float> FIREBALL_POWER_Y = new EntityData<>(Float.class);
    public static final EntityData<Float> FIREBALL_POWER_Z = new EntityData<>(Float.class);
    public static final EntityData<Byte> AUX_POWER = new EntityData<>(Byte.class);
    public static final EntityData<Float> FISH_X = new EntityData<>(Float.class);
    public static final EntityData<Float> FISH_Z = new EntityData<>(Float.class);
    public static final EntityData<Float> FISH_ANGLE = new EntityData<>(Float.class);
    public static final EntityData<Short> AUX_VALUE_DATA = new EntityData<>(Short.class);
    public static final EntityData<Long> LEASH_HOLDER = new EntityData<>(Long.class);
    public static final EntityData<Float> SCALE = new EntityData<>(Float.class);
    public static final EntityData<Boolean> HAS_NPC = new EntityData<>(Boolean.class);
    public static final EntityData<String> NPC_DATA = new EntityData<>(String.class);
    public static final EntityData<String> ACTIONS = new EntityData<>(String.class);
    public static final EntityData<Short> AIR_SUPPLY_MAX = new EntityData<>(Short.class);
    public static final EntityData<Integer> MARK_VARIANT = new EntityData<>(Integer.class);
    public static final EntityData<Byte> CONTAINER_TYPE = new EntityData<>(Byte.class);
    public static final EntityData<Integer> CONTAINER_SIZE = new EntityData<>(Integer.class);
    public static final EntityData<Integer> CONTAINER_STRENGTH_MODIFIER = new EntityData<>(Integer.class);
    public static final EntityData<Vector3ic> BLOCK_TARGET_POS = new EntityData<>(Vector3ic.class);
    public static final EntityData<Integer> WITHER_INVULNERABLE_TICKS = new EntityData<>(Integer.class);
    public static final EntityData<Long> WITHER_TARGET_A = new EntityData<>(Long.class);
    public static final EntityData<Long> WITHER_TARGET_B = new EntityData<>(Long.class);
    public static final EntityData<Long> WITHER_TARGET_C = new EntityData<>(Long.class);
    public static final EntityData<Short> WITHER_AERIAL_ATTACK = new EntityData<>(Short.class);
    public static final EntityData<Float> WIDTH = new EntityData<>(Float.class);
    public static final EntityData<Float> HEIGHT = new EntityData<>(Float.class);
    public static final EntityData<Integer> FUSE_TIME = new EntityData<>(Integer.class);
    public static final EntityData<Vector3fc> SEAT_OFFSET = new EntityData<>(Vector3fc.class);
    public static final EntityData<Boolean> SEAT_LOCK_RIDER_ROTATION = new EntityData<>(Boolean.class);
    public static final EntityData<Float> SEAT_LOCK_RIDER_ROTATION_DEGREES = new EntityData<>(Float.class);
    public static final EntityData<Boolean> SEAT_HAS_ROTATION = new EntityData<>(Boolean.class);
    public static final EntityData<Float> SEAT_ROTATION_OFFSET_DEGREES = new EntityData<>(Float.class);
    public static final EntityData<Float> AREA_EFFECT_CLOUD_RADIUS = new EntityData<>(Float.class);
    public static final EntityData<Integer> AREA_EFFECT_CLOUD_WAITING = new EntityData<>(Integer.class);
    // TODO: find a proper way to implement it
//    public static final EntityDataType<Particle> AREA_EFFECT_CLOUD_PARTICLE = new EntityDataType<>(Particle.class);
    public static final EntityData<Integer> SHULKER_PEEK_AMOUNT = new EntityData<>(Integer.class);
    public static final EntityData<Integer> SHULKER_ATTACH_FACE = new EntityData<>(Integer.class);
    public static final EntityData<Boolean> SHULKER_ATTACHED = new EntityData<>(Boolean.class);
    public static final EntityData<Vector3ic> SHULKER_ATTACH_POS = new EntityData<>(Vector3ic.class);
    public static final EntityData<Long> TRADE_TARGET_EID = new EntityData<>(Long.class);
    public static final EntityData<Boolean> COMMAND_BLOCK_ENABLED = new EntityData<>(Boolean.class);
    public static final EntityData<String> COMMAND_BLOCK_NAME = new EntityData<>(String.class);
    public static final EntityData<String> COMMAND_BLOCK_LAST_OUTPUT = new EntityData<>(String.class);
    public static final EntityData<Boolean> COMMAND_BLOCK_TRACK_OUTPUT = new EntityData<>(Boolean.class);
    public static final EntityData<Byte> CONTROLLING_RIDER_SEAT_INDEX = new EntityData<>(Byte.class);
    public static final EntityData<Integer> STRENGTH = new EntityData<>(Integer.class);
    public static final EntityData<Integer> STRENGTH_MAX = new EntityData<>(Integer.class);
    public static final EntityData<Integer> EVOKER_SPELL_CASTING_COLOR = new EntityData<>(Integer.class);
    public static final EntityData<Integer> DATA_LIFETIME_TICKS = new EntityData<>(Integer.class);
    public static final EntityData<Integer> ARMOR_STAND_POSE_INDEX = new EntityData<>(Integer.class);
    public static final EntityData<Integer> END_CRYSTAL_TICK_OFFSET = new EntityData<>(Integer.class);
    public static final EntityData<Byte> NAMETAG_ALWAYS_SHOW = new EntityData<>(Byte.class);
    public static final EntityData<Byte> COLOR_2 = new EntityData<>(Byte.class);
    public static final EntityData<String> NAME_AUTHOR = new EntityData<>(String.class);
    public static final EntityData<String> SCORE = new EntityData<>(String.class);
    public static final EntityData<Long> BALLOON_ANCHOR_EID = new EntityData<>(Long.class);
    public static final EntityData<Byte> PUFFED_STATE = new EntityData<>(Byte.class);
    public static final EntityData<Integer> BOAT_BUBBLE_TIME = new EntityData<>(Integer.class);
    public static final EntityData<Long> AGENT_EID = new EntityData<>(Long.class);
    public static final EntityData<Float> SITTING_AMOUNT = new EntityData<>(Float.class);
    public static final EntityData<Float> SITTING_AMOUNT_PREVIOUS = new EntityData<>(Float.class);
    public static final EntityData<Integer> EATING_COUNTER = new EntityData<>(Integer.class);
    public static final EntityData<Float> LAYING_AMOUNT = new EntityData<>(Float.class);
    public static final EntityData<Float> LAYING_AMOUNT_PREVIOUS = new EntityData<>(Float.class);
    public static final EntityData<Integer> AREA_EFFECT_CLOUD_DURATION = new EntityData<>(Integer.class);
    public static final EntityData<Integer> AREA_EFFECT_CLOUD_SPAWN_TIME = new EntityData<>(Integer.class);
    public static final EntityData<Float> AREA_EFFECT_CLOUD_CHANGE_RATE = new EntityData<>(Float.class);
    public static final EntityData<Float> AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP = new EntityData<>(Float.class);
    public static final EntityData<Integer> AREA_EFFECT_CLOUD_PICKUP_COUNT = new EntityData<>(Integer.class);
    public static final EntityData<String> INTERACT_TEXT = new EntityData<>(String.class);
    public static final EntityData<Integer> TRADE_TIER = new EntityData<>(Integer.class);
    public static final EntityData<Integer> MAX_TRADE_TIER = new EntityData<>(Integer.class);
    public static final EntityData<Integer> TRADE_EXPERIENCE = new EntityData<>(Integer.class);
    public static final EntityData<Integer> SKIN_ID = new EntityData<>(Integer.class);
    public static final EntityData<Integer> SPAWNING_FRAMES = new EntityData<>(Integer.class);
    public static final EntityData<Integer> COMMAND_BLOCK_TICK_DELAY = new EntityData<>(Integer.class);
    public static final EntityData<Boolean> COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK = new EntityData<>(Boolean.class);
    public static final EntityData<Float> AMBIENT_SOUND_INTERVAL = new EntityData<>(Float.class);
    public static final EntityData<Float> AMBIENT_SOUND_INTERVAL_RANGE = new EntityData<>(Float.class);
    public static final EntityData<String> AMBIENT_SOUND_EVENT_NAME = new EntityData<>(String.class);
    public static final EntityData<Float> FALL_DAMAGE_MULTIPLIER = new EntityData<>(Float.class);
    public static final EntityData<String> NAME_RAW_TEXT = new EntityData<>(String.class);
    public static final EntityData<Boolean> CAN_RIDE_TARGET = new EntityData<>(Boolean.class);
    public static final EntityData<Integer> LOW_TIER_CURED_TRADE_DISCOUNT = new EntityData<>(Integer.class);
    public static final EntityData<Integer> HIGH_TIER_CURED_TRADE_DISCOUNT = new EntityData<>(Integer.class);
    public static final EntityData<Integer> NEARBY_CURED_TRADE_DISCOUNT = new EntityData<>(Integer.class);
    public static final EntityData<Integer> NEARBY_CURED_DISCOUNT_TIME_STAMP = new EntityData<>(Integer.class);
    public static final EntityData<NbtMap> HITBOX = new EntityData<>(NbtMap.class);
    public static final EntityData<Boolean> IS_BUOYANT = new EntityData<>(Boolean.class);
    public static final EntityData<String> BASE_RUNTIME_ID = new EntityData<>(String.class);
    public static final EntityData<Float> FREEZING_EFFECT_STRENGTH = new EntityData<>(Float.class);
    public static final EntityData<String> BUOYANCY_DATA = new EntityData<>(String.class);
    public static final EntityData<Integer> GOAT_HORN_COUNT = new EntityData<>(Integer.class);
    public static final EntityData<Float> MOVEMENT_SOUND_DISTANCE_OFFSET = new EntityData<>(Float.class);
    public static final EntityData<Integer> HEARTBEAT_INTERVAL_TICKS = new EntityData<>(Integer.class);
    public static final EntityData<Integer> HEARTBEAT_SOUND_EVENT = new EntityData<>(Integer.class);
    public static final EntityData<Vector3ic> PLAYER_LAST_DEATH_POS = new EntityData<>(Vector3ic.class);
    public static final EntityData<Integer> PLAYER_LAST_DEATH_DIMENSION = new EntityData<>(Integer.class);
    public static final EntityData<Boolean> PLAYER_HAS_DIED = new EntityData<>(Boolean.class);
    public static final EntityData<Vector3fc> COLLISION_BOX = new EntityData<>(Vector3fc.class);
    public static final EntityData<Long> VISIBLE_MOB_EFFECTS = new EntityData<>(Long.class);
    public static final EntityData<String> FILTERED_NAME = new EntityData<>(String.class);
    public static final EntityData<Vector3fc> BED_ENTER_POSITION = new EntityData<>(Vector3fc.class);
    public static final EntityData<Float> SEAT_THIRD_PERSON_CAMERA_RADIUS = new EntityData<>(Float.class);
    public static final EntityData<Float> SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING = new EntityData<>(Float.class);

    /**
     * The supported value type of this data entry.
     */
    @Getter
    private final Class<?> type;

    private EntityData(Class<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EntityData[" + "type=" + type + ']';
    }
}
