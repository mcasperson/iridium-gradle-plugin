package com.matthewcasperson.build.services.impl.apiomegalibrary

import com.matthewcasperson.build.services.ConfigureDependencies
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait ConfigureDependenciesImpl implements ConfigureDependencies {
    def junitVersion = '4.12'

    void configureDependencies(Project project) {
        assert project != null;

        project.dependencies {
            testCompile 'junit:junit:' + junitVersion
        }
    }
}