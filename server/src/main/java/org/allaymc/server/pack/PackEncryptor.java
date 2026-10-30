package org.allaymc.server.pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.Strictness;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.pack.PackManifest;
import org.apache.commons.lang3.RandomStringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * {@code PackEncryptor} writes encrypted Bedrock pack archives and their content
 * indexes. Each encrypted file receives an independent key, while the pack key
 * encrypts the index that associates paths with those file keys. Root manifest
 * and icon files remain unencrypted and have {@code null} keys in that index.
 *
 * <p>
 * Subpack indexes use paths relative to their explicit {@code subpacks/name/}
 * directory entries; root processing skips all subpack files to avoid writing
 * them twice. The writer replaces the destination directly and closes its
 * output only on normal completion. Public entry points log caught exceptions
 * without reporting failure to callers, so a returned key does not establish
 * that a complete output archive exists.
 *
 * @author daoge_cmd
 */
@Slf4j
public final class PackEncryptor {
    /**
     * {@code GSON} writes content indexes with explicit {@code null} entry keys
     * for root files that remain outside encryption.
     */
    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls()
            .setStrictness(Strictness.LENIENT)
            .create();
    /**
     * {@code KEY_LENGTH} controls generated alphabetic keys and the character
     * count accepted before constructing the AES content-index cipher.
     */
    private static final int KEY_LENGTH = 32;
    // VERSION and MAGIC form the cleartext prefix of each content index. The
    // encrypted JSON begins after the padded header and its pack UUID bytes.
    /** Content-index format version bytes. */
    private static final byte[] VERSION = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
    /** Content-index format signature bytes. */
    private static final byte[] MAGIC = new byte[]{(byte) 0xFC, (byte) 0xB9, (byte) 0xCF, (byte) 0x9B};
    /**
     * {@code EXCLUDED_FILES} names root files copied without encryption and
     * recorded with {@code null} keys so clients can identify cleartext entries.
     */
    private static final List<String> EXCLUDED_FILES = List.of(PackManifest.FILE_NAME, "pack_icon.png", "bug_pack_icon.png");

    /**
     * Attempts pack encryption using a new alphabetic key and returns that key.
     * Caught encryption or archive-close exceptions are logged without changing
     * the return value, so callers must verify output separately. Source and
     * destination must be distinct because writing deletes the destination.
     */
    public static String encrypt(Path inputPath, Path outputPath) {
        var key = RandomStringUtils.secure().nextAlphabetic(KEY_LENGTH);
        try (var inputZip = new ZipFile(inputPath.toString())) {
            encrypt0(inputZip, outputPath, key);
        } catch (Exception e) {
            // The API returns its generated key even after failure; retain the
            // exception in logs because callers receive no success indicator.
            log.error("Failed to encrypt pack", e);
        }
        return key;
    }

    /**
     * Attempts pack encryption using the supplied content-index key.
     * The writer checks for {@code 32} characters, but AES also requires suitable
     * UTF-8 key and initialization-vector byte lengths. Caught failures are
     * logged; callers must use distinct paths and verify the output separately.
     */
    public static void encrypt(Path inputPath, Path outputPath, String key) {
        try (var inputZip = new ZipFile(inputPath.toString())) {
            encrypt0(inputZip, outputPath, key);
        } catch (Exception e) {
            // Supplied-key callers receive no failure result, so preserve the
            // exception in logs even when destination writing has already begun.
            log.error("Failed to encrypt pack", e);
        }
    }

    /**
     * Writes an encrypted archive and root content index from an open source ZIP.
     * The destination is deleted before output creation, so a later failure can
     * leave partial output and an unclosed output stream.
     *
     * <p>
     * Explicit subpack root entries trigger a separate index with relative paths.
     * Root processing skips every subpack file because that pass owns them;
     * files without a matching directory entry are consequently omitted. Excluded
     * root entries remain cleartext, and their {@code null} keys distinguish them
     * from encrypted entries in the final index. The caller retains the source ZIP.
     */
    @SneakyThrows
    private static void encrypt0(ZipFile inputZip, Path outputPath, String key) {
        if (key.length() != KEY_LENGTH) {
            throw new IllegalArgumentException("key length must be 32");
        }

        var uuid = findPackUUID(inputZip);

        var contentEntries = new ArrayList<ContentEntry>();

        // Replace the destination directly rather than stage a new archive;
        // failures after deletion do not restore its earlier contents.
        Files.deleteIfExists(outputPath);
        var outputStream = new ZipOutputStream(new FileOutputStream(outputPath.toFile()), StandardCharsets.UTF_8);
        // Subpack roots own their files and indexes; the root pass skips those
        // files so archive entries are not written through both traversal paths.
        inputZip.stream().forEach(zipEntry -> {
            if (zipEntry.isDirectory()) {
                createDirectoryRoot(zipEntry, outputStream);
                if (isSubPackRoot(zipEntry)) {
                    encryptSubPack(inputZip, outputStream, zipEntry.getName(), key, uuid);
                }

                return;
            }
            if (isSubPackFile(zipEntry)) {
                return;
            }

            String entryKey = null;
            if (EXCLUDED_FILES.contains(zipEntry.getName())) {
                encryptExcludedFile(inputZip, outputStream, zipEntry);
            } else {
                entryKey = encryptFile(inputZip, outputStream, zipEntry);
            }
            contentEntries.add(new ContentEntry(zipEntry.getName(), entryKey));
        });

        generateContentsJson("contents.json", outputStream, uuid, key, contentEntries);
        // Finalize the ZIP only after all index entries are written. Earlier
        // failures bypass this close because the writer has no finally block.
        outputStream.close();
    }

    /**
     * Encrypts files below one explicit subpack root and writes its local index.
     * The caller supplies a directory prefix ending in a slash and keeps both
     * archives open for the entire operation.
     *
     * <p>
     * Archive entries retain their full paths, but index entries remove
     * {@code subPackPath} so the client resolves them relative to that subpack.
     * This pass encrypts every matching file, including names excluded only at
     * the root level. The generated subpack index uses the pack-wide content ID
     * and key while retaining independent keys for its encrypted files.
     */
    @SneakyThrows
    private static void encryptSubPack(ZipFile inputZip, ZipOutputStream zos, String subPackPath, String key, String contentId) {
        var subPackContentEntries = new ArrayList<ContentEntry>();

        // Preserve full archive paths while stripping the root from index
        // paths, because each subpack index resolves its own local file names.
        inputZip.stream().forEach(zipEntry -> {
            if (zipEntry.isDirectory() || !zipEntry.getName().startsWith(subPackPath)) {
                return;
            }

            String entryKey = encryptFile(inputZip, zos, zipEntry);
            subPackContentEntries.add(new ContentEntry(zipEntry.getName().substring(subPackPath.length()), entryKey));
        });

        generateContentsJson(subPackPath + "contents.json", zos, contentId, key, subPackContentEntries);
    }

    /**
     * Writes a content-index entry with a cleartext header and encrypted JSON.
     * The caller supplies an open output ZIP, a UTF-8-compatible AES key, and
     * content paths relative to the index's root.
     *
     * <p>
     * Version and magic bytes precede padding to offset {@code 0x10}, followed
     * by the one-byte UUID length and its bytes. JSON starts at offset
     * {@code 0x100} and uses {@code AES/CFB8/NoPadding} with the first sixteen
     * characters of {@code key} as its initialization vector. The cleartext identity remains
     * separate from the encrypted path-to-key associations clients consume.
     */
    @SneakyThrows
    private static void generateContentsJson(String name, ZipOutputStream outputStream, String contentId, String key, ArrayList<ContentEntry> contentEntries) {
        outputStream.putNextEntry(new ZipEntry(name));
        try (var stream = new ByteArrayOutputStream()) {
            stream.write(VERSION);
            stream.write(MAGIC);
            // The UUID header starts at the fixed format offset after version and magic.
            paddingTo(stream, 0x10);
            var contentIdBytes = contentId.getBytes(StandardCharsets.UTF_8);
            stream.write(contentIdBytes.length);
            stream.write(contentIdBytes);
            // The index cipher derives its IV from the pack key so clients
            // can decrypt the path-to-key table using that shared key alone.
            var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            var cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(key.substring(0, 16).getBytes(StandardCharsets.UTF_8)));
            var contentJson = GSON.toJson(new Content(contentEntries));
            // Encrypted index bytes begin at the fixed payload offset of 256 bytes.
            paddingTo(stream, 0x100);
            stream.write(cipher.doFinal(contentJson.getBytes(StandardCharsets.UTF_8)));
            outputStream.write(stream.toByteArray());
        }
        outputStream.closeEntry();
    }

    /**
     * Copies an excluded root file into the output ZIP without encryption.
     * The caller records a {@code null} key for this file and retains ownership
     * of both archives; this helper does not separately close the entry's input
     * stream.
     */
    @SneakyThrows
    private static void encryptExcludedFile(ZipFile inputZip, ZipOutputStream outputStream, ZipEntry zipEntry) {
        outputStream.putNextEntry(copyZipEntry(zipEntry));
        outputStream.write(inputZip.getInputStream(zipEntry).readAllBytes());
        outputStream.closeEntry();
    }

    /**
     * Encrypts one archive file with a fresh alphabetic AES key and returns it.
     * The output entry retains source metadata with a deflated compression method.
     * The caller owns both archives, and this helper leaves its input entry
     * stream for archive cleanup rather than closing it separately.
     */
    @SneakyThrows
    private static String encryptFile(ZipFile inputZip, ZipOutputStream outputStream, ZipEntry zipEntry) {
        byte[] bytes;
        bytes = inputZip.getInputStream(zipEntry).readAllBytes();
        // Each file gets an independent key; the content index records that key
        // so clients can decrypt the file after reading its enclosing index.
        var key = RandomStringUtils.secure().nextAlphabetic(KEY_LENGTH);
        var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        var cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(key.substring(0, 16).getBytes(StandardCharsets.UTF_8)));
        var encryptedBytes = cipher.doFinal(bytes);
        outputStream.putNextEntry(copyZipEntry(zipEntry));
        outputStream.write(encryptedBytes);
        outputStream.closeEntry();
        return key;
    }

    /**
     * Returns whether the entry path belongs beneath the subpack namespace.
     */
    private static boolean isSubPackFile(ZipEntry zipEntry) {
        return zipEntry.getName().startsWith("subpacks/");
    }

    /**
     * Returns whether the entry explicitly names a direct subpack directory.
     * Only directory entries shaped like {@code subpacks/name/} qualify, because
     * root traversal uses them to start subpack encryption and index generation.
     */
    private static boolean isSubPackRoot(ZipEntry zipEntry) {
        // Two separators restrict processing to direct subpack roots.
        return zipEntry.isDirectory() && zipEntry.getName().startsWith("subpacks/") && calculateCharCount(zipEntry.getName(), '/') == 2;
    }

    /**
     * Counts occurrences of the target character in the supplied string.
     */
    private static int calculateCharCount(String str, char target) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == target) {
                count++;
            }
        }
        return count;
    }

    /**
     * Pads the buffer with zero bytes until it reaches an absolute offset.
     * {@code pos} must exceed the current size; equal or earlier offsets throw
     * {@code IllegalArgumentException} instead of truncating existing header bytes.
     */
    private static void paddingTo(ByteArrayOutputStream stream, int pos) {
        if (pos <= stream.size()) {
            throw new IllegalArgumentException("pos must be bigger than stream size");
        }

        var need = pos - stream.size();
        for (int i = 0; i < need; i++) {
            stream.write(0);
        }
    }

    /**
     * Copies ZIP metadata while selecting deflated output for rewritten file bytes.
     * Encrypted bytes differ from the source payload, so the output writer must
     * calculate their size and checksum rather than require stored-entry metadata.
     */
    private static ZipEntry copyZipEntry(ZipEntry entry) {
        var newEntry = new ZipEntry(entry);
        // Rewritten payloads no longer match stored-entry size and CRC metadata;
        // deflated output lets the ZIP writer calculate their resulting values.
        newEntry.setMethod(ZipEntry.DEFLATED);
        return newEntry;
    }

    /**
     * Reads the manifest header UUID used as the encrypted content identity.
     * A missing manifest throws {@code IllegalArgumentException}; parsing and
     * missing-header failures propagate without a separate manifest-validity check.
     * The temporary manifest reader closes before this helper returns.
     */
    @SneakyThrows
    private static String findPackUUID(ZipFile zip) {
        var manifestEntry = zip.getEntry(PackManifest.FILE_NAME);
        if (manifestEntry == null) {
            throw new IllegalArgumentException("manifest file not exists");
        }

        try (var reader = new InputStreamReader(zip.getInputStream(manifestEntry), StandardCharsets.UTF_8)) {
            var manifest = PackManifest.fromJson(reader);
            return manifest.getHeader().getUuid().toString();
        }
    }

    /**
     * Writes an empty directory entry using copied metadata and deflated output.
     * The caller keeps the output archive open so later encrypted files and
     * content indexes can follow this directory entry.
     */
    @SneakyThrows
    private static void createDirectoryRoot(ZipEntry zipEntry, ZipOutputStream outputStream) {
        outputStream.putNextEntry(copyZipEntry(zipEntry));
        outputStream.closeEntry();
    }

    /**
     * {@code Content} represents the file-key table serialized into an encrypted index.
     *
     * @param content file-key entries retained without copying; callers share
     *                list changes until serialization reads the current contents
     */
    public record Content(List<ContentEntry> content) {
    }

    /**
     * {@code ContentEntry} associates an indexed pack path with its decryption key.
     *
     * @param path index-relative file path
     * @param key file decryption key; {@code null} identifies an excluded root
     *            file whose archive bytes remain unencrypted
     */
    public record ContentEntry(String path, String key) {
    }
}
