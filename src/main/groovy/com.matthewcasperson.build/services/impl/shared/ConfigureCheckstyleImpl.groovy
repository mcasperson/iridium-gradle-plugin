package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureCheckstyle
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle
import org.gradle.api.plugins.quality.CheckstyleExtension

/**
 * Configures the checkstyle task to use a supplied set of rules
 */
trait ConfigureCheckstyleImpl implements ConfigureCheckstyle {
    void configureCheckstyle(Project project) {
        assert(project != null);

        project.plugins.apply('checkstyle');

        def checkStyleRules = getClass().getClassLoader().getResourceAsStream("checkstyle.xml").text;

        CheckstyleExtension checkstyleExtension = project.extensions.findByType(CheckstyleExtension);
        checkstyleExtension.toolVersion = '6.18';
        checkstyleExtension.showViolations = true;
        checkstyleExtension.ignoreFailures = false;
        checkstyleExtension.config = project.resources.text.fromString(checkStyleRules);

        Checkstyle checkStyle = project.tasks.withType(Checkstyle).first();
        checkStyle.showViolations = true;
        checkStyle.ignoreFailures = false;
        checkStyle.reports.html.destination = project.rootProject.file("${project.buildDir}/reports/checkstyle.html");
    }
}