pipeline {
    agent {
        docker {
            image 'maven:3.9.9-eclipse-temurin-21'
            args '-v /var/jenkins_home/.m2:/root/.m2'
        }
    }

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