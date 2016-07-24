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
        assert project.hasProperty('ossrhUsername') : 'You need to define the ossrhUsername property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('ossrhPassword') : 'You need to define the ossrhPassword property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('MavenName') : 'You need to define the MavenName property in the gradle.properties file'
        assert project.hasProperty('MavenDescription') : 'You need to define the MavenDescription property in the gradle.properties file'
        assert project.hasProperty('MavenURL') : 'You need to define the MavenURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMURL') : 'You need to define the MavenSCMURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMConnection') : 'You need to define the MavenSCMConnection property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseName') : 'You need to define the MavenLicenseName property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseURL') : 'You need to define the MavenLicenseURL property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperID') : 'You need to define the MavenDeveloperID property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperName') : 'You need to define the MavenDeveloperName property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperEMail') : 'You need to define the MavenDeveloperEMail property in the gradle.properties file'
        assert project.hasProperty('Group') : 'You need to define the Group property in the gradle.properties file'
        assert project.hasProperty('ArchivesBaseName') : 'You need to define the ArchivesBaseName property in the gradle.properties file'
        assert project.hasProperty('Version') : 'You need to define the Version property in the gradle.properties file'

        project.plugins.apply('maven');
        project.plugins.apply('signing');

        def artifactGroup = project.getProperties().get('Group');
        def artifactName = project.getProperties().get('ArchivesBaseName');
        def artifactVersion = project.getProperties().get('Version');
        def username = project.getProperties().get('ossrhUsername')
        def password = project.getProperties().get('ossrhPassword')

        project.signing {
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
                        name project.getProperties().get('MavenName')
                        packaging 'jar'
                        description project.getProperties().get('MavenDescription')
                        url project.getProperties().get('MavenURL')

                        scm {
                            url project.getProperties().get('MavenSCMURL')
                            connection project.getProperties().get('MavenSCMConnection')
                            developerConnection project.getProperties().get('MavenSCMConnection')
                        }

                        licenses {
                            license {
                                name project.getProperties().get('MavenLicenseName')
                                url project.getProperties().get('MavenLicenseURL')
                            }
                        }

                        developers {
                            developer {
                                id project.getProperties().get('MavenDeveloperID')
                                name project.getProperties().get('MavenDeveloperName')
                                email project.getProperties().get('MavenDeveloperEMail')
                            }
                        }
                    }
                }
            }
        }
    }
}