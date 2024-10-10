  pipeline {
    agent any
    parameters {
        string(name: 'BRANCH_NAME', defaultValue: 'feature/Priyanka', description: 'Branch to build')
        string(name: 'REPO_URL', defaultValue: 'https://github.com/PriyankaKumbhar2022/Jenkins_Docker.git', description: 'Repository URL')
    }
     environment {
        KATALON_PROJECT_PATH = 'D:/JenkinsDocker/Jenkins_Docker/EStrella/EStrella.prj' 
        KATALON_STUDIO_ENGINE = 'C:/Users/Dell/Downloads/Katalon_Studio_Engine_Windows_64-9.6.0/Katalon_Studio_Engine_Windows_64-9.6.0'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: "${params.BRANCH_NAME}", url: "${params.REPO_URL}"
            }
        }
        stage("API key") {
            steps {
                // Use withCredentials to access MY_API_KEY
                withCredentials([string(credentialsId: 'MY_API_KEY', variable: 'API_KEY')]) {
                    echo 'API Key has been retrieved successfully.'
                }
            }
        }
        stage('Build') {
            steps {
                lock('my-resource') {
                    // Your build steps here
                    echo 'Building...'
                }
            }
        }
        stage('PullCode') {
            steps {
                git branch: 'feature/Priyanka', credentialsId: 'd81cca46-fbfd-4f68-ae91-eafa2124d7a0', url: 'https://github.com/PriyankaKumbhar2022/Jenkins_Docker.git'
                echo 'Pull successfully .'
            }
        }
        stage('TestSuitExecution') {
            steps {
                withCredentials([string(credentialsId: 'MY_API_KEY', variable: 'API_KEY')]) {
                bat """
                cd ${env.KATALON_STUDIO_ENGINE}
                katalonc -noSplash -runMode=console -projectPath="${env.KATALON_PROJECT_PATH}" -retry=0 -testSuitePath="Test Suites/TS_Prelogin" -browserType="Android" -deviceId="emulator-5554" -executionProfile="default" -apiKey="${API_KEY}"
                """
                }
            }
        }
    }
 
     post {
    always {
        echo 'One way or another, I have finished'
    }
    success {
        emailext(
            subject: "Build Successful: ${currentBuild.fullDisplayName}",
            body: "Good news! The build was successful.\nCheck console output at ${env.BUILD_URL} to view the results.",
            to: 'kumbharpriyanka043@gmail.com'
        )
        echo 'Build succeeded!'
    }
    failure {
        emailext(
            subject: "Build Failed: ${currentBuild.fullDisplayName}",
            body: "Unfortunately, the build has failed.\nCheck console output at ${env.BUILD_URL} to view the results.",
            to: 'kumbharpriyanka043@gmail.com'
        )
        echo 'Build failure!'
    }
    unstable {
        emailext(
            subject: "Build Successful: ${currentBuild.fullDisplayName}",
            body: "The Build is unstable.\nCheck console output at ${env.BUILD_URL} to view the results.",
            to: 'kumbharpriyanka043@gmail.com'
        )
             echo 'Build Unstable!'  
    }  
    changed {  
        emailext(
            subject: "Build Successful: ${currentBuild.fullDisplayName}",
            body: "The state of the Pipeline has changed.\nCheck console output at ${env.BUILD_URL} to view the results.",
            to: 'kumbharpriyanka043@gmail.com'
        )
             echo 'The Pipeline was previously failing but is now successful'  
         }  
    }
}