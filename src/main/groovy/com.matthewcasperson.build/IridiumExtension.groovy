package com.matthewcasperson.build

import com.matthewcasperson.build.services.impl.apiomegalibrary.ConfigureDependenciesImpl
import com.matthewcasperson.build.services.impl.apiomegalibrary.ApplyPluginsImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureArtifactsImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureJavaVersionImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureJavadocImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureMavenImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureWrapperImpl
import com.matthewcasperson.build.services.impl.shared.SonatypePublishingImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
class APIOmegaLibrary implements
        Plugin<Project>,
        ApplyPluginsImpl,
        ConfigureJavaVersionImpl,
        ConfigureMavenImpl,
        SonatypePublishingImpl,
        ConfigureWrapperImpl,
        ConfigureDependenciesImpl,
        ConfigureJavadocImpl,
        ConfigureArtifactsImpl {

    void apply(Project project) {
        applyPlugins(project);
        configureDependencies(project);
        configureArtifacts(project);
        configureSonatypePublishing(project);
        configureJavaVersion(project);
        configureMaven(project);
        configureWrapper(project);
        configureJavadoc(project);
    }
}