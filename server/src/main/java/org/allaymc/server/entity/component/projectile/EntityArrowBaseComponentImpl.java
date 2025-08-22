package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.primitives.AABBd;
import org.joml.primitives.AABBdc;

import java.util.Collections;
import java.util.List;

/**
 * @author harryxi | daoge_cmd
 */
public class EntityArrowBaseComponentImpl extends EntityProjectileBaseComponentImpl {

    protected static final String TAG_ENCHANT_POWER = "enchantPower";
    protected static final String TAG_ENCHANT_PUNCH = "enchantPunch";
    protected static final String TAG_ENCHANT_FLAME = "enchantFlame";
    protected static final String TAG_ENCHANT_INFINITY = "enchantInfinity";
    protected static final String TAG_MOB_EFFECTS = "mobEffects";

    @Getter
    protected double baseDamage = 1;
    @Getter
    protected byte enchantPower = 0;
    @Getter
    protected byte enchantPunch = 0;
    @Getter
    protected byte enchantFlame = 0;
    @Getter
    protected byte enchantInfinity = 0;
    @Getter
    protected List<EffectInstance> mobEffects = Collections.emptyList();

    public EntityArrowBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    public boolean isCritical() {
        return this.metadata.get(EntityFlag.CRITICAL);
    }

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
        nbt.listenForByte(TAG_ENCHANT_POWER, enchantPower -> this.enchantPower = enchantPower);
        nbt.listenForByte(TAG_ENCHANT_PUNCH, enchantPunch -> this.enchantPunch = enchantPunch);
        nbt.listenForByte(TAG_ENCHANT_FLAME, enchantFlame -> this.enchantFlame = enchantFlame);
        nbt.listenForByte(TAG_ENCHANT_INFINITY, enchantInfinity -> this.enchantInfinity = enchantInfinity);
        nbt.listenForList(
                TAG_MOB_EFFECTS, NbtType.COMPOUND,
                list -> this.mobEffects = list.stream().map(EffectInstance::fromNBT).toList()
        );
    }

    @Override
    public NbtMap saveNBT() {
        return super.saveNBT()
                .toBuilder()
                .putByte(TAG_ENCHANT_POWER, enchantPower)
                .putByte(TAG_ENCHANT_PUNCH, enchantPunch)
                .putByte(TAG_ENCHANT_FLAME, enchantFlame)
                .putByte(TAG_ENCHANT_INFINITY, enchantInfinity)
                .putList(TAG_MOB_EFFECTS, NbtType.COMPOUND, mobEffects.stream().map(EffectInstance::saveNBT).toList())
                .build();
    }
}
