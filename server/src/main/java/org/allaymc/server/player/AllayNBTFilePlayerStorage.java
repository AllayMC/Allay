package org.allaymc.server.player;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.PlayerData;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.nbt.NbtUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * {@code AllayNBTFilePlayerStorage} stores each player-data envelope in a UUID-named
 * GZIP NBT file. Missing or unreadable main files yield fresh global-spawn data
 * rather than consulting a backup file, so read success does not prove that an
 * existing file was recovered.
 *
 * <p>
 * Saving first deletes a leftover backup and moves the current main file to the
 * backup name before writing the replacement. A write failure attempts to move the
 * backup back without replacing an existing target; a partially created main file
 * can therefore prevent restoration and propagate the move failure. This is a
 * sequential recovery attempt rather than an atomic commit, and callers must
 * serialize operations targeting the same UUID.
 *
 * @author daoge_cmd
 */
@Slf4j
public class AllayNBTFilePlayerStorage extends AllayPlayerStorage {
    /**
     * {@code dataFolderPath} owns the directory used for UUID-named main and backup
     * files. The constructor creates only this directory when it is absent.
     */
    protected Path dataFolderPath;

    /**
     * Creates a file-backed store rooted at the supplied data directory. The
     * constructor creates that directory when absent, requires its parent to exist,
     * and propagates filesystem failures through Lombok's unchecked exception
     * handling.
     */
    @SneakyThrows
    public AllayNBTFilePlayerStorage(Path dataFolderPath) {
        this.dataFolderPath = dataFolderPath;
        if (!Files.exists(dataFolderPath)) Files.createDirectory(dataFolderPath);
    }

    /**
     * Reads the UUID's main GZIP NBT file and decodes its player-data envelope. A
     * missing file or any caught read/decoding failure returns global-spawn
     * defaults; caught failures are logged, and backup files are not consulted.
     */
    @Override
    public PlayerData readPlayerData(UUID uuid) {
        var path = buildPlayerDataFilePath(uuid);
        // A first login has no main file; select the current global spawn defaults without trying to
        // load an unrelated leftover backup.
        if (!Files.exists(path)) return PlayerData.createEmpty();

        try (var reader = NbtUtils.createGZIPReader(Files.newInputStream(path))) {
            return PlayerData.fromNBT((NbtMap) reader.readTag());
        } catch (Throwable e) {
            // An unreadable or malformed main file must not abort this read path; log the failure and
            // return fresh global-spawn data instead.
            log.error("Error while reading player data {}", uuid, e);
            return PlayerData.createEmpty();
        }
    }

    /**
     * Writes a replacement player-data file after moving an existing main file to a
     * backup. Callers must serialize access to the same UUID because these
     * filesystem operations are not protected by a lock.
     *
     * <p>
     * The method deletes an old backup before moving the main file. A caught write
     * failure attempts restoration with a move that does not replace an existing
     * main file, so restoration can itself throw before logging or final backup
     * cleanup. Successful writes delete the backup; the method does not provide an
     * atomic replacement or a success result.
     */
    @SneakyThrows
    @Override
    public void savePlayerData(UUID uuid, PlayerData playerData) {
        var path = buildPlayerDataFilePath(uuid);

        var oldPath = path.resolveSibling(uuid + "_old.nbt");
        if (Files.exists(oldPath)) {
            // Discard an earlier leftover backup before creating this save's backup; the next move uses
            // a distinct path and does not replace an existing target.
            log.warn("Undeleted tmp player data file is found, which may caused by incorrect shutdown. File: {}", oldPath);
            Files.delete(oldPath);
        }

        // Preserve the previous main file before opening the replacement, so a caught write failure
        // has a source for the restoration attempt below.
        var currentFileExists = Files.exists(path);
        if (currentFileExists) Files.move(path, oldPath);

        try (var writer = NbtUtils.createGZIPWriter(Files.newOutputStream(path))) {
            writer.writeTag(playerData.toNBT());
        } catch (Throwable e) {
            if (currentFileExists) {
                // Attempt restoration without replacing the new path. A partial main file may still
                // exist, so this move can fail before logging and final backup cleanup.
                Files.move(oldPath, path);
            }
            log.error("Error while writing player data {}", uuid, e);
        }

        // Release the previous file after writing or a completed recovery attempt; a failed recovery
        // move propagates before reaching this cleanup.
        Files.deleteIfExists(oldPath);
    }

    /**
     * Deletes the UUID's main data file and reports whether it existed. Backup files
     * are left alone, and filesystem failures propagate instead of being converted
     * to a {@code false} result.
     */
    @SneakyThrows
    @Override
    public boolean removePlayerData(UUID uuid) {
        return Files.deleteIfExists(buildPlayerDataFilePath(uuid));
    }

    /**
     * Returns whether the UUID's main data path exists on disk. This check does not
     * validate the NBT contents or treat a leftover backup file as a stored main
     * entry.
     */
    @Override
    public boolean hasPlayerData(UUID uuid) {
        return Files.exists(buildPlayerDataFilePath(uuid));
    }

    /**
     * Returns the main NBT path derived from the supplied player UUID.
     */
    protected Path buildPlayerDataFilePath(UUID uuid) {
        return dataFolderPath.resolve(uuid.toString() + ".nbt");
    }
}
