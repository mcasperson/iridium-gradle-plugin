package com.matthewcasperson.build.services.impl.build

import com.matthewcasperson.build.services.ApplyPlugins
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait BuildApplyPlugins implements ApplyPlugins {
    void applyPlugins(Project project) {
        assert project != null;
        project.plugins.apply('groovy');
        project.plugins.apply('maven');
        project.plugins.apply('maven-publish');
        project.plugins.apply('signing');
    }
}