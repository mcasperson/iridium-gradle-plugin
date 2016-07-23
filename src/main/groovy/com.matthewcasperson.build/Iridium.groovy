package com.matthewcasperson.build

import com.matthewcasperson.build.services.impl.shared.ApplyPluginsImpl
import com.matthewcasperson.build.services.impl.shared.*
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
class Iridium implements
        Plugin<Project>,
        ApplyPluginsImpl,
        ConfigureJavaVersionImpl,
        ConfigureMavenImpl,
        SonatypePublishingImpl,
        ConfigureWrapperImpl,
        ConfigureJavadocImpl,
        ConfigureArtifactsImpl,
        ConfigureCheckstyleImpl {

    void apply(Project project) {
        applyPlugins(project);
        configureCheckstyle(project);
        configureArtifacts(project);
        configureSonatypePublishing(project);
        configureJavaVersion(project);
        configureMaven(project);
        configureWrapper(project);
        configureJavadoc(project);
    }
}