# Thoth

This is a note-taking app for Android.

---

## Getting Started
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