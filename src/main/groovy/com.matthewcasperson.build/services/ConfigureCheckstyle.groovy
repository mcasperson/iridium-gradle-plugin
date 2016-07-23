package com.matthewcasperson.build.services;

import org.gradle.api.Project;

/**
 * Configures checkstyle validation
 */
public interface ConfigureCheckstyle {
    void configureCheckstyle(Project project);
}