package com.matthewcasperson.build.services.impl.shared

import com.matthewcasperson.build.services.impl.ConfigureXercesVersion
import org.gradle.api.Project

/**
 * Created by matthewcasperson on 24/07/2016.
 */
trait ConfigureXercesVersionImpl implements ConfigureXercesVersion {
    void configureXercesVersion(Project project) {
        project.configurations.all {
            resolutionStrategy {
                force 'xml-apis:xml-apis:1.4.01'
            }
        }
    }
}