node {
   stage('Preparation') {
        git branch: 'dev-aaric', url: 'https://github.com/aaric/spring-boot-2-achieve'
   }

    stage('Build') {
        sh "${tool 'gradle-5.2.1'}/bin/gradle clean build"
   }

   stage('JUnit Test Reports') {
       junit '**/build/test-results/test/*.xml'
   }

   stage('SonarQube Analysis') {
       withSonarQubeEnv('SonarQube') {
           sh "${tool 'sonar-scanner-4.0'}/bin/sonar-scanner"
       }
   }
}