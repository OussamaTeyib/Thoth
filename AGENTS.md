# AGENTS.md

> Guidance for AI agents (e.g. Copilot, Antigravity, Cursor, Claude) working in this repository.

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [Repository Layout](#2-repository-layout)
3. [Build System](#3-build-system)
4. [Coding Standards](#4-coding-standards)
5. [CI/CD Pipeline](#5-cicd-pipeline)
6. [Important Constraints](#6-important-constraints)

---

## 1. Project Overview

**Thoth** is a is a note-taking app for Android.

| Property       | Value                                        |
|----------------|----------------------------------------------|
| Language       | Kotlin                                       |
| Build system   | Gradle                                       |
| Android SDK    | `compileSdk` 37, `minSdk` 24, `targetSdk` 37 |
| Application ID | `com.oussamateyib.thoth`                     |
| Version        | 1.0.0 (versionCode 1)                        |
| License        | MIT                                          |

---

## 2. Repository Layout

```
Thoth/
├── .github/
│   ├── ISSUE_TEMPLATE/        # Bug report & feature request templates
│   ├── workflows/
│   │   ├── build.yml          # CI: build, lint, and upload artifacts
│   │   ├── release.yml        # CD: create GitHub releases
│   │   └── dependency-submission.yml
│   ├── CODEOWNERS
│   ├── CODE_OF_CONDUCT.md
│   ├── CONTRIBUTING.md
│   ├── pull_request_template.md
│   └── SECURITY.md
├── app/
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── kotlin/             # Application code
│       └── res/                # Android resources (icons, strings, XML rules)
├── gradle/                     # Gradle wrapper and version catalog
├── build.gradle.kts            # Root Gradle build script
├── settings.gradle.kts         # Project name and module declarations
├── gradle.properties           # JVM args, caching, and Android flags
├── renovate.json               # Dependency update automation
├── .gitignore
├── .gitattributes
├── gradlew / gradlew.bat       # Gradle wrapper scripts
├── LICENSE
├── README.md
└── AGENTS.md
```

---

## 3. Build System

### Prerequisites

| Tool        | Version                      |
|-------------|------------------------------|
| JDK         | 17                           |
| Android SDK | Managed automatically by AGP |

### Gradle tasks

```bash
# Build debug and release APKs + AABs
./gradlew build
./gradlew bundle

# Install on a connected device or emulator
./gradlew installDebug
./gradlew installRelease

# Uninstall
./gradlew uninstallDebug
./gradlew uninstallRelease

# Run lint checks
./gradlew lint lintRelease

# Clean all build outputs
./gradlew clean
```

### Release signing

Release builds read signing credentials from the following **environment variables**:

| Variable         | Description                                            |
|------------------|--------------------------------------------------------|
| `STORE_FILE`     | Absolute path to the `.jks` / `.p12` keystore          |
| `STORE_PASSWORD` | Keystore password                                      |
| `KEY_ALIAS`      | Key alias inside the keystore                          |
| `KEY_PASSWORD`   | Key password (falls back to `STORE_PASSWORD` if unset) |

If none of these are set, the release build falls back to the **debug keystore** automatically.

### Gradle performance settings (`gradle.properties`)

- Build cache and configuration cache are enabled, running in parallel.
- JVM heap is set to 2 GB.
- Non-transitive R classes are enabled (`android.nonTransitiveRClass=true`).

---

## 4. Coding Standards

### Kotlin code (`app/src/main/kotlin/`)

- **Style**: Follow official Kotlin style.

### Gradle (`.gradle.kts` files)

- Use **Kotlin DSL** (`.kts`) — do not introduce Groovy build scripts.
- Add new dependencies through the **version catalog** (`gradle/libs.versions.toml`), not as inline
  strings.

### XML / Resources

- All string resources belong in `app/src/main/res/values/strings.xml`.
- Follow Android resource naming conventions (`snake_case` for resource IDs).

---

## 5. CI/CD Pipeline

All workflows are defined in `.github/workflows/`.

### `build.yml` — triggered on

- Push to `main`
- Push of a `v*.*.*` tag
- Pull requests targeting `main`
- Manual dispatch

**Steps summary:**

1. Check out code (with submodules).
2. Set up Gradle.
3. Build APKs and AABs (`./gradlew build bundle`).
4. Run lint (`./gradlew lint lintRelease`).
5. Upload artifacts: debug/release APKs, debug/release AABs, native debug symbols, ProGuard mapping,
   build logs, lint reports.
6. Generate **build-provenance attestations** for all output artifacts.

### `release.yml` — triggered on version tag push

Creates a GitHub Release and attaches the signed release artifacts.

### `dependency-submission.yml`

Submits the dependency graph to GitHub for security analysis.

---

## 6. Important Constraints

| Rule                                        | Reason                                                                                                  |
|---------------------------------------------|---------------------------------------------------------------------------------------------------------|
| **Always use `./gradlew`, never `gradle`.** | The wrapper pins the exact Gradle version required for reproducible builds.                             |
| **Lint is treated as errors.**              | `warningsAsErrors = true` in the lint configuration. All lint warnings must be resolved before merging. |  |