# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

Each release is associated with a specific API version,
and any changes to API will have a prefix `(API)`.

Unless otherwise specified, any version comparison below is the comparison of server version, not API version.

## 0.1.1 (API 0.2.0) - Unreleased

<small>[Compare with 0.1.0](https://github.com/AllayMC/Allay/compare/0.1.0...HEAD)</small>

### Added

- (API) Added `TextFormat#MATERIAL_RESIN`.
- (API) Entity#teleport method now accepts an extra `Reason` argument.
- (API) Added structure API by @harry-xi.
- Added `/structure` command to manage structures.

### Changed

- Breaking block related warnings are now moved to debug channel.
- Improved code readability for I18n module.
- NBT library is now updated to 3.0.10.
- `/gametest` command is now only available in dev build.

### Fixed

- (API) Fixed exception when setting item count or meta to zero.
- Passing non-positive amount or negative meta arguments to `/give` command now will result in a syntax error.
- Entity#teleport method now will reset fall distance correctly.
- Fixed visual flashes when eating chorus fruits.
- Fixed incorrect comparison of `Position3x#dimension`.
- Fixed a number of falling block related bugs.

## [0.1.0](https://github.com/AllayMC/Allay/releases/tag/0.1.0) (API 0.1.0) - 2024-12-22

Hello Allay! This is the first release of Allay.
