This Gradle plugin has been written to automate common processes relating to the development
or Iridium and its ecosystem. It adds the required configuration to publish to the Sonatype
OSS repository, configures standard Maven repos, sets up Gradle wrapper versions, configures
JavaDoc generation and more.

To make use of this plugin, add the following to the build.gradle file:

```
buildscript {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots'
        }
    }
    dependencies {
        classpath 'com.matthewcasperson:build:0.0.1'
    }
}

apply plugin: 'com.matthewcasperson.iridiumextension'
```

To publish to Sonatype, save your username and password in the ~/.gradle/gradle.properties file:

```
ossrhUsername=sonatype_user
ossrhPassword=sonatype_password
```

To sign your releases, generate a key with:

```
gpg --gen-key
```

Get the details of any keys with:

```
gpg --list-secret-keys
```

Save the details of the key in ~/.gradle/gradle.properties file:

```
signing.keyId=12345678
signing.password=password
signing.secretKeyRingFile=C:/Users/Matthew/AppData/Roaming/gnupg/secring.gpg
```

To publish, run:

```
./gradlew clean publish --refresh-dependencies
```