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