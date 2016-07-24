package com.matthewcasperson.build.services.impl

import org.gradle.api.Project

/**
 * Deals with the problem where Xerces aml-apis are incorrectly versioned
 */
interface ConfigureXercesVersion {
    void configureXercesVersion(Project project);
}