pipeline {

    agent {
        node {
            label 'мастер || master'
        }
    }
    options{
        // Increase the memory limit to 8 GB
        // This line will set -Xmx4g as the maximum heap size for the JVM
        jvmArgs: '-Xmx8g'
    }

    stages {

        stage("Download idea community") {
            steps {
                sh "mkdir -p sberide"
                dir('sberide') {
                    script {
                        //clean workspace
                        cleanWs disableDeferredWipeout: true, deleteDirs: true
                        //download
                        git branch: '231.9225', credentialsId: 'extreme_0788', url: 'https://github.com/JetBrains/intellij-community.git'
                        sh "ls -la"
                        sh "./getPlugins.cmd"
                        sh "ls -la"
                    }
                }
            }
        }

        stage("start installer.cmd") {
            steps {
                dir('sberide') {
                    print '___________________----------START - INSTALLER.CMD' as java.lang.Object
                    sh "./installers.cmd"
                    print '___________________----------END - INSTALLER.CMD' as java.lang.Object
                }
            }
        }
    }

}