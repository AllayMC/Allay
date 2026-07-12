package org.allaymc.server.item.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.player.Player;
import org.allaymc.api.world.Dimension;
import org.allaymc.api.world.sound.CustomSound;
import org.allaymc.api.world.sound.SoundNames;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
class ItemGoatHornBaseComponentImplTest {

    @Test
    void shouldUseSharedCooldownCategoryForEveryVariant() {
        var dimension = mock(Dimension.class);
        var player = mock(EntityPlayer.class);
        when(player.getDimension()).thenReturn(dimension);
        when(player.getLocation()).thenReturn(new Location3d(0, 64, 0, dimension));
        when(dimension.getPlayers()).thenReturn(Set.of());
        when(player.isCooldownEnd(ItemGoatHornBaseComponentImpl.COOLDOWN_CATEGORY))
                .thenReturn(true, false);

        component(0).rightClickItemInAir(player);
        component(7).rightClickItemInAir(player);

        verify(player, times(2)).isCooldownEnd(ItemGoatHornBaseComponentImpl.COOLDOWN_CATEGORY);
        verify(player).setCooldown(
                ItemGoatHornBaseComponentImpl.COOLDOWN_CATEGORY,
                ItemGoatHornBaseComponentImpl.COOLDOWN_TICKS,
                true
        );
    }

    @Test
    void shouldPlayVariantSoundOnceAndOnlyWithinRange() {
        var dimension = mock(Dimension.class);
        var source = mock(EntityPlayer.class);
        var sourcePosition = new Location3d(0, 64, 0, dimension);
        when(source.getDimension()).thenReturn(dimension);
        when(source.getLocation()).thenReturn(sourcePosition);
        when(source.isCooldownEnd(ItemGoatHornBaseComponentImpl.COOLDOWN_CATEGORY))
                .thenReturn(true, false);

        var atLimit = viewerAt(dimension, 256);
        var outside = viewerAt(dimension, 256.01);
        when(dimension.getPlayers()).thenReturn(Set.of(atLimit, outside));

        var component = component(5);
        component.rightClickItemInAir(source);
        component.rightClickItemInAir(source);

        verify(source).setCooldown(
                ItemGoatHornBaseComponentImpl.COOLDOWN_CATEGORY,
                ItemGoatHornBaseComponentImpl.COOLDOWN_TICKS,
                true
        );
        verify(atLimit).viewSound(
                new CustomSound(SoundNames.HORN_CALL_5),
                sourcePosition,
                true
        );
        verify(outside, never()).viewSound(any(), any(), anyBoolean());
    }

    private static ItemGoatHornBaseComponentImpl component(int meta) {
        return new ItemGoatHornBaseComponentImpl(ItemStackInitInfo.builder()
                .count(1)
                .meta(meta)
                .assignUniqueId(false)
                .build());
    }

    private static Player viewerAt(Dimension dimension, double x) {
        var viewer = mock(Player.class);
        var controlledEntity = mock(EntityPlayer.class);
        when(controlledEntity.getLocation()).thenReturn(new Location3d(x, 64, 0, dimension));
        when(viewer.getControlledEntity()).thenReturn(controlledEntity);
        return viewer;
    }
}
