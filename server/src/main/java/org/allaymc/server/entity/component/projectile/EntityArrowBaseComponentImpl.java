package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.allaymc.api.item.data.PotionType;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

/**
 * @author harryxi | daoge_cmd
 */
@Getter
@Setter
public class EntityArrowBaseComponentImpl extends EntityProjectileBaseComponentImpl implements EntityArrowBaseComponent {

    protected static final int DEFAULT_BASE_DAMAGE = 1;

    protected static final String TAG_POWER_LEVEL = "enchantPower";
    protected static final String TAG_PUNCH_LEVEL = "enchantPunch";
    protected static final String TAG_FLAME_LEVEL = "enchantFlame";
    protected static final String TAG_INFINITY_LEVEL = "enchantInfinity";
    protected static final String TAG_POTION_ID = "auxValue";

    protected float baseDamage;
    protected int powerLevel;
    protected int punchLevel;
    protected int flameLevel;
    protected int infinityLevel;
    protected PotionType potionType;

    public EntityArrowBaseComponentImpl(EntityInitInfo info) {
        super(info);
        this.baseDamage = DEFAULT_BASE_DAMAGE;
    }

    @Override
    public boolean isCritical() {
        return this.metadata.get(EntityFlag.CRITICAL);
    }

    @Override
    public void setCritical(boolean critical) {
        this.metadata.set(EntityFlag.CRITICAL, critical);
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
        nbt.listenForByte(TAG_FLAME_LEVEL, b -> this.flameLevel = b);
        nbt.listenForByte(TAG_INFINITY_LEVEL, b -> this.infinityLevel = b);
        nbt.listenForByte(TAG_POTION_ID, b -> this.potionType = b > 0 ? PotionType.fromId(b - 1) : null);
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putByte(TAG_POWER_LEVEL, (byte) powerLevel)
                .putByte(TAG_PUNCH_LEVEL, (byte) punchLevel)
                .putByte(TAG_FLAME_LEVEL, (byte) flameLevel)
                .putByte(TAG_INFINITY_LEVEL, (byte) infinityLevel)
                .putByte(TAG_POTION_ID, (byte) (potionType.ordinal() + 1))
                .build();
    }
}
