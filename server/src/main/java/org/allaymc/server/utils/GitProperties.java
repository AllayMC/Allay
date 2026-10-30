package org.allaymc.server.utils;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.allaymc.server.Allay;

import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Properties;

/**
 * {@code GitProperties} exposes build-time Git metadata packaged in the extra resource loader.
 * Class initialization reads {@code "git.properties"} once; string accessors return the
 * stored text unchanged, including {@code null} for absent keys, while development-build
 * detection expects its boolean marker to exist.
 *
 * @author daoge_cmd
 */
@UtilityClass
public final class GitProperties {

    /**
     * {@code GIT_PROPERTIES} holds the metadata loaded during class initialization. Accessors
     * reuse
     * this same property set rather than reopening the resource for each build-information
     * request.
     */
    private static final Properties GIT_PROPERTIES = init();

    /**
     * Loads build metadata from {@code Allay.EXTRA_RESOURCE_CLASS_LOADER}. The resource must exist
     * when the class initializes; missing data or reader failures abort initialization rather than
     * returning a substitute empty property set.
     */
    @SneakyThrows
    private static Properties init() {
        var properties = new Properties();
        try (var inputStreamReader = new InputStreamReader(Objects.requireNonNull(Allay.EXTRA_RESOURCE_CLASS_LOADER.getResourceAsStream("git.properties")))) {
            properties.load(inputStreamReader);
        }
        return properties;
    }

    /**
     * Returns the source branch name from the packaged build metadata.
     */
    public static String getBranch() {
        return GIT_PROPERTIES.getProperty("git.branch");
    }

    /**
     * Returns the host recorded when the build ran from the packaged build metadata.
     */
    public static String getBuildHost() {
        return GIT_PROPERTIES.getProperty("git.build.host");
    }

    /**
     * Returns the build user email from the packaged build metadata.
     */
    public static String getBuildUserEmail() {
        return GIT_PROPERTIES.getProperty("git.build.user.email");
    }

    /**
     * Returns the build user name from the packaged build metadata.
     */
    public static String getBuildUserName() {
        return GIT_PROPERTIES.getProperty("git.build.user.name");
    }

    /**
     * Returns the server artifact version from the packaged build metadata.
     */
    public static String getBuildVersion() {
        return GIT_PROPERTIES.getProperty("git.build.version");
    }

    /**
     * Returns the API artifact version from the packaged build metadata.
     */
    public static String getBuildApiVersion() {
        return GIT_PROPERTIES.getProperty("git.build.api_version");
    }

    /**
     * Returns the commit count from the closest tag from the packaged build metadata.
     */
    public static String getClosestTagCommitCount() {
        return GIT_PROPERTIES.getProperty("git.closest.tag.commit.count");
    }

    /**
     * Returns the closest source tag name from the packaged build metadata.
     */
    public static String getClosestTagName() {
        return GIT_PROPERTIES.getProperty("git.closest.tag.name");
    }

    /**
     * Returns the full source commit identifier from the packaged build metadata.
     */
    public static String getCommitId() {
        return GIT_PROPERTIES.getProperty("git.commit.id");
    }

    /**
     * Returns the abbreviated source commit identifier from the packaged build metadata.
     */
    public static String getCommitIdAbbrev() {
        return GIT_PROPERTIES.getProperty("git.commit.id.abbrev");
    }

    /**
     * Returns the described source commit identifier from the packaged build metadata.
     */
    public static String getCommitIdDescribe() {
        return GIT_PROPERTIES.getProperty("git.commit.id.describe");
    }

    /**
     * Returns the full source commit message from the packaged build metadata.
     */
    public static String getCommitMessageFull() {
        return GIT_PROPERTIES.getProperty("git.commit.message.full");
    }

    /**
     * Returns the short source commit message from the packaged build metadata.
     */
    public static String getCommitMessageShort() {
        return GIT_PROPERTIES.getProperty("git.commit.message.short");
    }

    /**
     * Returns the recorded source commit timestamp from the packaged build metadata.
     */
    public static String getCommitTime() {
        return GIT_PROPERTIES.getProperty("git.commit.time");
    }

    /**
     * Returns the source commit author email from the packaged build metadata.
     */
    public static String getCommitUserEmail() {
        return GIT_PROPERTIES.getProperty("git.commit.user.email");
    }

    /**
     * Returns the source commit author name from the packaged build metadata.
     */
    public static String getCommitUserName() {
        return GIT_PROPERTIES.getProperty("git.commit.user.name");
    }

    /**
     * Returns the recorded working-tree dirty marker from the packaged build metadata.
     */
    public static String getDirty() {
        return GIT_PROPERTIES.getProperty("git.dirty");
    }

    /**
     * Returns the recorded origin remote URL from the packaged build metadata.
     */
    public static String getRemoteOriginUrl() {
        return GIT_PROPERTIES.getProperty("git.remote.origin.url");
    }

    /**
     * Returns the recorded source tag list from the packaged build metadata.
     */
    public static String getTags() {
        return GIT_PROPERTIES.getProperty("git.tags");
    }

    /**
     * Returns the recorded total source commit count from the packaged build metadata.
     */
    public static String getTotalCommitCount() {
        return GIT_PROPERTIES.getProperty("git.total.commit.count");
    }

    /**
     * Returns whether the packaged development-build marker equals {@code "true"} exactly.
     * Other spellings produce {@code false}, while an absent marker fails with
     * {@code NullPointerException} instead of selecting a boolean default.
     */
    public static boolean isDevBuild() {
        return GIT_PROPERTIES.getProperty("git.build.is_dev_build").equals("true");
    }
}
