package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureCheckstyle
import org.gradle.api.Project
import org.gradle.api.plugins.quality.Checkstyle

/**
 * Created by matthewcasperson on 24/07/2016.
 */
trait ConfigureCheckstyleImpl implements ConfigureCheckstyle {
    void configureCheckstyle(Project project) {
        assert(project != null);

        project.plugins.apply('checkstyle');

        project.checkstyle {
            toolVersion = '6.18'
            config = getClass().getClassLoader().getResourceAsStream("checkstyle.xml").text;
            showViolations = true
            ignoreFailures = false
        }

        project.tasks.withType(Checkstyle) {
            ignoreFailures = false
            reports {
                html.destination project.rootProject.file("${buildDir}/reports/checkstyle.html")
            }
        }

    }
}