node {
   stage('Preparation') {
        git branch: 'dev-aaric', url: 'https://github.com/aaric/spring-boot-2-achieve'
   }

    stage('Build') {
        // no 'java' --> sudo ln -s /usr/java/jdk1.8.0_172/bin/java /usr/bin/java
        if (isUnix()) {
            sh "${tool 'gradle-5.2.1'}/bin/gradle clean build"
        }
   }

   stage('Test Reports') {
       junit '**/build/test-results/test/*.xml'
   }
}