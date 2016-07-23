package com.matthewcasperson.build.services.impl.tasks

import org.gradle.api.tasks.TaskAction
import org.gradle.jvm.tasks.Jar

/**
 * Created by Matthew on 7/02/2016.
 */
class SourceJarTask extends Jar {
   public SourceJarTask() {
        classifier = 'sources'
        from project.sourceSets.main.allSource
    }
}
