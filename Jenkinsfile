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
                echo 'Pull successfully.'
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
<<<<<<< HEAD
    post {
    always {
        echo 'One way or another, I have finished'

        // Initialize changes variable
        def changes = currentBuild.changeSets.collect { changeSet ->
            changeSet.items.collect { item ->
                "<li>Commit: ${item.commitId} - Message: ${item.msg}</li>"
            }
        }.flatten().join('\n')  // Collect commit messages

        // Construct email body with HTML
        def emailBody = """ 
            <html>
            <body>

                <p>Hi Team</p>
                <h2>For the updated code Build Result is: ${currentBuild.currentResult}</h2>
                <p><strong>Job Name:</strong> ${env.JOB_NAME}</p>
                <p><strong>Build Number:</strong> ${env.BUILD_NUMBER}</p>
                <p><strong>More info at:</strong> <a href="${env.BUILD_URL}">${env.BUILD_URL}</a></p>

                <h3>Commit Messages:</h3>
                <ul>
                    ${changes}
                </ul>
            </body>
            </html>
        """


        emailext(
            body: emailBody,
            subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
            to: 'kumbharpriyanka043@gmail.com',
            mimeType: 'text/html'  // Specify that the body is HTML
        )
    }
}

=======
   post {
    always {
        echo 'One way or another, I have finished'

        script {
            // Initialize changes variable
            def changes = currentBuild.changeSets.collect { changeSet ->
                changeSet.items.collect { item ->
                    "<li>Commit: ${item.commitId} - Message: ${item.msg}</li>"
                }
            }.flatten().join('\n')  // Collect commit messages

            // Construct email body with HTML
            def emailBody = """ 
                <html>
                <body>
                    <p>Hi Team</p>
                    <h4>The updated Pushed Code Build Result: ${currentBuild.currentResult}</h4>
                    <p>Job Name: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p>More info at: <a href="${env.BUILD_URL}">${env.BUILD_URL}</a>
                    <h5>Commit Messages:</h5>
                    <ul>
                      ${changes}
                   </ul>
                </body>
                </html>
            """

            emailext(
                body: emailBody,
                subject: "Jenkins Build ${currentBuild.currentResult}: Job ${env.JOB_NAME}",
                to: 'Priyankak@siddhatech.com',
                from: "Estrella Devops <priyanka158725@gmail.com>",
                mimeType: 'text/html'  // Specify that the body is HTML
            )
        }
    }
}


>>>>>>> 338320e3246ec992efaa33d5756fd63a126e548c
}
