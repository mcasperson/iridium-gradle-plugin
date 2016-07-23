package com.matthewcasperson.build.services

import org.gradle.api.Project

/**
 * Created by Matthew on 23/01/2016.
 */
interface SonatypePublishing {
    void configureSonatypePublishing(Project project);
}