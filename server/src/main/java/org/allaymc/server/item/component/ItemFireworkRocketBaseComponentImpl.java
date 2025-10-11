package org.allaymc.server.item.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.FireworkExplosion;
import org.allaymc.api.world.sound.SimpleSound;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;
import org.joml.Vector3ic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author daoge_cmd
 */
@Getter
@Setter
public class ItemFireworkRocketBaseComponentImpl extends ItemBaseComponentImpl implements ItemFireworkRocketBaseComponent {

    protected static final String TAG_FIREWORKS = "Fireworks";
    protected static final String TAG_EXPLOSIONS = "Explosions";
    protected static final String TAG_FLIGHT = "Flight";

    protected int duration;
    protected Set<FireworkExplosion> explosions;

    public ItemFireworkRocketBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.explosions = new HashSet<>();
    }

    @Override
    public boolean canUseItemInAir(EntityPlayer player) {
        return player.isGliding();
    }

    @Override
    public boolean useItemInAir(EntityPlayer player, long usedTime) {
        var location = player.getLocation();
        var dimension = player.getDimension();
        dimension.addSound(location, SimpleSound.FIREWORK_LAUNCH);

        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder().loc(location).build()
        );
        firework.setExistenceTicks(getRandomizedDuration());
        firework.setAttachedPlayer(player);
        firework.setExplosions(this.explosions);
        dimension.getEntityManager().addEntity(firework);

        return true;
    }

    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var clickedPos = interactInfo.clickedPos();
        var pos = new Vector3d(
                clickedBlockPos.x() + clickedPos.x(),
                clickedBlockPos.y() + clickedPos.y(),
                clickedBlockPos.z() + clickedPos.z()
        );

        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos)
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 90)
                        .build()
        );
        firework.setExistenceTicks(getRandomizedDuration());
        firework.setExplosions(this.explosions);
        dimension.getEntityManager().addEntity(firework);
        dimension.addSound(pos, SimpleSound.FIREWORK_LAUNCH);

        return true;
    }

    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS, this::loadFireworks);
    }

    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag()
                .toBuilder()
                .putCompound(TAG_FIREWORKS, saveFireworks())
                .build();
    }

    protected void loadFireworks(NbtMap nbt) {
        nbt.listenForList(TAG_EXPLOSIONS, NbtType.COMPOUND, entries -> {
            for (var entry : entries) {
                this.explosions.add(FireworkExplosion.fromNBT(entry));
            }
        });

        nbt.listenForByte(TAG_FLIGHT, value -> this.duration = value);
    }

    protected NbtMap saveFireworks() {
        return NbtMap.builder()
                .putList(TAG_EXPLOSIONS, NbtType.COMPOUND, this.explosions.stream().map(FireworkExplosion::saveNBT).toList())
                .putByte(TAG_FLIGHT, (byte) this.duration)
                .build();
    }
}
