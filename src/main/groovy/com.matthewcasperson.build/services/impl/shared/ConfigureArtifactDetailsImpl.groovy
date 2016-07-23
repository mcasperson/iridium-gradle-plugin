package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.ConfigureArtifactDetails
import org.gradle.api.Project

/**
 * An implementation that is shared across APIOmega projects to provide a way to configure the artifact details
 */
trait ConfigureArtifactDetailsImpl implements ConfigureArtifactDetails {
    void configureArtifactDetails(Project project) {
        assert project != null;

        /*
            TODO: work out how to fix the file that is published when these properties are set
         */
        project.group = project.getProperties().get('Group');
        project.archivesBaseName = project.getProperties().get('ArchivesBaseName');
        project.version = project.getProperties().get('Version');
    }
}