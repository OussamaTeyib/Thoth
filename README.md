# Thoth

[![Build](https://github.com/OussamaTeyib/Thoth/actions/workflows/build.yml/badge.svg)](https://github.com/OussamaTeyib/Thoth/actions/workflows/build.yml)
[![CodeQL](https://github.com/OussamaTeyib/Thoth/actions/workflows/codeql.yml/badge.svg)](https://github.com/OussamaTeyib/Thoth/actions/workflows/codeql.yml)
[![Dependency Submission](https://github.com/OussamaTeyib/Thoth/actions/workflows/dependency-submission.yml/badge.svg)](https://github.com/OussamaTeyib/Thoth/actions/workflows/dependency-submission.yml)

This is a note-taking app for Android.

---

## Downloads

| Channel                | Source                                                                                                  |
|------------------------|---------------------------------------------------------------------------------------------------------|
| **Stable Releases**    | [GitHub Releases](https://github.com/OussamaTeyib/Thoth/releases)                                       |
| **Canary (CI) Builds** | [GitHub Actions](https://github.com/OussamaTeyib/Thoth/actions/workflows/build.yml?query=branch%3Amain) |

> [!NOTE]
> Debug builds are recommended for users encountering issues or performing troubleshooting.

> [!CAUTION]
> A signed-in GitHub account is required to retrieve CI-generated artifacts.
>
> The page linked above only displays builds produced from the `main` branch.
> Builds originating from pull requests may contain unfinished or unverified changes, so sticking to
`main` builds is generally recommended unless specifically requested for testing purposes.

---

## Development

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** — JDK 17

If you want to use your own signing key for release builds, set the following environment variables:

- **STORE_FILE** — Path to the keystore
- **STORE_PASSWORD** — Keystore password
- **KEY_ALIAS** — Alias of the key in the keystore
- **KEY_PASSWORD** *(optional)* — Key password (default: **STORE_PASSWORD**)

### Build Instructions

1. Clone the repository:

   ```bash
   git clone https://github.com/OussamaTeyib/Thoth.git
   ```

2. Build the project:
    - Open the project in Android Studio.
    - Let Gradle sync.
    - Use **Run** to launch on a device or emulator.
    - Use **Build** > **Build Bundle(s) / APK(s)** to generate APK or AAB.

---

## License

This project is licensed under the [MIT License](LICENSE).
