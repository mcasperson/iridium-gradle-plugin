package com.matthewcasperson.build.services

import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
interface ConfigureWrapper {
    void configureWrapper(Project project);
}