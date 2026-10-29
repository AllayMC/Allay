---
name: release-allay
description: Prepare, validate, publish, and close out stable Allay releases. Use when Codex needs to prepare an Allay release commit or tag, validate API and server versions or release metadata, publish a stable version through GitHub Actions, monitor release results, recover a failed release, or advance the repository to its next development versions.
---

# Release Allay

Follow the current repository workflow from preparation through post-release cleanup. Treat
`.github/workflows/release.yml`, `gradle.properties`, `CHANGELOG.md`, `README.md`, and
`docs/development/version-of-allay.md` as the sources of truth. Re-read them before every release;
do not reconstruct the procedure from old release commits alone.

## Preserve release safety

- Preserve unrelated user changes. Start release preparation only from a clean worktree; never
  stash, discard, amend, or commit changes that do not belong to the release.
- Keep `allay.is-dev-build=true` in `gradle.properties`. Produce stable artifacts with the Gradle
  override `-Pallay.is-dev-build=false` used by the current release workflow.
- Require the release commit to be on `master`, based on the current `origin/master`, before
  publishing. Never force-push master or a stable tag.
- Use the repository's lightweight tag convention: name the tag exactly as the server version,
  such as `0.13.1`.
- Stop before every remote write until the user explicitly confirms the publication summary.
  Treat a push to master as a publication action because it updates snapshots, the nightly tag and
  asset, and the remote test server through `.github/workflows/gradle.yml`.
- Never move or delete a published stable tag automatically. Require separate explicit approval for
  any recovery that would rewrite a remote ref.

## 1. Collect release inputs

1. Read `api.version` and `server.version` from `gradle.properties`. Use them as the release
   candidates unless the user supplied different versions.
2. Require the user to provide both the next API version and the next server version before any
   publication. Do not infer the next API version. Require the next server version to be greater
   than the release version and the next API version to be equal to or greater than the released API
   version.
3. Apply the policy in `docs/development/version-of-allay.md` when checking requested versions.
   Treat API and server versions independently; account for `@MinecraftVersionSensitive` exclusions
   and protocol-driven server minor releases.
4. Verify Git, Python 3.11 or newer, JDK 21, the Gradle wrapper, and GitHub CLI. Run `gh auth status`
   before presenting the publication gate.
5. Query Maven Central for both target coordinates before publishing. Require
   `org.allaymc.allay:api:<api-version>` and `org.allaymc.allay:server:<server-version>` to be absent.
   The current workflow publishes both modules unconditionally; if an unchanged API version already
   exists, stop and resolve the workflow/version choice before creating the tag.
6. Fetch `origin/master` and all tags. Require a clean worktree, branch `master`, and no divergence
   from `origin/master`. If the remote advances later, reconcile it and repeat all preflight checks.

## 2. Prepare release metadata

1. Find the highest reachable strict SemVer tag and inspect `git log`, `git diff`, and API changes
   from that tag through `HEAD`.
2. Reconcile the top `CHANGELOG.md` section with those changes. Use this exact heading shape:
   `# <server-version> (API <api-version>) - Unreleased`.
3. Keep the compare link directly below the heading in the form
   `https://github.com/AllayMC/Allay/compare/<previous-tag>...HEAD`.
4. Use the existing Added, Changed, Deprecated, Removed, Fixed, and Security categories. Include at
   least one concrete bullet, prefix public API changes with `(API)`, and omit internal chores that
   do not affect release users.
5. Compare the latest Bedrock protocol dependency in `gradle/libs.versions.toml` with the Minecraft
   badge in `README.md`. Update the badge and changelog when supported versions changed. Remember
   that the release workflow derives its Minecraft range from this badge.
6. Update `api.version` and `server.version` if needed, but leave `allay.is-dev-build=true`.
7. Run the static preflight from the repository root:

   ```text
   python .agents/skills/release-allay/scripts/preflight_release.py --tag <server-version>
   ```

8. Resolve every failure and inspect `git diff --check`. Commit only the release preparation files
   with `chore: release <server-version> (API <api-version>)`. Do not create an empty release commit
   when the existing clean `HEAD` already contains valid metadata.

## 3. Build the exact release commit

1. Require a clean worktree after the local release commit.
2. Run the same stable build semantics as CI, adding `clean` to remove stale local jars:

   ```text
   ./gradlew -Pallay.is-dev-build=false clean build server:shadowJar
   ```

   Use `gradlew.bat` on Windows.
3. Find the single `server/build/libs/allay-server-*-shaded.jar` and run the artifact preflight:

   ```text
   python .agents/skills/release-allay/scripts/preflight_release.py \
     --tag <server-version> \
     --artifact <shaded-jar-path> \
     --require-clean
   ```

   Use a single line or PowerShell backticks instead of backslash continuation on Windows.

4. Require the jar name to contain the release server version and current short commit hash. Require
   its embedded `git.properties` to contain the stable API/server versions, the full `HEAD` commit,
   `git.build.is_dev_build=false`, and `git.dirty=false`.

## 4. Obtain publication confirmation

Show the user all of the following in one concise summary, then wait for an explicit confirmation:

- release server and API versions;
- next development server and API versions;
- previous tag and exact release commit;
- supported Minecraft range and changelog summary;
- local build and preflight results;
- master-push effects: snapshot publication, nightly update, and test-server restart;
- tag-push effects: stable API/server publication to Maven Central and GitHub Release creation.

Do not interpret approval to prepare files as approval to push. If the user requested preparation
only, stop here and hand off the local commit and verification results.

## 5. Publish and monitor

1. After confirmation, fetch `origin/master` and tags again. Require the fetched `origin/master` to
   equal the parent state used for the local release commit. If it advanced, integrate the change,
   rebuild, rerun preflight, and obtain a refreshed confirmation.
2. Push the release commit to master without force. Locate the matching `gradle.yml` run by release
   commit SHA and wait for it to succeed before creating the stable tag.
3. Confirm the target tag is absent locally and remotely. Create a lightweight tag on the verified
   release SHA and push only that tag.
4. Locate the `release.yml` run by tag and release SHA, then wait with `gh run watch <run-id>
   --exit-status`. Do not rely on the most recent unrelated workflow run.
5. Inspect failures with `gh run view <run-id> --log-failed` and record the last successful
   publication step before choosing a recovery.

## 6. Verify external results

1. Inspect `gh release view <tag> --json name,tagName,isDraft,isPrerelease,assets,url`. Require the
   expected title, a published non-prerelease release, and exactly the expected shaded jar asset.
2. Verify both Maven Central coordinates: `org.allaymc.allay:api:<api-version>` and
   `org.allaymc.allay:server:<server-version>`. Allow for repository propagation delay, but report a
   timeout instead of claiming success early.
3. Confirm the stable tag still resolves to the verified release SHA.

## 7. Start the next development cycle

Proceed only after the stable release and both Maven publications are verified.

1. Replace `Unreleased` on the released section with the release date in the workflow's
   `Asia/Shanghai` timezone and `YYYY/M/D` format. Replace its compare-link endpoint `HEAD` with the
   stable tag.
2. Insert a new top section using the explicitly supplied next versions:

   ```text
   # <next-server-version> (API <next-api-version>) - Unreleased

   <small>[Compare with <released-tag>](https://github.com/AllayMC/Allay/compare/<released-tag>...HEAD)</small>
   ```

3. Set the next versions in `gradle.properties` and keep `allay.is-dev-build=true`.
4. Run the static preflight for the new candidate only as a structural check; expect it to reject an
   empty changelog until the first user-facing change is recorded. Review the files manually instead
   of weakening the release-ready checks.
5. Commit with `chore: start <next-server-version> development (API <next-api-version>)`, push master,
   and monitor its normal build. Report post-release push failures separately; they do not undo the
   completed stable release.

## Recover without duplicating publication

- Before retrying a failed release workflow, determine whether neither, one, or both Maven modules
  were accepted. The workflow publishes API and server in separate commands.
- Rerun the complete workflow only when neither stable coordinate was published and the tag contents
  remain correct.
- If Maven publication completed but GitHub Release creation failed, download the workflow artifact
  and create or update only the GitHub Release. Do not republish Maven coordinates.
- If exactly one Maven module was published, publish only the missing module after explicit
  maintainer approval, then finish the GitHub Release.
- If source contents or versions are wrong, stop and report the immutable artifacts already exposed.
  Do not rewrite the tag or version history automatically.

## Report completion

Report the release tag and SHA, GitHub Release URL, Maven coordinates, artifact name, workflow run
URLs, next development versions, and any propagation or recovery caveats.
