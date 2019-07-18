package com.matthewcasperson.build

import com.matthewcasperson.build.services.impl.shared.*
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin used to build the main Iridium application. This plugin
 * leave a lot of configuration to the build.gradle file, because
 * things like dependencies and system properties passed through to
 * the run command are not shared. Where functionality is shared
 * (deploy to Sonatype, javadoc config etc) it is implemented here.
 */
class Iridium implements
        Plugin<Project>,
        ApplyPluginsImpl,
        ConfigureJavaVersionImpl,
        ConfigureMavenImpl,
        SonatypePublishingImpl,
        ConfigureJavadocImpl,
        ConfigureArtifactsImpl,
        ConfigureCheckstyleImpl,
        ConfigureXercesVersionImpl{

    void apply(Project project) {
        applyPlugins(project);
        configureXercesVersion(project);
        configureCheckstyle(project);
        configureArtifacts(project);
        configureSonatypePublishing(project);
        configureJavaVersion(project);
        configureMaven(project);
        configureJavadoc(project);
    }
}