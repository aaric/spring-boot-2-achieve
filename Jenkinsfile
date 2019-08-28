pipeline {

  agent any

  parameters {
    string(name: 'repoUrl', defaultValue: 'https://github.com/aaric/spring-boot-2-achieve', description: 'Git仓库地址')
    string(name: 'repoBranch', defaultValue: 'dev-aaric', description: 'Git仓库分支名称')
  }

  tools {
    jdk  'jdk8'
    gradle 'gradle-5.2.1'
  }

  environment {
    HELLO_TIPS = 'Hello, Pipeline!'
  }

  stages {
    //---------BEGIN----------//
    stage('Infomation') {
      steps {
        echo "${env.HELLO_TIPS}"
        echo "Repo Address: ${params.repoUrl}"
        echo "Repo Branch: ${params.repoBranch}"
        sh 'git --version'
        sh 'java -version'
        sh 'gradle --version'
      }
    }

    stage('Preparation') {
      steps {
        git branch: params.repoBranch, url: params.repoUrl
      }
    }

    stage('Build') {
      steps {
        sh "gradle clean build"
      }
    }

    stage('JUnit Test Reports') {
      steps {
        junit '**/build/test-results/test/*.xml'
      }
    }

    stage('SonarQube Analysis') {
      // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-gradle/
      steps {
        withSonarQubeEnv('SonarQube') {
          sh "gradle sonarqube -x test --info"
        }
      }
    }

    /*stage('Quality Gate') { //忽略，状态无法返回给Jenkins
      // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-jenkins/
      steps {
        script {
          timeout(time: 10, unit: 'MINUTES') { //1-HOURS
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
              error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
          }
        }
      }
    }*/

    stage('Automated Testing') {
      steps {
        // TODO 执行自动化测试脚本
        echo '自动化测试, 待完善...'
      }
    }

    stage('Performance Testing') {
      steps {
        // TODO 执行JMeter性能测试
        echo '待完善...'
      }
    }

    stage('Notification') {
      steps {
        mail to: 'account@incarcloud.com',
          subject: 'PineLine Result',
          body: 'PineLine Task Completed !!!'
        }
    }

    //---------END----------//
  }
}