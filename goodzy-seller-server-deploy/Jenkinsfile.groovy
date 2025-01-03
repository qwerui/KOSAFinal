pipeline {
    agent any
    tools {
        jdk('jdk21')
    }

    stages {
        stage('Build') {
            steps {
                sh './gradlew build -x test'
            }
        }
//        stage('Test') {
//            steps {
//                sh './gradlew test'
//            }
//        }
        stage('Making docker image') {
            steps {
                sh 'docker-compose build'
            }
        }
        stage('Deploy') {
            steps {
                sh 'docker-compose up -d --force-recreate'
            }
        }
    }
}