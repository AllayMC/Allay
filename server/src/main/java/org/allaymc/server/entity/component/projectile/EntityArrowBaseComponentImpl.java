package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityPotionComponent;
import org.allaymc.server.component.annotation.Dependency;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * {@code EntityArrowBaseComponentImpl} represents arrow impact parameters,
 * pickup flags, and embedded state. The constructor supplies the default base
 * damage, while generated accessors expose enchantment levels and most flags
 * directly. Changing the critical flag additionally broadcasts state so viewer
 * metadata follows that change.
 *
 * <p>
 * Persistence stores enchantment levels as signed bytes and expresses pickup
 * eligibility through the inverse {@code TAG_SHOT_BY_PLAYER} flag. The runtime
 * fields use wider integers and a pickup-disabled boolean, so load and save
 * must preserve that polarity while narrowing values to the entity format.
 * Critical state and base damage are not added to the arrow-specific saved
 * tags in this implementation.
 *
 * @author harryxi | daoge_cmd
 */
public class EntityArrowBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityArrowBaseComponent {

    /**
     * {@code DEFAULT_BASE_DAMAGE} initializes the mutable arrow damage before
     * player speed, difficulty, critical, and Power adjustments.
     */
    protected static final int DEFAULT_BASE_DAMAGE = 1;

    /**
     * {@code TAG_POWER_LEVEL} names the signed-byte Power level restored and
     * written by arrow entity persistence.
     */
    protected static final String TAG_POWER_LEVEL = "enchantPower";
    /**
     * {@code TAG_PUNCH_LEVEL} names the signed-byte Punch level restored and
     * written by arrow entity persistence.
     */
    protected static final String TAG_PUNCH_LEVEL = "enchantPunch";
    /**
     * {@code TAG_INFINITY_LEVEL} names the byte-valued infinite flag, decoded
     * as enabled whenever its saved value is nonzero.
     */
    protected static final String TAG_INFINITY_LEVEL = "enchantInfinity";
    /**
     * {@code TAG_PIERCING_LEVEL} names the signed-byte Piercing level restored
     * and written by arrow entity persistence.
     */
    protected static final String TAG_PIERCING_LEVEL = "enchantPiercing";
    /**
     * {@code TAG_SHOT_BY_PLAYER} stores the inverse of {@code pickUpDisabled},
     * preserving the entity format polarity during loading and saving.
     */
    protected static final String TAG_SHOT_BY_PLAYER = "player";
    /**
     * {@code TAG_IN_GROUND} names the embedded-state boolean retained across
     * an arrow entity save and load.
     */
    protected static final String TAG_IN_GROUND = "inGround";

    /**
     * Base damage before impact-specific adjustments.
     */
    @Getter
    @Setter
    protected float baseDamage;
    /**
     * Stored Power enchantment level.
     */
    @Getter
    @Setter
    protected int powerLevel;
    /**
     * Stored Punch enchantment level.
     */
    @Getter
    @Setter
    protected int punchLevel;
    /**
     * Stored Piercing enchantment level.
     */
    @Getter
    @Setter
    protected int piercingLevel;
    /**
     * Pickup-discard flag for infinite arrows.
     */
    @Getter
    @Setter
    protected boolean infinite;
    /**
     * Player pickup suppression flag.
     */
    @Getter
    @Setter
    protected boolean pickUpDisabled;
    /**
     * Critical impact and particle flag.
     */
    @Getter
    protected boolean critical;
    /**
     * Embedded block collision state flag.
     */
    @Getter
    @Setter
    protected boolean inGround;

    /**
     * Injected component supplying optional potion contents.
     */
    @Dependency
    protected EntityPotionComponent potionComponent;

    /**
     * Creates an arrow base with the configured default base damage.
     * Enchantment levels and flags retain their zero-valued initial state
     * until component initialization restores saved data or callers configure
     * them.
     */
    public EntityArrowBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.baseDamage = DEFAULT_BASE_DAMAGE;
    }

    /**
     * Replaces the critical flag and broadcasts the current arrow state.
     * Block-impact handling clears this flag before marking an arrow embedded,
     * while generated setters for other flags only replace their stored
     * values.
     */
    @Override
    public void setCritical(boolean critical) {
        this.critical = critical;
        broadcastState();
    }

    /**
     * Returns the small arrow local bounding box in block coordinates.
     */
    @Override
    public AABBdc getBaseAABB() {
        return new AABBd(-0.025, 0.0, -0.025, 0.025, 0.05, 0.025);
    }

    /**
     * Loads inherited entity data and restores present arrow enchantments,
     * pickup flags, and embedded state. Missing tags preserve current values,
     * while signed bytes widen directly into the integer enchantment fields.
     * Any nonzero infinity byte enables the infinite flag.
     *
     * <p>
     * The persisted player flag has the opposite meaning from {@code
     * pickUpDisabled}, so loading inverts it instead of storing it directly.
     * The embedded flag is restored independently from current collision
     * geometry, allowing physics to recheck support later. This method does
     * not restore base damage or critical state through arrow-specific tags.
     */
    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_POWER_LEVEL, b -> this.powerLevel = b);
        nbt.listenForByte(TAG_PUNCH_LEVEL, b -> this.punchLevel = b);
        nbt.listenForByte(TAG_INFINITY_LEVEL, b -> this.infinite = b != 0);
        nbt.listenForByte(TAG_PIERCING_LEVEL, b -> this.piercingLevel = b);
        // The saved player flag means pickup eligibility, so invert it to recover
        // the runtime pickup-disabled flag.
        nbt.listenForBoolean(TAG_SHOT_BY_PLAYER, b -> this.pickUpDisabled = !b);
        nbt.listenForBoolean(TAG_IN_GROUND, b -> this.inGround = b);
    }

    /**
     * Returns inherited entity data with the arrow-specific flags and
     * enchantment levels. Enchantment integers are narrowed to signed bytes,
     * so values outside that range lose high bits rather than retaining their
     * wider runtime value. Infinity is encoded as zero or one.
     *
     * <p>
     * The saved player flag is the inverse of {@code pickUpDisabled}, matching
     * the load path rather than the runtime field name. Embedded state is
     * written separately so a recreated arrow can retain its pickup-relevant
     * state before collision revalidation. Base damage and critical state are
     * not added by this override.
     */
    @Override
    public NbtMap saveNBT() {
        // Narrow enchantment levels to the entity byte format and invert pickup
        // suppression into its stored eligibility polarity.
        var builder = super.saveNBT()
                .toBuilder()
                .putByte(TAG_POWER_LEVEL, (byte) powerLevel)
                .putByte(TAG_PUNCH_LEVEL, (byte) punchLevel)
                .putByte(TAG_INFINITY_LEVEL, (byte) (infinite ? 1 : 0))
                .putByte(TAG_PIERCING_LEVEL, (byte) piercingLevel)
                .putBoolean(TAG_SHOT_BY_PLAYER, !pickUpDisabled)
                .putBoolean(TAG_IN_GROUND, inGround);

        return builder.build();
    }

}
