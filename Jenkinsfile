pipeline {

    agent any

    parameters {
        string(name: 'repoUrl', defaultValue: 'https://github.com/aaric/spring-boot-2-achieve', description: 'Git仓库地址')
        string(name: 'repoBranch', defaultValue: 'master', description: 'Git仓库分支名称')
    }

    tools {
        jdk  'jdk8'
        gradle 'gradle-5.2.1'
    }

    environment {
        DEFAULT_CUSTOM_TITLE = 'Hello, Pipeline!'
    }

    stages {
        //--------------- BEGIN ---------------//
        stage('Information') {
            steps {
                echo '//---------  Information ----------//'
                echo "${env.DEFAULT_CUSTOM_TITLE}"
                echo "Repo Address: ${params.repoUrl}"
                echo "Repo Branch: ${params.repoBranch}"
                sh 'git --version'
                sh 'java -version'
                sh 'gradle --version'
            }
        }

        stage('Preparation') {
            steps {
                echo '//---------  Preparation ----------//'
                git branch: params.repoBranch, url: params.repoUrl
            }
        }

        stage('Build') {
            steps {
                echo '//---------  Build ----------//'
                sh "gradle clean build"
            }
        }

        stage('JUnit Test Reports') {
            steps {
                echo '//---------  JUnit Test Reports ----------//'
                junit '**/build/test-results/test/*.xml'
            }
        }

        stage('SonarQube Analysis') {
            // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-gradle/
            steps {
                echo '//---------  SonarQube Analysis ----------//'
                withSonarQubeEnv('SonarQube') {
                    sh "gradle sonarqube -x test --info"
                }
            }
        }

        /*stage('Quality Gate') { //忽略，状态无法返回给Jenkins
            // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-jenkins/
            steps {
                echo '//---------  Quality Gate ----------//'
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
                echo '//---------  Automated Testing ----------//'
                echo '执行自动化测试脚本, 待完善...(未来)'
            }
        }

        stage('Performance Testing') {
            steps {
                // TODO 执行JMeter性能测试
                echo '//---------  Performance Testing ----------//'
                echo '执行JMeter性能测试, 待完善...(未来)'
            }
        }

        stage('Deployment') {
            steps {
                echo '//---------  Deployment ----------//'
                script {
                    sh 'docker stop sb2-web-plat'
                    sh 'docker rm sb2-web-plat'
                    sh 'docker build -t local/sb2-web-plat:latest -f ./Dockerfile ./sb2-web-plat/build/libs/'
                    sh 'docker run --name sb2-web-plat -p 9090:8080 -d local/sb2-web-plat:latest'
                }

            }
        }

        stage('Publish') {
            steps {
                echo '//---------  Publish ----------//'
                script {
                    /*def projectVersion = sh (
                        script: "gradle properties -q | grep \"version:\" | awk '{print \$2'}",
                        returnStdout: true
                    )*/
                    // TODO 发布镜像到Harbor
                }
            }
        }

        //--------------- END ---------------//
    }

    post() {
        always {
            // Notification
            echo '//---------  Notification ----------//'
            mail to: "account@incarcloud.com", //for many with ',' separate
                subject: "Jenkins Notification: ${env.JOB_NAME}(${BUILD_DISPLAY_NAME}) ",
                body: "Hi all,\n      The job of ${env.JOB_NAME}(${BUILD_DISPLAY_NAME}) has been completed!\n      Please visit ${env.JOB_URL} for inspection.\n\n      Thanks for your attention!"
        }
    }
}