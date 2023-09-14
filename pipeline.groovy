pipeline {

    agent {
        node {
            label "Linux_Default"
        }
    }

    stages {

        stage("Download idea community") {
            sh "mkdir -p sberide/android"
            dir(sberide) {
                script {
                    //clean workspace
                    cleanWs disableDeferredWipeout: true, deleteDirs: true
                    //download
                    git branch: 'main', credentialsId: 'extreme_0788@bk.ru', url: 'https://github.com/JetBrains/intellij-community.git'
                    sh "ls -la"

                }
            }
        }
    }

}