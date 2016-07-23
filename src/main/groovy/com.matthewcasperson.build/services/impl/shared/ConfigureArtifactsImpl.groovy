package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureArtifacts
import com.matthewcasperson.build.services.impl.tasks.JavadocJarTask
import com.matthewcasperson.build.services.impl.tasks.SourceJarTask
import org.gradle.api.Project

/**
 * Adds the source and javadoc jar tasks, configures some dependencies, and adds the archives to the
 * output of a build
 */
trait ConfigureArtifactsImpl implements ConfigureArtifacts {
    void configureArtifacts(Project project) {
        project.tasks.create('javadocJar', JavadocJarTask.class);
        project.tasks.create('sourceJar', SourceJarTask.class );

        project.getArtifacts().add("archives", project.tasks.getByName('sourceJar'));
        project.getArtifacts().add("archives", project.tasks.getByName('javadocJar'));

        project.getTasks().getByName("sourceJar").dependsOn(project.getTasks().getByName("classes"));
        project.getTasks().getByName("javadocJar").dependsOn(project.getTasks().getByName("javadoc"));
    }
}