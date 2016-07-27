package com.matthewcasperson.build.services.impl.apiomegalibrary

import com.matthewcasperson.build.services.ConfigureDependencies
import org.gradle.api.Project

/**
 * Defines the common dependencies used by iridium extensions
 */
trait ConfigureDependenciesImpl implements ConfigureDependencies {
    def junitVersion = '4.12'
    def iridiumVersion = '0.+'

    void configureDependencies(Project project) {
        assert project != null;

        project.dependencies {
            compileOnly 'com.matthewcasperson:iridium:' + iridiumVersion
            testCompile 'junit:junit:' + junitVersion
        }
    }
}