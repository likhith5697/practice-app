pipeline {
    agent any
    environment {
        BACKEND_IMAGE = 'practice-backend'
        FRONTEND_IMAGE = 'practice-frontend'
    }
    stages {
        stage('Checkout') {
            steps {
                // Clone the repository
                git branch: 'main', url: 'https://your-repo-url.git'
            }
        }
        
        stage('Build Backend') {
            steps {
                dir('practice') {
                    // Build the Maven project
                    sh './mvnw clean package -DskipTests'
                }
            }
        }

        stage('Build Frontend') {
            steps {
                dir('practiceReact/crm-frontend') {
                    // Install npm dependencies and build the React app
                    sh 'npm install'
                    sh 'npm run build'
                }
            }
        }

        stage('Docker Build') {
            steps {
                // Build Docker images for backend and frontend
                sh 'docker build -t ${BACKEND_IMAGE}:latest ./practice'
                sh 'docker build -t ${FRONTEND_IMAGE}:latest ./practiceReact/crm-frontend'
            }
        }

        stage('Push Docker Images') {
            steps {
                // Push Docker images to a Docker registry
                withDockerRegistry([url: 'https://your-docker-registry', credentialsId: 'docker-credentials-id']) {
                    sh 'docker push ${BACKEND_IMAGE}:latest'
                    sh 'docker push ${FRONTEND_IMAGE}:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                // Deployment step (e.g., Docker Compose or Kubernetes)
                echo 'Add deployment commands here'
            }
        }
    }
    post {
        always {
            // Clean up workspace after the pipeline
            cleanWs()
        }
    }
}
