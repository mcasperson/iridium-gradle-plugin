package com.matthewcasperson.build.services

import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
interface ConfigureJavaVersion {
    void configureJavaVersion(Project project);
}