package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.SonatypePublishing
import org.apache.commons.lang.ObjectUtils
import org.apache.commons.lang.StringUtils
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
        assert project.hasProperty('ossrhUsername') || StringUtils.isNotBlank(System.getenv('ossrhUsername')) : 'You need to define the ossrhUsername property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('ossrhPassword') || StringUtils.isNotBlank(System.getenv('ossrhPassword')) : 'You need to define the ossrhPassword property in the ~/.gradle/gradle.properties file'
        assert project.hasProperty('MavenName') || StringUtils.isNotBlank(System.getenv('MavenName')) : 'You need to define the MavenName property in the gradle.properties file'
        assert project.hasProperty('MavenDescription') || StringUtils.isNotBlank(System.getenv('MavenDescription')) : 'You need to define the MavenDescription property in the gradle.properties file'
        assert project.hasProperty('MavenURL') || StringUtils.isNotBlank(System.getenv('MavenURL')) : 'You need to define the MavenURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMURL') || StringUtils.isNotBlank(System.getenv('MavenSCMURL')) : 'You need to define the MavenSCMURL property in the gradle.properties file'
        assert project.hasProperty('MavenSCMConnection') || StringUtils.isNotBlank(System.getenv('MavenSCMConnection')) : 'You need to define the MavenSCMConnection property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseName') || StringUtils.isNotBlank(System.getenv('MavenLicenseName')) : 'You need to define the MavenLicenseName property in the gradle.properties file'
        assert project.hasProperty('MavenLicenseURL') || StringUtils.isNotBlank(System.getenv('MavenLicenseURL')) : 'You need to define the MavenLicenseURL property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperID') || StringUtils.isNotBlank(System.getenv('MavenDeveloperID')) : 'You need to define the MavenDeveloperID property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperName') || StringUtils.isNotBlank(System.getenv('MavenDeveloperName')) : 'You need to define the MavenDeveloperName property in the gradle.properties file'
        assert project.hasProperty('MavenDeveloperEMail') || StringUtils.isNotBlank(System.getenv('MavenDeveloperEMail')) : 'You need to define the MavenDeveloperEMail property in the gradle.properties file'
        assert project.hasProperty('Group') || StringUtils.isNotBlank(System.getenv('Group')) : 'You need to define the Group property in the gradle.properties file'
        assert project.hasProperty('ArchivesBaseName') || StringUtils.isNotBlank(System.getenv('ArchivesBaseName')) : 'You need to define the ArchivesBaseName property in the gradle.properties file'
        assert project.hasProperty('Version') || StringUtils.isNotBlank(System.getenv('Version')) : 'You need to define the Version property in the gradle.properties file'

        project.plugins.apply('maven');
        project.plugins.apply('signing');

        def artifactGroup = ObjectUtils.defaultIfNull(project.getProperties().get('Group'), System.getenv('Group'));
        def artifactName = ObjectUtils.defaultIfNull(project.getProperties().get('ArchivesBaseName'), System.getenv('ArchivesBaseName'));
        def artifactVersion = ObjectUtils.defaultIfNull(project.getProperties().get('Version'), System.getenv('Version'));
        def username = ObjectUtils.defaultIfNull(project.getProperties().get('ossrhUsername'), System.getenv('ossrhUsername'));
        def password = ObjectUtils.defaultIfNull(project.getProperties().get('ossrhPassword'), System.getenv('ossrhPassword'));
        def mavenName = ObjectUtils.defaultIfNull(project.getProperties().get('MavenName'), System.getenv('MavenName'));
        def mavenDescription = ObjectUtils.defaultIfNull(project.getProperties().get('MavenDescription'), System.getenv('MavenDescription'));
        def mavenUrl = ObjectUtils.defaultIfNull(project.getProperties().get('MavenURL'), System.getenv('MavenURL'));
        def mavenScmUrl = ObjectUtils.defaultIfNull(project.getProperties().get('MavenSCMURL'), System.getenv('MavenSCMURL'));
        def mavenScmConnection = ObjectUtils.defaultIfNull(project.getProperties().get('MavenSCMConnection'), System.getenv('MavenSCMConnection'));
        def mavenLicenseName = ObjectUtils.defaultIfNull(project.getProperties().get('MavenLicenseName'), System.getenv('MavenLicenseName'));
        def mavenLicenseUrl = ObjectUtils.defaultIfNull(project.getProperties().get('MavenLicenseURL'), System.getenv('MavenLicenseURL'));
        def mavenDeveloperId = ObjectUtils.defaultIfNull(project.getProperties().get('MavenDeveloperID'), System.getenv('MavenDeveloperID'));
        def mavenDeveloperName = ObjectUtils.defaultIfNull(project.getProperties().get('MavenDeveloperName'), System.getenv('MavenDeveloperName'));
        def mavenDeveloperEmail = ObjectUtils.defaultIfNull(project.getProperties().get('MavenDeveloperEMail'), System.getenv('MavenDeveloperEMail'));

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