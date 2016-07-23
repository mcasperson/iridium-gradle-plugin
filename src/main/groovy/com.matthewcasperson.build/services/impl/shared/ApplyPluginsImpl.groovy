package com.matthewcasperson.build.services.impl.shared

import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait ApplyPluginsImpl {
    void applyPlugins(Project project) {
        assert project != null;
        project.plugins.apply('java');
        project.plugins.apply('propdeps');
        project.plugins.apply('propdeps-maven');
        project.plugins.apply('propdeps-idea');
        project.plugins.apply('propdeps-eclipse');
        project.plugins.apply('maven-publish');
        project.plugins.apply('signing');
    }
}