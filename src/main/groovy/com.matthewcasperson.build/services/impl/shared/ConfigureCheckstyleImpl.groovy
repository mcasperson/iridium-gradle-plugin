package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureCheckstyle
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle

/**
 * Configures the checkstyle task to use a supplied set of rules
 */
trait ConfigureCheckstyleImpl implements ConfigureCheckstyle {
    void configureCheckstyle(Project project) {
        assert(project != null);

        project.plugins.apply('checkstyle');

        Checkstyle checkstyle = project.getTasksByName('checkstyle', false).first();
        checkstyle.showViolations = true;
        checkstyle.ignoreFailures = false;
        checkstyle.config = getClass().getClassLoader().getResourceAsStream("checkstyle.xml").text;
        checkstyle.reports.html.destination project.rootProject.file("${buildDir}/reports/checkstyle.html");
    }
}