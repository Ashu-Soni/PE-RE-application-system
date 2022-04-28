pipeline {
 environment {
        registry = "meetgoswami/spe_project"
        registryCredential = 'docker_cred'
    }
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
                    script {
                    dockerImage = docker.build registry + ":final_frontend"
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push()  
                    }
                }
                }
                dir('spe_majorProject'){
                     script {
                    dockerImagebackend = docker.build registry + ":final_backend"
                    docker.withRegistry( '', registryCredential ) {
                        dockerImagebackend.push()  
                    }
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
