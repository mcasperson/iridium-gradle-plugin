package com.matthewcasperson.build.services.impl.shared

import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait ApplyPluginsImpl {
    void applyPlugins(Project project) {
        assert project != null;
        project.plugins.apply('java');
    }
}