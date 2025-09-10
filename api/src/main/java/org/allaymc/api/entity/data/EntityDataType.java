package org.allaymc.api.entity.data;

import lombok.Getter;
import org.allaymc.api.annotation.MinecraftVersionSensitive;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.entity.Entity;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.ParticleType;
import org.joml.Vector3fc;
import org.joml.Vector3ic;

/**
 * EntityDataType contains all the available data types in {@link Entity}.
 *
 * @author daoge_cmd
 */
@MinecraftVersionSensitive
public final class EntityDataType<T> {
    public static final EntityDataType<Integer> STRUCTURAL_INTEGRITY = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> VARIANT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<BlockState> BLOCK = new EntityDataType<>(BlockState.class);
    public static final EntityDataType<Byte> COLOR = new EntityDataType<>(Byte.class);
    public static final EntityDataType<String> NAME = new EntityDataType<>(String.class);
    public static final EntityDataType<Long> OWNER_EID = new EntityDataType<>(Long.class);
    public static final EntityDataType<Long> TARGET_EID = new EntityDataType<>(Long.class);
    public static final EntityDataType<Short> AIR_SUPPLY = new EntityDataType<>(Short.class);
    public static final EntityDataType<Integer> EFFECT_COLOR = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> JUMP_DURATION = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> HURT_TICKS = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> HURT_DIRECTION = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Float> ROW_TIME_LEFT = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> ROW_TIME_RIGHT = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> VALUE = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> WITHER_SKULL_DANGEROUS = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> HORSE_FLAGS = new EntityDataType<>(Integer.class);
    public static final EntityDataType<NbtMap> DISPLAY_FIREWORK = new EntityDataType<>(NbtMap.class);
    public static final EntityDataType<BlockState> DISPLAY_BLOCK_STATE = new EntityDataType<>(BlockState.class);
    public static final EntityDataType<Integer> DISPLAY_OFFSET = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> CUSTOM_DISPLAY = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Byte> HORSE_TYPE = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> OLD_SWELL = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> SWELL_DIRECTION = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> CHARGE_AMOUNT = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Byte> CLIENT_EVENT = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Boolean> USING_ITEM = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Byte> PLAYER_FLAGS = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> PLAYER_INDEX = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Vector3ic> BED_POSITION = new EntityDataType<>(Vector3ic.class);
    public static final EntityDataType<Float> FIREBALL_POWER_X = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> FIREBALL_POWER_Y = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> FIREBALL_POWER_Z = new EntityDataType<>(Float.class);
    public static final EntityDataType<Byte> AUX_POWER = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Float> FISH_X = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> FISH_Z = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> FISH_ANGLE = new EntityDataType<>(Float.class);
    public static final EntityDataType<Short> AUX_VALUE_DATA = new EntityDataType<>(Short.class);
    public static final EntityDataType<Long> LEASH_HOLDER = new EntityDataType<>(Long.class);
    public static final EntityDataType<Float> SCALE = new EntityDataType<>(Float.class);
    public static final EntityDataType<Boolean> HAS_NPC = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<String> NPC_DATA = new EntityDataType<>(String.class);
    public static final EntityDataType<String> ACTIONS = new EntityDataType<>(String.class);
    public static final EntityDataType<Short> AIR_SUPPLY_MAX = new EntityDataType<>(Short.class);
    public static final EntityDataType<Integer> MARK_VARIANT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> CONTAINER_TYPE = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> CONTAINER_SIZE = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> CONTAINER_STRENGTH_MODIFIER = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Vector3ic> BLOCK_TARGET_POS = new EntityDataType<>(Vector3ic.class);
    public static final EntityDataType<Integer> WITHER_INVULNERABLE_TICKS = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Long> WITHER_TARGET_A = new EntityDataType<>(Long.class);
    public static final EntityDataType<Long> WITHER_TARGET_B = new EntityDataType<>(Long.class);
    public static final EntityDataType<Long> WITHER_TARGET_C = new EntityDataType<>(Long.class);
    public static final EntityDataType<Short> WITHER_AERIAL_ATTACK = new EntityDataType<>(Short.class);
    public static final EntityDataType<Float> WIDTH = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> HEIGHT = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> FUSE_TIME = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Vector3fc> SEAT_OFFSET = new EntityDataType<>(Vector3fc.class);
    public static final EntityDataType<Boolean> SEAT_LOCK_RIDER_ROTATION = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Float> SEAT_LOCK_RIDER_ROTATION_DEGREES = new EntityDataType<>(Float.class);
    public static final EntityDataType<Boolean> SEAT_HAS_ROTATION = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Float> SEAT_ROTATION_OFFSET_DEGREES = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> AREA_EFFECT_CLOUD_RADIUS = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> AREA_EFFECT_CLOUD_WAITING = new EntityDataType<>(Integer.class);
    // TODO: replace it
    public static final EntityDataType<ParticleType> AREA_EFFECT_CLOUD_PARTICLE = new EntityDataType<>(ParticleType.class);
    public static final EntityDataType<Integer> SHULKER_PEEK_AMOUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> SHULKER_ATTACH_FACE = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Boolean> SHULKER_ATTACHED = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Vector3ic> SHULKER_ATTACH_POS = new EntityDataType<>(Vector3ic.class);
    public static final EntityDataType<Long> TRADE_TARGET_EID = new EntityDataType<>(Long.class);
    public static final EntityDataType<Boolean> COMMAND_BLOCK_ENABLED = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<String> COMMAND_BLOCK_NAME = new EntityDataType<>(String.class);
    public static final EntityDataType<String> COMMAND_BLOCK_LAST_OUTPUT = new EntityDataType<>(String.class);
    public static final EntityDataType<Boolean> COMMAND_BLOCK_TRACK_OUTPUT = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Byte> CONTROLLING_RIDER_SEAT_INDEX = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> STRENGTH = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> STRENGTH_MAX = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> EVOKER_SPELL_CASTING_COLOR = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> DATA_LIFETIME_TICKS = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> ARMOR_STAND_POSE_INDEX = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> END_CRYSTAL_TICK_OFFSET = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Byte> NAMETAG_ALWAYS_SHOW = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Byte> COLOR_2 = new EntityDataType<>(Byte.class);
    public static final EntityDataType<String> NAME_AUTHOR = new EntityDataType<>(String.class);
    public static final EntityDataType<String> SCORE = new EntityDataType<>(String.class);
    public static final EntityDataType<Long> BALLOON_ANCHOR_EID = new EntityDataType<>(Long.class);
    public static final EntityDataType<Byte> PUFFED_STATE = new EntityDataType<>(Byte.class);
    public static final EntityDataType<Integer> BOAT_BUBBLE_TIME = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Long> AGENT_EID = new EntityDataType<>(Long.class);
    public static final EntityDataType<Float> SITTING_AMOUNT = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> SITTING_AMOUNT_PREVIOUS = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> EATING_COUNTER = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Float> LAYING_AMOUNT = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> LAYING_AMOUNT_PREVIOUS = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> AREA_EFFECT_CLOUD_DURATION = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> AREA_EFFECT_CLOUD_SPAWN_TIME = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Float> AREA_EFFECT_CLOUD_CHANGE_RATE = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> AREA_EFFECT_CLOUD_CHANGE_ON_PICKUP = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> AREA_EFFECT_CLOUD_PICKUP_COUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<String> INTERACT_TEXT = new EntityDataType<>(String.class);
    public static final EntityDataType<Integer> TRADE_TIER = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> MAX_TRADE_TIER = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> TRADE_EXPERIENCE = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> SKIN_ID = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> SPAWNING_FRAMES = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> COMMAND_BLOCK_TICK_DELAY = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Boolean> COMMAND_BLOCK_EXECUTE_ON_FIRST_TICK = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Float> AMBIENT_SOUND_INTERVAL = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> AMBIENT_SOUND_INTERVAL_RANGE = new EntityDataType<>(Float.class);
    public static final EntityDataType<String> AMBIENT_SOUND_EVENT_NAME = new EntityDataType<>(String.class);
    public static final EntityDataType<Float> FALL_DAMAGE_MULTIPLIER = new EntityDataType<>(Float.class);
    public static final EntityDataType<String> NAME_RAW_TEXT = new EntityDataType<>(String.class);
    public static final EntityDataType<Boolean> CAN_RIDE_TARGET = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Integer> LOW_TIER_CURED_TRADE_DISCOUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> HIGH_TIER_CURED_TRADE_DISCOUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> NEARBY_CURED_TRADE_DISCOUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> NEARBY_CURED_DISCOUNT_TIME_STAMP = new EntityDataType<>(Integer.class);
    public static final EntityDataType<NbtMap> HITBOX = new EntityDataType<>(NbtMap.class);
    public static final EntityDataType<Boolean> IS_BUOYANT = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<String> BASE_RUNTIME_ID = new EntityDataType<>(String.class);
    public static final EntityDataType<Float> FREEZING_EFFECT_STRENGTH = new EntityDataType<>(Float.class);
    public static final EntityDataType<String> BUOYANCY_DATA = new EntityDataType<>(String.class);
    public static final EntityDataType<Integer> GOAT_HORN_COUNT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Float> MOVEMENT_SOUND_DISTANCE_OFFSET = new EntityDataType<>(Float.class);
    public static final EntityDataType<Integer> HEARTBEAT_INTERVAL_TICKS = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Integer> HEARTBEAT_SOUND_EVENT = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Vector3ic> PLAYER_LAST_DEATH_POS = new EntityDataType<>(Vector3ic.class);
    public static final EntityDataType<Integer> PLAYER_LAST_DEATH_DIMENSION = new EntityDataType<>(Integer.class);
    public static final EntityDataType<Boolean> PLAYER_HAS_DIED = new EntityDataType<>(Boolean.class);
    public static final EntityDataType<Vector3fc> COLLISION_BOX = new EntityDataType<>(Vector3fc.class);
    public static final EntityDataType<Long> VISIBLE_MOB_EFFECTS = new EntityDataType<>(Long.class);
    public static final EntityDataType<String> FILTERED_NAME = new EntityDataType<>(String.class);
    public static final EntityDataType<Vector3fc> BED_ENTER_POSITION = new EntityDataType<>(Vector3fc.class);
    public static final EntityDataType<Float> SEAT_THIRD_PERSON_CAMERA_RADIUS = new EntityDataType<>(Float.class);
    public static final EntityDataType<Float> SEAT_CAMERA_RELAX_DISTANCE_SMOOTHING = new EntityDataType<>(Float.class);

    @Getter
    private final Class<?> type;

    public EntityDataType(Class<T> type) {
        this.type = type;
    }
}
