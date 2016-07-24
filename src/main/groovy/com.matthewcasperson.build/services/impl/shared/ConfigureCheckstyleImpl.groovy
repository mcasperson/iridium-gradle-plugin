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

        def checkStyleRules = getClass().getClassLoader().getResourceAsStream("checkstyle.xml").text;

        Checkstyle checkstyle = project.tasks.withType(Checkstyle).first();
        checkstyle.showViolations = true;
        checkstyle.ignoreFailures = false;
        checkstyle.config = project.resources.text.fromString(checkStyleRules);
        checkstyle.reports.html.destination = project.rootProject.file("${project.buildDir}/reports/checkstyle.html");
    }
}