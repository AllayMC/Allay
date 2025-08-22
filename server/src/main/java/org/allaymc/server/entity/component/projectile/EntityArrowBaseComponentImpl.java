package org.allaymc.server.entity.component.projectile;

import lombok.Getter;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.initinfo.EntityInitInfo;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;

import java.util.Collections;
import java.util.List;

public class EntityArrowBaseComponentImpl extends EntityProjectileBaseComponentImpl {
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

    public boolean isCritical(){
        return this.metadata.get(EntityFlag.CRITICAL);
    }

    public void setCritical(boolean critical){
        this.metadata.set(EntityFlag.CRITICAL, critical);
    }
//
//    @Override
//    public AABBdc getAABB() {
//        return new AABBd(-0.05, 0.0, -0.05, 0.05, 0.5, 0.05);
//    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);
        enchantPower = nbt.getByte("enchantPower", (byte) 0);
        enchantPunch = nbt.getByte("enchantPunch", (byte) 0);
        enchantFlame = nbt.getByte("enchantFlame", (byte) 0);
        enchantInfinity = nbt.getByte("enchantInfinity", (byte) 0);

        mobEffects = nbt.getList("mobEffects", NbtType.COMPOUND).stream()
                .map(EffectInstance::fromNBT)
                .toList();
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putByte("enchantPower", enchantPower);
        builder.putByte("enchantPunch", enchantPunch);
        builder.putByte("enchantFlame", enchantFlame);
        builder.putByte("enchantInfinity", enchantInfinity);

        List<NbtMap> effects = mobEffects.stream()
                .map(EffectInstance::saveNBT)
                .toList();
        builder.putList("mobEffects", NbtType.COMPOUND, effects);

        return builder.build();
    }
}
