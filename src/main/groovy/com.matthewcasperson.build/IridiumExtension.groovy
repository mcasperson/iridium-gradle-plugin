package com.matthewcasperson.build

import com.matthewcasperson.build.services.impl.apiomegalibrary.ConfigureDependenciesImpl
import com.matthewcasperson.build.services.impl.shared.ApplyPluginsImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureArtifactsImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureCheckstyleImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureJavaVersionImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureJavadocImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureMavenImpl
import com.matthewcasperson.build.services.impl.shared.ConfigureXercesVersionImpl
import com.matthewcasperson.build.services.impl.shared.SonatypePublishingImpl
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
class IridiumExtension implements
        Plugin<Project>,
        ApplyPluginsImpl,
        ConfigureJavaVersionImpl,
        ConfigureMavenImpl,
        SonatypePublishingImpl,
        ConfigureDependenciesImpl,
        ConfigureJavadocImpl,
        ConfigureArtifactsImpl,
        ConfigureCheckstyleImpl,
        ConfigureXercesVersionImpl {

    void apply(Project project) {
        applyPlugins(project);
        configureXercesVersion(project);
        configureCheckstyle(project);
        configureDependencies(project);
        configureArtifacts(project);
        configureSonatypePublishing(project);
        configureJavaVersion(project);
        configureMaven(project);
        configureJavadoc(project);
    }
}