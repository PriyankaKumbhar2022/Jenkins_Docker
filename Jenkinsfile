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
        stage('Checkout/PullCode') {
            steps {
                git branch: "${params.BRANCH_NAME}", credentialsId: 'MY_API_KEY', url: "${params.REPO_URL}"
                echo 'Pull successfully .'
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

            // Collect additional information
        def changes = currentBuild.changeSets.collect { 
            it.items.collect { 
                "Commit: ${it.commitId} - Message: ${it.msg}" 
            } 
        }.flatten().join('\n')  // Collect commit messages

        // Construct email body
        def emailBody = """
            Build Result: ${currentBuild.currentResult}
            Job Name: ${env.JOB_NAME}
            Build Number: ${env.BUILD_NUMBER}
            More info at: ${env.BUILD_URL}

            Commit Messages:
            ${changes}

            Last 100 Lines of Build Log:
            ${buildLog}
        """



            emailext(
                body: emailBody,
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                to: 'kumbharpriyanka043@gmail.com'
            )
        }
    }

}