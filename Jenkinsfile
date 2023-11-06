pipeline {
    agent any

    stages {
        stage('Checkout Git') {
            steps {
                echo 'Pulling from git ...';
                git branch: 'khalil-branch',
                    url: "https://github.com/nesrinedata/devops";
            }
        }

        stage('MVN CLEAN') {
          steps {
            sh 'mvn clean'
          }
        }

        stage('MVN COMPILE') {
          steps {
            sh 'mvn compile'
          }
        }
        
        stage('MVN SONARQUBE Analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }
        
        stage('MVN TEST') {
          steps {
            sh 'mvn test'
          }
        }
        
        stage('MVN DEPLOY') {
          steps {
            sh 'mvn deploy -DskipTests'
          }
        }
        
        stage('DOCKER: BUILD') {
            steps{
                sh 'docker build -t achat .'
            }
        }
        
        stage('DOCKER: LOGIN & PUSH') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerHubCredentials', usernameVariable: 'DOCKERHUB_USER', passwordVariable: 'DOCKERHUB_PASS')]) {
                    sh 'echo $DOCKERHUB_PASS | docker login -u $DOCKERHUB_USER --password-stdin'
                }
                
                sh 'docker push khaliladimassi/achat:1.0'
                echo 'IMAGE DEPLOYED SUCCESSFULLY!'
            }
        }
        
        stage('DOCKER: COMPOSE') {
            steps{
                // check the configs
                sh 'docker compose config'
                
                // start if no errors arise
                sh 'docker compose up --detach'
                
                // wait for the MySQL server to start
               sleep 5
               
               // print out the tables in the database
               sh 'docker exec khalilallah_dimassi_5ds3-mysqldb-1 mysql -uroot -e "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = \'achatdb\';" | tr \'\n\' \', \''
            }
        }
        
    }
}
