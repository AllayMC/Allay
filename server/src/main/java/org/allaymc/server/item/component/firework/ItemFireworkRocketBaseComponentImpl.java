package org.allaymc.server.item.component.firework;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.block.dto.PlayerInteractInfo;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemFireworkRocketBaseComponent;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.explosion.FireworkExplosion;
import org.allaymc.api.world.sound.SimpleSound;
import org.allaymc.server.item.component.ItemBaseComponentImpl;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtType;
import org.joml.Vector3d;
import org.joml.Vector3ic;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * {@code ItemFireworkRocketBaseComponentImpl} supplies rocket configuration,
 * launching, and extra-tag serialization. Flight duration determines
 * randomized lifetime, while explosion definitions pass to the spawned entity;
 * the component can launch from a clicked position or attach a rocket to a
 * gliding player.
 *
 * @author daoge_cmd
 */
@Getter
@Setter
public class ItemFireworkRocketBaseComponentImpl extends ItemBaseComponentImpl implements ItemFireworkRocketBaseComponent {

    // These tags place explosion definitions and signed-byte flight duration inside
    // the nested Fireworks compound used by rocket loading and saving.
    /**
     * Rocket configuration compound tag.
     */
    protected static final String TAG_FIREWORKS = "Fireworks";
    /**
     * Ordered explosion-list payload tag.
     */
    protected static final String TAG_EXPLOSIONS = "Explosions";
    /**
     * Signed-byte flight duration tag.
     */
    protected static final String TAG_FLIGHT = "Flight";

    /**
     * {@code duration} holds base flight seconds without validation. The
     * generated setter stores any integer, while {@code saveFireworks} narrows
     * that integer to a signed byte.
     */
    protected int duration;
    /**
     * {@code explosions} holds the set shared by generated getters and
     * setters. Loading adds entries to this set, so replacement sets must
     * remain non-{@code null} and mutable when loading is required.
     */
    protected Set<FireworkExplosion> explosions;

    /**
     * Creates rocket stack behavior with one base flight second and no
     * explosions. The constructor initializes mutable explosion storage before
     * component initialization can load any stored firework configuration.
     */
    public ItemFireworkRocketBaseComponentImpl(ItemStackInitInfo initInfo) {
        super(initInfo);
        this.duration = 1;
        this.explosions = new HashSet<>();
    }

    /**
     * Launches a player-attached rocket only when the supplied player is
     * gliding. The rocket receives the configured lifetime and explosions;
     * publication precedes the held-item consumption attempt, which leaves
     * creative inventory unchanged.
     */
    @Override
    public void rightClickItemInAir(EntityPlayer player) {
        if (!player.isGliding()) {
            return;
        }

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

        player.tryConsumeItemInHand();
    }

    /**
     * Launches a rocket at the interaction's clicked point and returns
     * {@code true}. The method requires interaction context, uses a random yaw
     * and vertical pitch, and attempts held-item consumption after entity
     * publication and launch sound.
     */
    @Override
    public boolean useItemOnBlock(Dimension dimension, Vector3ic placeBlockPos, PlayerInteractInfo interactInfo) {
        var clickedBlockPos = interactInfo.clickedBlockPos();
        var clickedPos = interactInfo.clickedPos();
        // The click offset is relative to the clicked block. Add its block
        // coordinates so the rocket spawns at the actual interaction point.
        var pos = new Vector3d(
                clickedBlockPos.x() + clickedPos.x(),
                clickedBlockPos.y() + clickedPos.y(),
                clickedBlockPos.z() + clickedPos.z()
        );

        var firework = EntityTypes.FIREWORKS_ROCKET.createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(pos)
                        // A full-circle yaw and ninety-degree pitch orient the free rocket vertically.
                        .rot(ThreadLocalRandom.current().nextDouble() * 360, 90)
                        .build()
        );
        firework.setExistenceTicks(getRandomizedDuration());
        firework.setExplosions(this.explosions);
        dimension.getEntityManager().addEntity(firework);
        dimension.addSound(pos, SimpleSound.FIREWORK_LAUNCH);

        interactInfo.player().tryConsumeItemInHand();
        return true;
    }

    /**
     * Loads inherited stack data and any nested firework configuration. A
     * missing firework compound leaves the current duration and explosion set
     * intact; present explosion entries are added rather than replacing
     * existing entries.
     */
    @Override
    public void loadExtraTag(NbtMap extraTag) {
        super.loadExtraTag(extraTag);
        extraTag.listenForCompound(TAG_FIREWORKS, this::loadFireworks);
    }

    /**
     * Serializes inherited stack data with the current nested firework
     * configuration. The explosion set must be non-{@code null}, and flight
     * duration is narrowed to a signed byte inside that compound.
     */
    @Override
    public NbtMap saveExtraTag() {
        return super.saveExtraTag()
                .toBuilder()
                .putCompound(TAG_FIREWORKS, saveFireworks())
                .build();
    }

    /**
     * Adds decoded explosion entries and applies a present signed-byte flight
     * duration. Existing explosions remain in the set; decoding or mutation
     * failure can leave earlier additions in place before flight duration is
     * read.
     */
    protected void loadFireworks(NbtMap nbt) {
        nbt.listenForList(TAG_EXPLOSIONS, NbtType.COMPOUND, entries -> {
            for (var entry : entries) {
                this.explosions.add(FireworkExplosion.fromNBT(entry));
            }
        });

        nbt.listenForByte(TAG_FLIGHT, value -> this.duration = value);
    }

    /**
     * Serializes the current explosion set and signed-byte flight duration
     * into a compound. The set is iterated in its current order, and integer
     * duration values outside byte range lose high bits.
     */
    protected NbtMap saveFireworks() {
        return NbtMap.builder()
                .putList(TAG_EXPLOSIONS, NbtType.COMPOUND, this.explosions.stream().map(FireworkExplosion::saveNBT).toList())
                // Flight uses a signed byte, so serialization retains only the integer's low eight bits.
                .putByte(TAG_FLIGHT, (byte) this.duration)
                .build();
    }
}
