#!/usr/bin/env python3
"""Run read-only consistency checks for an Allay stable release."""

from __future__ import annotations

import argparse
from dataclasses import dataclass, field
from html import unescape
from pathlib import Path, PurePosixPath
import re
import subprocess
import sys
import tomllib
from urllib.parse import unquote
import zipfile


SEMVER_PATTERN = r"(?:0|[1-9][0-9]*)\.(?:0|[1-9][0-9]*)\.(?:0|[1-9][0-9]*)"
SEMVER_RE = re.compile(rf"^{SEMVER_PATTERN}$")
RELEASE_HEADING_RE = re.compile(
    rf"^# (?P<server>{SEMVER_PATTERN}) \(API (?P<api>{SEMVER_PATTERN})\) - "
    r"(?P<state>Unreleased|[0-9]{4}/[0-9]{1,2}/[0-9]{1,2})$"
)
CHANGELOG_CATEGORIES = {
    "### Added",
    "### Changed",
    "### Deprecated",
    "### Removed",
    "### Fixed",
    "### Security",
}


@dataclass
class Report:
    failures: list[str] = field(default_factory=list)
    passes: list[str] = field(default_factory=list)

    def check(self, condition: bool, success: str, failure: str) -> bool:
        if condition:
            self.passes.append(success)
            return True
        self.failures.append(failure)
        return False

    def fail(self, message: str) -> None:
        self.failures.append(message)

    def finish(self, summary: str) -> int:
        for message in self.passes:
            print(f"[PASS] {message}")
        for message in self.failures:
            print(f"[FAIL] {message}")
        if self.failures:
            print(f"\nPreflight failed with {len(self.failures)} issue(s).")
            return 1
        print(f"\nPreflight passed: {summary}")
        return 0


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(
        description="Validate Allay release metadata and, optionally, a stable shaded jar."
    )
    parser.add_argument("--tag", required=True, help="Stable server version/tag, for example 0.13.1")
    parser.add_argument(
        "--repo-root",
        type=Path,
        help="Allay repository root; defaults to the current Git repository",
    )
    parser.add_argument(
        "--artifact",
        type=Path,
        help="Stable shaded jar to inspect after the release build",
    )
    parser.add_argument(
        "--require-clean",
        action="store_true",
        help="Require a clean worktree and git.dirty=false in the artifact",
    )
    return parser.parse_args()


def run_git(repo_root: Path, *args: str, check: bool = True) -> subprocess.CompletedProcess[str]:
    result = subprocess.run(
        ["git", *args],
        cwd=repo_root,
        check=False,
        capture_output=True,
        text=True,
    )
    if check and result.returncode != 0:
        detail = result.stderr.strip() or result.stdout.strip() or f"exit code {result.returncode}"
        raise RuntimeError(f"git {' '.join(args)} failed: {detail}")
    return result


def resolve_repo_root(requested_root: Path | None) -> Path:
    if requested_root is not None:
        root = requested_root.resolve()
    else:
        result = subprocess.run(
            ["git", "rev-parse", "--show-toplevel"],
            check=False,
            capture_output=True,
            text=True,
        )
        if result.returncode != 0:
            raise RuntimeError("Run from an Allay Git worktree or provide --repo-root")
        root = Path(result.stdout.strip()).resolve()
    if not root.is_dir():
        raise RuntimeError(f"Repository root does not exist: {root}")
    run_git(root, "rev-parse", "--is-inside-work-tree")
    return root


def read_properties(path: Path) -> tuple[dict[str, str], list[str]]:
    properties: dict[str, str] = {}
    duplicates: list[str] = []
    for raw_line in path.read_text(encoding="utf-8").splitlines():
        line = raw_line.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        key = key.strip()
        if key in properties:
            duplicates.append(key)
        properties[key] = value.strip()
    return properties, duplicates


def semver_key(version: str) -> tuple[int, int, int]:
    return tuple(int(part) for part in version.split("."))  # type: ignore[return-value]


def find_previous_tag(repo_root: Path, release_tag: str) -> str | None:
    tags = run_git(repo_root, "tag", "--merged", "HEAD", "--list").stdout.splitlines()
    stable_tags = [tag.strip() for tag in tags if SEMVER_RE.fullmatch(tag.strip())]
    stable_tags = [tag for tag in stable_tags if tag != release_tag]
    return max(stable_tags, key=semver_key, default=None)


def validate_changelog(
    report: Report,
    changelog_path: Path,
    release_tag: str,
    api_version: str,
    previous_tag: str | None,
) -> None:
    lines = changelog_path.read_text(encoding="utf-8").splitlines()
    headings = [(index, match) for index, line in enumerate(lines) if (match := RELEASE_HEADING_RE.fullmatch(line))]
    if not headings:
        report.fail("CHANGELOG.md has no release heading in the expected format")
        return

    first_index, first_match = headings[0]
    first_server = first_match.group("server")
    first_api = first_match.group("api")
    report.check(
        first_server == release_tag,
        f"top changelog section targets server {release_tag}",
        f"top changelog section targets server {first_server}, expected {release_tag}",
    )
    report.check(
        first_api == api_version,
        f"top changelog section targets API {api_version}",
        f"top changelog section targets API {first_api}, expected {api_version}",
    )
    report.check(
        first_match.group("state") == "Unreleased",
        "top changelog section is marked Unreleased",
        "top changelog section must be marked Unreleased before the tag is pushed",
    )

    matching_headings = [match for _, match in headings if match.group("server") == release_tag]
    report.check(
        len(matching_headings) == 1,
        f"changelog contains one section for {release_tag}",
        f"changelog must contain exactly one section for {release_tag}, found {len(matching_headings)}",
    )

    section_end = headings[1][0] if len(headings) > 1 else len(lines)
    section = lines[first_index + 1 : section_end]
    first_nonblank = next((line.strip() for line in section if line.strip()), None)
    if previous_tag is None:
        report.fail("could not find a previous strict SemVer tag reachable from HEAD")
    else:
        expected_compare = (
            f"<small>[Compare with {previous_tag}]"
            f"(https://github.com/AllayMC/Allay/compare/{previous_tag}...HEAD)</small>"
        )
        report.check(
            first_nonblank == expected_compare,
            f"changelog compare link starts at {previous_tag} and ends at HEAD",
            f"first nonblank changelog line must be: {expected_compare}",
        )

    categories = {line.strip() for line in section if line.strip() in CHANGELOG_CATEGORIES}
    bullets = [line for line in section if re.match(r"^\s*[-*]\s+\S", line)]
    report.check(
        bool(categories),
        "release changelog contains a recognized category",
        "release changelog needs at least one recognized category heading",
    )
    report.check(
        bool(bullets),
        f"release changelog contains {len(bullets)} bullet(s)",
        "release changelog needs at least one concrete bullet",
    )


def protocol_version(libs_path: Path) -> str:
    with libs_path.open("rb") as stream:
        data = tomllib.load(stream)
    entry = data.get("libraries", {}).get("protocol")
    if not isinstance(entry, dict) or not isinstance(entry.get("version"), str):
        raise ValueError("gradle/libs.versions.toml has no libraries.protocol.version")
    match = re.match(rf"^({SEMVER_PATTERN})(?:-|$)", entry["version"])
    if match is None:
        raise ValueError(f"unsupported protocol dependency version: {entry['version']}")
    return match.group(1)


def validate_minecraft_badge(report: Report, readme_path: Path, libs_path: Path) -> str | None:
    readme = readme_path.read_text(encoding="utf-8")
    badge = re.search(r'minecraft-([^"/]+)-green', readme)
    if badge is None:
        report.fail("README.md has no Minecraft version badge recognized by release.yml")
        return None

    badge_text = unescape(unquote(badge.group(1))).replace("%20", " ")
    versions = re.findall(SEMVER_PATTERN, badge_text)
    if not versions:
        report.fail(f"Minecraft badge contains no strict version: {badge_text}")
        return None

    try:
        dependency_version = protocol_version(libs_path)
    except (OSError, tomllib.TOMLDecodeError, ValueError) as error:
        report.fail(str(error))
        return versions[-1]

    badge_version = versions[-1]
    report.check(
        badge_version == dependency_version,
        f"README Minecraft upper bound {badge_version} matches the protocol dependency",
        (
            f"README Minecraft upper bound is {badge_version}, but the protocol dependency targets "
            f"{dependency_version}"
        ),
    )
    return badge_text


def parse_jar_properties(content: str) -> dict[str, str]:
    properties: dict[str, str] = {}
    for raw_line in content.splitlines():
        line = raw_line.strip()
        if not line or line.startswith("#") or "=" not in line:
            continue
        key, value = line.split("=", 1)
        properties[key.strip()] = value.strip()
    return properties


def validate_artifact(
    report: Report,
    repo_root: Path,
    artifact_arg: Path,
    server_version: str,
    api_version: str,
    head: str,
    short_head: str,
    require_clean: bool,
) -> Path:
    artifact = artifact_arg if artifact_arg.is_absolute() else repo_root / artifact_arg
    artifact = artifact.resolve()
    if not report.check(
        artifact.is_file(),
        f"release artifact exists at {artifact}",
        f"release artifact does not exist: {artifact}",
    ):
        return artifact

    shaded_jars = sorted(artifact.parent.glob("allay-server-*-shaded.jar"))
    report.check(
        len(shaded_jars) == 1,
        "artifact directory contains exactly one shaded jar",
        f"expected exactly one shaded jar in {artifact.parent}, found {len(shaded_jars)}",
    )
    expected_name = f"allay-server-{server_version}-{short_head}-shaded.jar"
    report.check(
        artifact.name == expected_name,
        f"artifact name is {expected_name}",
        f"artifact name is {artifact.name}, expected {expected_name}",
    )

    try:
        with zipfile.ZipFile(artifact) as jar:
            property_entries = [
                name for name in jar.namelist() if PurePosixPath(name).name == "git.properties"
            ]
            if not report.check(
                len(property_entries) == 1,
                "artifact contains one git.properties resource",
                f"artifact must contain exactly one git.properties resource, found {len(property_entries)}",
            ):
                return artifact
            content = jar.read(property_entries[0]).decode("utf-8")
    except (OSError, UnicodeDecodeError, zipfile.BadZipFile) as error:
        report.fail(f"could not read artifact as a jar: {error}")
        return artifact

    properties = parse_jar_properties(content)
    expected_properties = {
        "git.build.version": server_version,
        "git.build.api_version": api_version,
        "git.build.is_dev_build": "false",
        "git.commit.id": head,
    }
    for key, expected in expected_properties.items():
        actual = properties.get(key)
        report.check(
            actual == expected,
            f"artifact {key}={expected}",
            f"artifact {key} is {actual!r}, expected {expected!r}",
        )
    if require_clean:
        actual_dirty = properties.get("git.dirty")
        report.check(
            actual_dirty == "false",
            "artifact records git.dirty=false",
            f"artifact git.dirty is {actual_dirty!r}, expected 'false'",
        )
    return artifact


def main() -> int:
    args = parse_args()
    report = Report()
    if not SEMVER_RE.fullmatch(args.tag):
        report.fail(f"release tag must be strict SemVer without a prefix: {args.tag}")
        return report.finish(f"tag={args.tag}")

    try:
        repo_root = resolve_repo_root(args.repo_root)
    except RuntimeError as error:
        report.fail(str(error))
        return report.finish(f"tag={args.tag}")

    required_paths = {
        "gradle.properties": repo_root / "gradle.properties",
        "CHANGELOG.md": repo_root / "CHANGELOG.md",
        "README.md": repo_root / "README.md",
        "gradle/libs.versions.toml": repo_root / "gradle" / "libs.versions.toml",
    }
    missing = [name for name, path in required_paths.items() if not path.is_file()]
    if missing:
        report.fail(f"repository is missing required file(s): {', '.join(missing)}")
        return report.finish(f"tag={args.tag}")

    properties, duplicates = read_properties(required_paths["gradle.properties"])
    report.check(
        not duplicates,
        "gradle.properties has unique keys",
        f"gradle.properties contains duplicate key(s): {', '.join(sorted(set(duplicates)))}",
    )
    api_version = properties.get("api.version", "")
    server_version = properties.get("server.version", "")
    dev_build = properties.get("allay.is-dev-build", "")
    report.check(
        bool(SEMVER_RE.fullmatch(api_version)),
        f"api.version is strict SemVer ({api_version})",
        f"api.version must be strict SemVer, found {api_version!r}",
    )
    report.check(
        bool(SEMVER_RE.fullmatch(server_version)),
        f"server.version is strict SemVer ({server_version})",
        f"server.version must be strict SemVer, found {server_version!r}",
    )
    report.check(
        server_version == args.tag,
        f"server.version matches release tag {args.tag}",
        f"server.version is {server_version!r}, expected {args.tag!r}",
    )
    report.check(
        dev_build == "true",
        "allay.is-dev-build remains true in gradle.properties",
        "allay.is-dev-build must remain true; use the Gradle property override for stable builds",
    )

    try:
        head = run_git(repo_root, "rev-parse", "HEAD").stdout.strip()
        short_head = run_git(repo_root, "rev-parse", "--short", "HEAD").stdout.strip()
        tag_exists = run_git(
            repo_root,
            "show-ref",
            "--verify",
            "--quiet",
            f"refs/tags/{args.tag}",
            check=False,
        ).returncode == 0
        report.check(
            not tag_exists,
            f"release tag {args.tag} does not exist locally",
            f"release tag {args.tag} already exists locally",
        )
        previous_tag = find_previous_tag(repo_root, args.tag)
        if previous_tag is not None:
            report.check(
                semver_key(args.tag) > semver_key(previous_tag),
                f"release tag {args.tag} is newer than {previous_tag}",
                f"release tag {args.tag} must be newer than existing stable tag {previous_tag}",
            )
    except RuntimeError as error:
        report.fail(str(error))
        return report.finish(f"tag={args.tag}, api={api_version or 'unknown'}")

    if args.require_clean:
        status = run_git(repo_root, "status", "--porcelain").stdout
        report.check(
            not status.strip(),
            "Git worktree is clean",
            "Git worktree must be clean when --require-clean is used",
        )

    validate_changelog(
        report,
        required_paths["CHANGELOG.md"],
        args.tag,
        api_version,
        previous_tag,
    )
    minecraft_range = validate_minecraft_badge(
        report,
        required_paths["README.md"],
        required_paths["gradle/libs.versions.toml"],
    )

    artifact: Path | None = None
    if args.artifact is not None:
        artifact = validate_artifact(
            report,
            repo_root,
            args.artifact,
            server_version,
            api_version,
            head,
            short_head,
            args.require_clean,
        )

    summary_parts = [
        f"tag={args.tag}",
        f"api={api_version or 'unknown'}",
        f"previous={previous_tag or 'unknown'}",
    ]
    if minecraft_range:
        summary_parts.append(f"minecraft={minecraft_range}")
    if artifact is not None:
        summary_parts.append(f"artifact={artifact.name}")
    return report.finish(", ".join(summary_parts))


if __name__ == "__main__":
    sys.exit(main())
