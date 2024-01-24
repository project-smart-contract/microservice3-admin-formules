pipeline {
    agent any
    environment {
        // Define any environment variables needed for the pipeline
        IMAGE_NAME = 'microservice3-admin-formules'
        CONTAINER_NAME = 'microservice3-admin-formules-container'
        DOCKERHUB_CREDENTIALS = credentials('dockerhub_amina')
        PROJECT_ID = 'igneous-clover-409614'
        CLUSTER_NAME = 'cluster-2'
        LOCATION = 'us-central1-c'
        CREDENTIALS_ID = 'gke_cluster'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    echo 'Building...'
                    sh 'mvn clean compile package -DskipTests'
                }
            }
        }
        stage('Dockerize') {
            steps {
                script {
                    echo 'Dockerizing...'
                    sh "docker build -t aminabakkali/${IMAGE_NAME} ."
                }
            }
        }

        stage('Login + Push') {
            steps {
                echo 'Login...'
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
                echo 'Pushing...'
                sh "docker push aminabakkali/${IMAGE_NAME}"

            }
        }
        stage('Deploy to GKE') {
            steps{
                echo 'Deploying...'
                step([$class: 'KubernetesEngineBuilder', projectId: env.PROJECT_ID, clusterName: env.CLUSTER_NAME, location: env.LOCATION, manifestPattern: 'microservice3-admin-formules.yaml', credentialsId: env.CREDENTIALS_ID, verifyDeployments: true])
                echo "Deployment finished !"
            }
        }
    }
}

