pipeline {

    agent any

    parameters {
        string(name: 'repoUrl', defaultValue: 'https://github.com/aaric/spring-boot-2-achieve', description: 'Git Repo Address')
        string(name: 'repoBranch', defaultValue: 'master', description: 'Git Repo Branch')
        string(name: 'repoCredentialsId', defaultValue: 'auth-git-web', description: 'Git Repo Auth')
        string(name: 'registryHostname', defaultValue: 'linux7-2:5000', description: 'Registry Hostname')
        string(name: 'registryAuthLogin', defaultValue: 'aaric', description: 'Registry Login')
        string(name: 'registryAuthSecret', defaultValue: 'aaricT01', description: 'Registry Login Secret')
    }

    tools {
        jdk  'jdk8'
        gradle 'gradle-5.2.1'
    }

    environment {
        HELLO_CUSTOM_TITLE = 'Hello, Jenkins Pipeline!'
    }

    stages {
        //-------------------- BEGIN --------------------//
        stage('Preparation') {
            steps {
                echo "${env.HELLO_CUSTOM_TITLE}"

                echo '//---------  Preparation ----------//'
                git branch: params.repoBranch, credentialsId: params.repoCredentialsId, url: params.repoUrl
            }
        }

        stage('Build') {
            steps {
                echo '//---------  Build ----------//'
                sh "gradle clean build"
            }
        }

        stage('Reports') {
            steps {
                echo '//---------  Reports ----------//'
                junit '**/build/test-results/test/*.xml'
                checkstyle canComputeNew: false, defaultEncoding: '', healthy: '', pattern: '**/build/reports/checkstyle/*.xml', unHealthy: ''
            }
        }

        stage('SonarQube Analysis') {
            // https://docs.sonarqube.org/7.8/analysis/scan/sonarscanner-for-gradle/
            steps {
                echo '//---------  SonarQube Analysis ----------//'
                withSonarQubeEnv('SonarQube') {
                    sh "gradle sonarqube --info"
                }
            }
        }

        /*stage('Automated Testing') {
            steps {
                // TODO 执行自动化测试脚本
                echo '//---------  Automated Testing ----------//'
                echo '执行自动化测试脚本, 待完善...(未来)'
            }
        }*/

        /*stage('Performance Testing') {
            steps {
                // TODO 执行JMeter性能测试
                echo '//---------  Performance Testing ----------//'
                echo '执行JMeter性能测试, 待完善...(未来)'
            }
        }*/

        stage('Deployment') {
            steps {
                echo '//---------  Deployment ----------//'
                script {
                    // 编译镜像并运行容器
                    def deployPkgName = 'sb2-web-plat'
                    def deployPkgVersion = sh (
                        script: "gradle properties -q | grep \"version:\" | awk '{print \$2'}",
                        returnStdout: true
                    ).trim()
                    sh "docker build --build-arg deployPkg=${deployPkgName}-${deployPkgVersion}.jar -t local/${deployPkgName}:${deployPkgVersion} ./${deployPkgName}/build/libs -f ./Dockerfile"

                    // 如果旧容器正在运行则停止
                    def dockerPsId = sh (
                        script: "docker ps | grep ${deployPkgName} | awk '{print \$1}'",
                        returnStdout: true
                    )?.trim()
                    if (dockerPsId) {
                        sh "docker stop ${deployPkgName}"
                    }

                    // 如果旧容器存储则删除
                    def dockerPsAId = sh (
                        script: "docker ps -a | grep ${deployPkgName} | awk '{print \$1}'",
                        returnStdout: true
                    )?.trim()
                    if (dockerPsAId) {
                        sh "docker rm ${deployPkgName}"
                    }

                    // 运行编译好的新容器
                    sh "docker run --name ${deployPkgName} -p 9090:8080 -d local/${deployPkgName}:${deployPkgVersion}"
                }

            }
        }

        stage('Publish') {
            steps {
                echo '//---------  Publish ----------//'
                script {
                    // 发布镜像到Harbor
                    def deployPkgName = 'sb2-web-plat'
                    def deployPkgVersion = sh (
                        script: "gradle properties -q | grep \"version:\" | awk '{print \$2'}",
                        returnStdout: true
                    ).trim()
                    sh "docker login ${params.registryHostname} -u ${params.registryAuthLogin} -p ${params.registryAuthSecret}"
                    sh "docker tag local/${deployPkgName}:${deployPkgVersion} ${params.registryHostname}/dev/${deployPkgName}"
                    sh "docker tag local/${deployPkgName}:${deployPkgVersion} ${params.registryHostname}/dev/${deployPkgName}:${deployPkgVersion}"
                    sh "docker push ${params.registryHostname}/dev/${deployPkgName}"
                    sh "docker push ${params.registryHostname}/dev/${deployPkgName}:${deployPkgVersion}"

                    // 删除已发布的镜像
                    sh "docker rmi ${params.registryHostname}/dev/${deployPkgName}"
                    sh "docker rmi ${params.registryHostname}/dev/${deployPkgName}:${deployPkgVersion}"
                }
            }
        }

        //-------------------- END --------------------//
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