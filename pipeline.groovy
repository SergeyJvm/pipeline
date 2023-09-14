pipeline {

    agent {
        node {
            label 'мастер || master'
        }
    }

    stages {

        stage("Download idea community") {
            steps {
                sh "mkdir -p sberide/android"
                dir('sberide') {
                    script {
                        //clean workspace
                        cleanWs disableDeferredWipeout: true, deleteDirs: true
                        //download
                        git branch: '231.9225', credentialsId: 'extreme_0788@bk.ru', url: 'https://github.com/JetBrains/intellij-community.git'
                        sh "ls -la"

                    }
                }
            }
        }
    }

}