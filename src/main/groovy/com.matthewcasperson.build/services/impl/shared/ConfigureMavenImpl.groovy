package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureMaven
import org.gradle.api.Project

/**
 * An implementation that is shared across Iridium projects to configure the various
 * maven repos that we will be using
 */
trait ConfigureMavenImpl implements ConfigureMaven {
    void configureMaven(Project project) {
        assert project != null;
        project.repositories {
            mavenCentral()
            mavenLocal()
            maven {
                url 'https://oss.sonatype.org/content/repositories/snapshots'
            }
            maven {
                url 'http://repository.springsource.com/maven/bundles/external'
            }
            maven {
                url 'https://maven.repository.redhat.com/ga/'
            }
        }
    }
}