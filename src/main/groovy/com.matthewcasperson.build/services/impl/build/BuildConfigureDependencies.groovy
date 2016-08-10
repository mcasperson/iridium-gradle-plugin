package com.matthewcasperson.build.services.impl.build

import com.matthewcasperson.build.services.ConfigureDependencies
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait BuildConfigureDependencies implements ConfigureDependencies {
    void configureDependencies(Project project) {
        assert project != null;

        project.dependencies {
            compile gradleApi()
            compile localGroovy()
            compile group: 'commons-lang', name: 'commons-lang', version: '2.6'
        }
    }
}