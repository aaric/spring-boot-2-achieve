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
       // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-gradle/
       withSonarQubeEnv() {
           sh "${tool 'gradle-5.2.1'}/bin/gradle sonarqube -x test"
       }
   }
}