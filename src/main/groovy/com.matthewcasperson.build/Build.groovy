package com.matthewcasperson.build

import com.matthewcasperson.build.services.impl.build.BuildApplyPlugins
import com.matthewcasperson.build.services.impl.build.BuildConfigureDependencies
import com.matthewcasperson.build.services.impl.shared.ConfigureArtifactsImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureJavadocImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureMavenImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureWrapperImpl
import com.matthewcasperson.build.services.impl.shared.SonatypePublishingImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A plugin to build the APIOmega RESTful interface war files
 */
class Build implements
        Plugin<Project>,
        BuildApplyPlugins,
        ConfigureMavenImpl,
        SonatypePublishingImpl,
        BuildConfigureDependencies,
        ConfigureWrapperImpl,
        ConfigureJavadocImpl,
        ConfigureArtifactsImpl {

    void apply(Project project) {
        applyPlugins(project);
        configureSonatypePublishing(project);
        configureArtifacts(project);
        configureDependencies(project);
        configureMaven(project);
        configureWrapper(project);
        configureJavadoc(project);
    }
}