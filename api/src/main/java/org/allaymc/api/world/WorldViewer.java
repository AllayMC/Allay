package org.allaymc.api.world;

import org.allaymc.api.block.action.BlockAction;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.action.EntityAction;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.entity.data.EntityAnimation;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.enchantment.EnchantOption;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.primitiveshape.PrimitiveShapeViewer;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.chunk.Chunk;
import org.allaymc.api.world.data.Weather;
import org.allaymc.api.world.gamerule.GameRules;
import org.allaymc.api.world.particle.Particle;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.Sound;
import org.joml.Vector3dc;
import org.joml.Vector3ic;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * {@code WorldViewer} receives representations of world objects and changes
 * for presentation to a viewer. Entity, chunk, block, sound, and interface
 * operations describe client-visible updates; they do not themselves define
 * simulation mutations. Implementations translate these requests through the
 * viewer's supported protocol.
 *
 * @author daoge_cmd
 */
public interface WorldViewer extends PrimitiveShapeViewer {
    /**
     * Presents the supplied entity to this viewer using its current state.
     */
    void viewEntity(Entity entity);

    /**
     * Removes the supplied entity from this viewer's presented world state.
     */
    void removeEntity(Entity entity);

    /**
     * Presents an entity location with ordinary movement or teleport
     * semantics. {@code teleporting} identifies a discontinuous move; callers
     * supply the destination without relying on this presentation operation to
     * move the simulated entity.
     */
    void viewEntityLocation(Entity entity, Location3dc newLocation, boolean teleporting);

    /**
     * Presents the supplied motion vector for the specified physics entity.
     */
    <T extends Entity & EntityPhysicsComponent> void viewEntityMotion(T entity, Vector3dc motion);

    /**
     * Presents the supplied entity's current metadata to this viewer.
     */
    void viewEntityState(Entity entity);

    /**
     * Presents the main-hand equipment of the supplied container-holding
     * entity.
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityHand(T entity);

    /**
     * Presents the offhand equipment of the supplied container-holding entity.
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityOffhand(T entity);

    /**
     * Presents the armor equipment of the supplied container-holding entity.
     */
    <T extends Entity & EntityContainerHolderComponent> void viewEntityArmors(T entity);

    /**
     * Presents the selected animation performed by the supplied entity.
     */
    void viewEntityAnimation(Entity entity, EntityAnimation animation);

    /**
     * Presents the supplied action associated with the specified entity.
     */
    void viewEntityAction(Entity entity, EntityAction action);

    /**
     * Presents an effect addition, replacement, or removal for the supplied
     * entity. {@code oldEffect == null} denotes addition, {@code newEffect ==
     * null} denotes removal, and both values must not be {@code null}
     * together.
     */
    void viewEntityEffectChange(Entity entity, EffectInstance newEffect, EffectInstance oldEffect);

    /**
     * Presents the current skin associated with the supplied player entity.
     */
    void viewPlayerSkin(EntityPlayer player);

    /**
     * Presents a player emote with its chat message enabled for this viewer.
     * This convenience overload supplies {@code false} for {@code silence} and
     * delegates to the full emote presentation operation.
     */
    default void viewPlayerEmote(EntityPlayer player, UUID emoteId) {
        viewPlayerEmote(player, emoteId, false);
    }

    /**
     * Presents the selected emote performed by the supplied player entity.
     * {@code silence} requests suppression of the emote chat message when
     * {@code true}; {@code false} permits the usual client message.
     */
    void viewPlayerEmote(EntityPlayer player, UUID emoteId, boolean silence);

    /**
     * Presents the game mode associated with the supplied player entity.
     */
    void viewPlayerGameMode(EntityPlayer player);

    /**
     * Presents the supplied chunk and its associated visible world content.
     */
    void viewChunk(Chunk chunk);

    /**
     * Removes content associated with the chunk identified by its packed
     * coordinates.
     */
    void removeChunk(long chunkHash);

    /**
     * Presents the supplied world time of day in game ticks.
     */
    void viewTime(int timeOfDay);

    /**
     * Presents the supplied game rule values to this world viewer.
     */
    void viewGameRules(GameRules gameRules);

    /**
     * Presents one block state change at the supplied dimension coordinates
     * and layer.
     */
    void viewBlockUpdate(Vector3ic pos, int layer, BlockState blockState);

    /**
     * Presents batched changes for the chunk's standard and extra block
     * layers. Each update uses dimension block coordinates with a height
     * inside the chunk's dimension; callers keep both layer collections
     * distinct.
     */
    void viewBlockUpdates(Chunk chunk, Collection<BlockUpdate> blockUpdates, Collection<BlockUpdate> extraBlockUpdates);

    /**
     * {@code BlockUpdate} carries one block state change at dimension block
     * coordinates.
     *
     * @param x dimension block x coordinate
     * @param y dimension block y coordinate
     * @param z dimension block z coordinate
     * @param blockState replacement block state value
     */
    record BlockUpdate(int x, int y, int z, BlockState blockState) {
    }

    /**
     * Presents a block action at the supplied dimension block coordinates.
     */
    void viewBlockAction(Vector3ic pos, BlockAction action);

    /**
     * Presents the supplied sound at a position with the selected attenuation
     * policy. {@code relative} enables distance-based volume when {@code
     * true}; {@code false} requests full volume independent of distance.
     */
    void viewSound(Sound sound, Vector3dc pos, boolean relative);

    /**
     * Stops a named sound or every currently playing sound. {@code null}
     * selects all sounds; other values use the same sound identifier supplied
     * when a custom sound is presented.
     */
    void stopSound(String soundName);

    /**
     * Requests that this viewer stop all currently playing sounds. This
     * convenience operation passes {@code null} to {@link #stopSound(String)},
     * using that operation's all-sounds selection instead of identifying one
     * sound.
     */
    default void stopAllSounds() {
        stopSound(null);
    }

    /**
     * Presents the supplied particle at the specified dimension position.
     */
    void viewParticle(Particle particle, Vector3dc pos);

    /**
     * Presents the supplied weather state to this world viewer.
     */
    void viewWeather(Weather weather);

    /**
     * Presents the supplied block entity's client-visible state to this
     * viewer.
     */
    void viewBlockEntity(BlockEntity blockEntity);

    /**
     * Opens the reading interface for the lectern at the supplied coordinates.
     */
    void viewLectern(Vector3ic pos);

    /**
     * Opens a sign editor for the selected face at the supplied coordinates.
     * {@code true} selects the front face and {@code false} selects the back
     * face for the viewer.
     */
    void viewSignEditor(Vector3ic pos, boolean frontSide);

    /**
     * Presents the count of sleeping players and the eligible player total.
     */
    void viewSleepingIndicator(int sleepingCount, int totalCount);

    /**
     * Opens the command block editor at the supplied dimension block
     * coordinates.
     */
    void viewCommandBlockEditor(Vector3ic pos);

    /**
     * Presents available enchantment choices together with their selection
     * identifiers. Each pair supplies the network ID used to identify the
     * choice and the corresponding {@code EnchantOption} offered by the table.
     */
    void viewEnchantOptions(List<Pair<Integer, EnchantOption>> enchantOptions);
}
