pipeline {
    agent any
    stages {
        stage("Git clone") {
            steps {
                git url: 'https://github.com/Ashu-Soni/PE-RE-application-system.git', branch: 'master'
            }
        }
        stage("Prerequisite") {
            steps{
                dir('frontend'){
                    sh 'npm install'
                }
            }
        }
        stage('Test Frontend'){
            steps{
                dir('frontend'){
                    sh 'npm test'
                }
            }
        }
        stage('Test Backend'){
            steps {
                dir('spe_majorProject'){
                    sh 'mvn clean test'
                }   
            }
        }
        stage('Build'){
            steps {
                dir('spe_majorProject'){
                    sh 'mvn install'   
                }
            }
        }
        stage('Docker Build and Push'){
            steps {
                dir('frontend'){
                    sh 'docker build -t meetgoswami/final_frontend .'
                    sh 'docker push meetgoswami/final_frontend'
                }
                dir('spe_majorProject'){
                    sh 'docker build -t meetgoswami/final_backend .'
                    sh 'docker push meetgoswami/final_backend'
                }
            }
        }
        stage('Ansible Deploy') {
             steps {
                  ansiblePlaybook colorized: true, disableHostKeyChecking: true, installation: 'Ansible', inventory: 'inventory', playbook: 'deploy.yml'
             }
        }
    }
    post {
        always {
            echo 'One way or another, I have finished'
            deleteDir() /* clean up our workspace */
        }
    }
}