package org.allaymc.server.player;

import org.allaymc.api.player.Player;

import java.util.Map;
import java.util.Set;
import java.util.UUID;

final class PlayerNameMatcher {
    private PlayerNameMatcher() {
    }

    static Player findOnlinePlayerByName(Map<UUID, Player> players, String playerName) {
        Player ignoreCaseMatch = null;
        for (var player : players.values()) {
            var originName = player.getOriginName();
            if (originName.equals(playerName)) {
                return player;
            }
            if (originName.equalsIgnoreCase(playerName)) {
                if (ignoreCaseMatch != null) {
                    return null;
                }
                ignoreCaseMatch = player;
            }
        }
        return ignoreCaseMatch;
    }

    static boolean matchesPlayer(Player player, String uuidOrName) {
        return player.getLoginData().getUuid().toString().equals(uuidOrName) ||
               player.getOriginName().equalsIgnoreCase(uuidOrName);
    }

    static boolean containsStoredIdentity(Set<String> identities, String uuidOrName) {
        if (identities.contains(uuidOrName)) {
            return true;
        }
        if (isUuid(uuidOrName)) {
            return false;
        }
        return identities.stream()
                .filter(identity -> !isUuid(identity))
                .anyMatch(identity -> identity.equalsIgnoreCase(uuidOrName));
    }

    static String resolveStoredIdentity(Set<String> identities, String uuidOrName) {
        if (identities.contains(uuidOrName) || isUuid(uuidOrName)) {
            return uuidOrName;
        }

        String ignoreCaseMatch = null;
        for (var identity : identities) {
            if (isUuid(identity) || !identity.equalsIgnoreCase(uuidOrName)) {
                continue;
            }
            if (ignoreCaseMatch != null) {
                return uuidOrName;
            }
            ignoreCaseMatch = identity;
        }

        return ignoreCaseMatch != null ? ignoreCaseMatch : uuidOrName;
    }

    private static boolean isUuid(String value) {
        try {
            UUID.fromString(value);
            return true;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }
}
