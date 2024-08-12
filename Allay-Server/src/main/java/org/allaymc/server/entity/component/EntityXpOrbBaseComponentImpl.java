package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntityXpOrbBaseComponent;
import org.allaymc.api.entity.init.EntityInitInfo;
import org.allaymc.server.entity.component.common.EntityBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityDataTypes;
import org.cloudburstmc.protocol.bedrock.data.entity.EntityFlag;
import org.joml.primitives.AABBf;
import org.joml.primitives.AABBfc;

/**
 * Allay Project 2024/8/12
 *
 * @author daoge_cmd
 */
public class EntityXpOrbBaseComponentImpl extends EntityBaseComponentImpl implements EntityXpOrbBaseComponent {

    public static final int MAX_AGE = 6000;

    @Getter
    @Setter
    protected short age;
    @Getter
    @Setter
    protected int experienceValue;

    public EntityXpOrbBaseComponentImpl(EntityInitInfo info) {
        super(info);
    }

    @Override
    protected void initMetadata() {
        super.initMetadata();

        metadata.set(EntityFlag.HAS_COLLISION, false);
    }

    @Override
    public void tick() {
        super.tick();
        if (age != -1) {
            age++;
            if (age >= MAX_AGE) despawn();
        }
    }

    @Override
    public void loadNBT(NbtMap nbt) {
        super.loadNBT(nbt);

        nbt.listenForShort("Age", age -> this.age = age);
        nbt.listenForInt("ExperienceValue", experienceValue -> this.experienceValue = experienceValue);

        setAndSendEntityData(EntityDataTypes.VALUE, experienceValue);
    }

    @Override
    public NbtMap saveNBT() {
        var builder = super.saveNBT().toBuilder();

        builder.putShort("Age", age);
        builder.putInt("ExperienceValue", experienceValue);

        return builder.build();
    }

    @Override
    public AABBfc getAABB() {
        return new AABBf(-0.05f, 0.0f, -0.05f, 0.05f, 0.1f, 0.05f);
    }

    @Override
    public boolean computeBlockCollisionMotion() {
        return true;
    }
}
