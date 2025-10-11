package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.item.data.PotionType;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author harryxi | daoge_cmd
 */
public class EntityArrowBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityArrowBaseComponent {

    protected static final int DEFAULT_BASE_DAMAGE = 1;

    protected static final String TAG_POWER_LEVEL = "enchantPower";
    protected static final String TAG_PUNCH_LEVEL = "enchantPunch";
    protected static final String TAG_INFINITY_LEVEL = "enchantInfinity";
    protected static final String TAG_POTION_ID = "auxValue";
    protected static final String TAG_SHOT_BY_PLAYER = "player";

    @Getter
    @Setter
    protected float baseDamage;
    @Getter
    @Setter
    protected int powerLevel;
    @Getter
    @Setter
    protected int punchLevel;
    @Getter
    @Setter
    protected boolean infinite;
    @Getter
    @Setter
    protected boolean pickUpDisabled;
    @Getter
    protected boolean critical;
    @Getter
    protected PotionType potionType;

    public EntityArrowBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.baseDamage = DEFAULT_BASE_DAMAGE;
    }

    @Override
    public void setCritical(boolean critical) {
        this.critical = critical;
        broadcastState();
    }

    @Override
    public void setPotionType(PotionType potionType) {
        this.potionType = potionType;
        broadcastState();
    }

    @Override
    public AABBdc getAABB() {
        return new AABBd(-0.025, 0.0, -0.025, 0.025, 0.05, 0.025);
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        nbt.listenForByte(TAG_POWER_LEVEL, b -> this.powerLevel = b);
        nbt.listenForByte(TAG_PUNCH_LEVEL, b -> this.punchLevel = b);
        nbt.listenForByte(TAG_INFINITY_LEVEL, b -> this.infinite = b != 0);
        nbt.listenForBoolean(TAG_SHOT_BY_PLAYER, b -> this.pickUpDisabled = !b);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT()
                .toBuilder()
                .putByte(TAG_POWER_LEVEL, (byte) powerLevel)
                .putByte(TAG_PUNCH_LEVEL, (byte) punchLevel)
                .putByte(TAG_INFINITY_LEVEL, (byte) (infinite ? 1 : 0))
                .putBoolean(TAG_SHOT_BY_PLAYER, !pickUpDisabled);

        // Store this for vanilla map compatibility, although we don't need to save this
        // again in nbt because it's already saved in metadata
        var potionType = getPotionType();
        if (potionType != null) {
            builder.putByte(TAG_POTION_ID, (byte) (potionType.ordinal() + 1));
        }

        return builder.build();
    }

    @Override
    protected boolean hasDeadTimer() {
        return false;
    }
}
