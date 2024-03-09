package org.allaymc.server.pack;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.pack.PackManifest;
import org.allaymc.api.utils.JSONUtils;
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
 * Allay Project 2024/2/7
 *
 * @author daoge_cmd
 *
 * An util for encrypting pack
 */
@Slf4j
public final class PackEncryptor {
    private static final Gson GSON = new GsonBuilder()
            .disableHtmlEscaping()
            .serializeNulls() // NOTICE: Null should be serialized!
            .setLenient()
            .create();
    private static final int KEY_LENGTH = 32;
    private static final byte[] VERSION = new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00};
    private static final byte[] MAGIC = new byte[]{(byte) 0xFC, (byte) 0xB9, (byte) 0xCF, (byte) 0x9B};
    private static final List<String> EXCLUDED_FILES = List.of("manifest.json", "pack_icon.png", "bug_pack_icon.png");

    public static String encrypt(Path inputPath, Path outputPath) {
        var key = RandomStringUtils.randomAlphanumeric(KEY_LENGTH);
        try (var inputZip = new ZipFile(inputPath.toString())) {
            encrypt0(inputZip, outputPath, key);
        } catch (Exception e) {
            log.error("Failed to encrypt pack", e);
        }
        return key;
    }

    public static void encrypt(Path inputPath, Path outputPath, String key) {
        try (var inputZip = new ZipFile(inputPath.toString())) {
            encrypt0(inputZip, outputPath, key);
        } catch (Exception e) {
            log.error("Failed to encrypt pack", e);
        }
    }

    @SneakyThrows
    private static void encrypt0(ZipFile inputZip, Path outputPath, String key) {
        if (key.length() != KEY_LENGTH)
            throw new IllegalArgumentException("key length must be 32");
        // Find content id
        var uuid = findPackUUID(inputZip);

        var contentEntries = new ArrayList<ContentEntry>();

        // Delete old output
        Files.deleteIfExists(outputPath);
        var outputStream = new ZipOutputStream(new FileOutputStream(outputPath.toFile()), StandardCharsets.UTF_8);
        // Encrypt files
        inputZip.stream().forEach(zipEntry -> {
            if (zipEntry.isDirectory()) {
                createDirectoryRoot(zipEntry, outputStream);
                if (isSubPackRoot(zipEntry)) {
                    // Handle sub pack
                    encryptSubPack(inputZip, outputStream, zipEntry.getName(), key, uuid);
                }
                return;
            }
            // Sub pack files will be handled in encryptSubPack()
            if (isSubPackFile(zipEntry)) return;
            String entryKey = null;
            // Check if file is excluded
            if (EXCLUDED_FILES.contains(zipEntry.getName())) {
                encryptExcludedFile(inputZip, outputStream, zipEntry);
                // Excluded file does not have entry key
            } else {
                // Encrypt file
                entryKey = encryptFile(inputZip, outputStream, zipEntry);
            }
            contentEntries.add(new ContentEntry(zipEntry.getName(), entryKey));
        });

        generateContentsJson("contents.json", outputStream, uuid, key, contentEntries);
        outputStream.close();
    }

    @SneakyThrows
    private static void encryptSubPack(ZipFile inputZip, ZipOutputStream zos, String subPackPath, String key, String contentId) {
        var subPackContentEntries = new ArrayList<ContentEntry>();

        // Encrypt files
        inputZip.stream().forEach(zipEntry -> {
            if (zipEntry.isDirectory()) return;
            if (!zipEntry.getName().startsWith(subPackPath)) return;
            String entryKey = encryptFile(inputZip, zos, zipEntry);
            subPackContentEntries.add(new ContentEntry(zipEntry.getName().substring(subPackPath.length()), entryKey));
        });

        generateContentsJson(subPackPath + "contents.json", zos, contentId, key, subPackContentEntries);
    }

    @SneakyThrows
    private static void generateContentsJson(String name, ZipOutputStream outputStream, String contentId, String key, ArrayList<ContentEntry> contentEntries) {
        outputStream.putNextEntry(new ZipEntry(name));
        try (var stream = new ByteArrayOutputStream()) {
            stream.write(VERSION);
            stream.write(MAGIC);
            paddingTo(stream, 0x10);
            var contentIdBytes = contentId.getBytes(StandardCharsets.UTF_8);
            // Write content id length
            stream.write(contentIdBytes.length);
            // Write content id
            stream.write(contentIdBytes);
            // Init contents.json encryptor
            var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            var cipher = Cipher.getInstance("AES/CFB8/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(key.substring(0, 16).getBytes(StandardCharsets.UTF_8)));
            // Write contents.json
            var contentJson = GSON.toJson(new Content(contentEntries));
            paddingTo(stream, 0x100);
            stream.write(cipher.doFinal(contentJson.getBytes(StandardCharsets.UTF_8)));
            outputStream.write(stream.toByteArray());
        }
        outputStream.closeEntry();
    }

    @SneakyThrows
    private static void encryptExcludedFile(ZipFile inputZip, ZipOutputStream outputStream, ZipEntry zipEntry) {
        outputStream.putNextEntry((ZipEntry) zipEntry.clone());
        outputStream.write(inputZip.getInputStream(zipEntry).readAllBytes());
        outputStream.closeEntry();
    }

    @SneakyThrows
    private static String encryptFile(ZipFile inputZip, ZipOutputStream outputStream, ZipEntry zipEntry) {
        byte[] bytes;
        bytes = inputZip.getInputStream(zipEntry).readAllBytes();
        // Init encryptor
        var key = RandomStringUtils.randomAlphanumeric(KEY_LENGTH);
        var secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
        var cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(key.substring(0, 16).getBytes(StandardCharsets.UTF_8)));
        // Encrypt the file
        var encryptedBytes = cipher.doFinal(bytes);
        // Write bytes
        outputStream.putNextEntry((ZipEntry) zipEntry.clone());
        outputStream.write(encryptedBytes);
        outputStream.closeEntry();
        return key;
    }

    private static boolean isSubPackFile(ZipEntry zipEntry) {
        return zipEntry.getName().startsWith("subpacks/");
    }

    private static boolean isSubPackRoot(ZipEntry zipEntry) {
        return zipEntry.isDirectory() && zipEntry.getName().startsWith("subpacks/") && calCharCount(zipEntry.getName(), '/') == 2;
    }

    private static int calCharCount(String str, char target) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == target) count++;
        }
        return count;
    }

    private static void paddingTo(ByteArrayOutputStream stream, int pos) {
        if (pos <= stream.size()) throw new IllegalArgumentException("pos must be bigger than stream size");
        var need = pos - stream.size();
        for (int i = 0; i < need; i++) {
            stream.write(0);
        }
    }

    @SneakyThrows
    private static String findPackUUID(ZipFile zip) {
        var manifestEntry = zip.getEntry("manifest.json");
        if (manifestEntry == null) throw new IllegalArgumentException("manifest file not exists");
        var manifest = JSONUtils.from(new InputStreamReader(zip.getInputStream(manifestEntry), StandardCharsets.UTF_8), PackManifest.class);
        return manifest.getHeader().getUuid().toString();
    }

    @SneakyThrows
    private static void createDirectoryRoot(ZipEntry zipEntry, ZipOutputStream outputStream) {
        outputStream.putNextEntry((ZipEntry) zipEntry.clone());
        outputStream.closeEntry();
    }

    public record Content(List<ContentEntry> content) {}

    public record ContentEntry(String path, String key) {}
}
