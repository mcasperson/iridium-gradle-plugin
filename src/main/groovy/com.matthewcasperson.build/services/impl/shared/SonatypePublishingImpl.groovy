package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.SonatypePublishing
import org.gradle.api.Project
import org.gradle.api.artifacts.maven.MavenDeployment

/**
 * An implementation that is shared across APIOmega projects to provide a way to publish to Sonatype
 * see http://mike-neck.github.io/blog/2013/06/21/how-to-publish-artifacts-with-gradle-maven-publish-plugin-version-1-dot-6/
 * for more details.
 */
trait SonatypePublishingImpl implements SonatypePublishing {
    String SNAPSHOTS_REPO = "https://oss.sonatype.org/content/repositories/snapshots/";
    String RELEASES_REPO = "https://oss.sonatype.org/service/local/staging/deploy/maven2/";

    void configureSonatypePublishing(Project project) {
        assert project != null;
        assert project.hasProperty('ossrhUsername') || System.getenv('ossrhUsername') != null : 'You need to define the ossrhUsername property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('ossrhPassword') || System.getenv('ossrhPassword') != null : 'You need to define the ossrhPassword property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('MavenName') || System.getenv('MavenName') != null : 'You need to define the MavenName property in the gradle.properties file'
        assert project.hasProperty('MavenDescription') || System.getenv('MavenDescription') != null : 'You need to define the MavenDescription property in the gradle.properties file'
        assert project.hasProperty('MavenURL') || System.getenv('MavenURL') != null : 'You need to define the MavenURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMURL') || System.getenv('MavenSCMURL') != null : 'You need to define the MavenSCMURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMConnection') || System.getenv('MavenSCMConnection') != null : 'You need to define the MavenSCMConnection property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseName') || System.getenv('MavenLicenseName') != null : 'You need to define the MavenLicenseName property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseURL') || System.getenv('MavenLicenseURL') != null : 'You need to define the MavenLicenseURL property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperID') || System.getenv('MavenDeveloperID') != null : 'You need to define the MavenDeveloperID property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperName') || System.getenv('MavenDeveloperName') != null : 'You need to define the MavenDeveloperName property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperEMail') || System.getenv('MavenDeveloperEMail') != null : 'You need to define the MavenDeveloperEMail property in the gradle.properties file'
        assert project.hasProperty('Group') || System.getenv('Group') != null : 'You need to define the Group property in the gradle.properties file'
        assert project.hasProperty('ArchivesBaseName') || System.getenv('ArchivesBaseName') != null : 'You need to define the ArchivesBaseName property in the gradle.properties file'
        assert project.hasProperty('Version') || System.getenv('Version') != null : 'You need to define the Version property in the gradle.properties file'

        project.plugins.apply('maven');
        project.plugins.apply('signing');

        def artifactGroup = project.getProperties().get('Group') ?: System.getenv('Group');
        def artifactName = project.getProperties().get('ArchivesBaseName') ?: System.getenv('ArchivesBaseName');
        def artifactVersion = project.getProperties().get('Version') ?: System.getenv('Version');
        def username = project.getProperties().get('ossrhUsername') ?: System.getenv('ossrhUsername');
        def password = project.getProperties().get('ossrhPassword') ?: System.getenv('ossrhPassword');
        def mavenName = project.getProperties().get('MavenName') ?: System.getenv('MavenName');
        def mavenDescription = project.getProperties().get('MavenDescription') ?: System.getenv('MavenDescription');
        def mavenUrl = project.getProperties().get('MavenURL') ?: System.getenv('MavenURL');
        def mavenScmUrl = project.getProperties().get('MavenSCMURL') ?: System.getenv('MavenSCMURL');
        def mavenScmConnection = project.getProperties().get('MavenSCMConnection') ?: System.getenv('MavenSCMConnection');
        def mavenLicenseName = project.getProperties().get('MavenLicenseName') ?: System.getenv('MavenLicenseName');
        def mavenLicenseUrl = project.getProperties().get('MavenLicenseURL') ?: System.getenv('MavenLicenseURL');
        def mavenDeveloperId = project.getProperties().get('MavenDeveloperID') ?: System.getenv('MavenDeveloperID');
        def mavenDeveloperName = project.getProperties().get('MavenDeveloperName') ?: System.getenv('MavenDeveloperName');
        def mavenDeveloperEmail = project.getProperties().get('MavenDeveloperEMail') ?: System.getenv('MavenDeveloperEMail');

        def signingDisabledValue = project.getProperties().get('DisableSigning') ?: System.getenv('DisableSigning');
        def signingDisabled = Boolean.parseBoolean(signingDisabledValue);

        project.signing {
            required = !signingDisabled
            sign project.configurations.archives
        }

        project.uploadArchives {
            repositories {
                mavenDeployer {
                    beforeDeployment { MavenDeployment deployment -> project.signing.signPom(deployment) }

                    getPom().groupId = artifactGroup
                    getPom().artifactId = artifactName
                    getPom().version = artifactVersion

                    repository(url: RELEASES_REPO) {
                        authentication(userName: username, password: password)
                    }
                    snapshotRepository(url: SNAPSHOTS_REPO) {
                        authentication(userName: username, password: password)
                    }

                    getPom().project {
                        name mavenName
                        packaging 'jar'
                        description mavenDescription
                        url mavenUrl

                        scm {
                            url mavenScmUrl
                            connection mavenScmConnection
                            developerConnection mavenScmConnection
                        }

                        licenses {
                            license {
                                name mavenLicenseName
                                url mavenLicenseUrl
                            }
                        }

                        developers {
                            developer {
                                id mavenDeveloperId
                                name mavenDeveloperName
                                email mavenDeveloperEmail
                            }
                        }
                    }
                }
            }
        }
    }
}