pipeline {
    agent any

    environment {
        SPRING_PROFILES_ACTIVE = 'test'
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'mvn clean verify'
            }
        }
    }
}