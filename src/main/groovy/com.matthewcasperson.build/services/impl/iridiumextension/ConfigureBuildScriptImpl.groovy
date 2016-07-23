package com.matthewcasperson.build.services.impl.apiomegalibrary

import com.matthewcasperson.build.services.ConfigureBuildScript
import org.gradle.api.Project

/**
 * Created by Matthew on 7/02/2016.
 */
trait ConfigureBuildScriptImpl implements ConfigureBuildScript{
    void configureBuildScript(Project project) {
        assert project != null;

        project.buildscript {
            repositories {
                maven { url 'http://repo.spring.io/plugins-release' }
            }
            dependencies {
                classpath 'org.springframework.build.gradle:propdeps-plugin:0.0.7'
            }
        }
    }
}