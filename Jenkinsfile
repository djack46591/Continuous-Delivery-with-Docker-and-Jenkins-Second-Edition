pipeline {
    // Runs the pipeline in an agent container 
    agent {
        docker { 
            image 'eclipse-temurin:11-jdk' 
        }
    }
    
    // Polls GitHub every minute
    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage("Compile & Unit Test") {
            steps {
                // Runs every time
                sh "cd Chapter08/sample1 && ./gradlew compileJava"
                sh "cd Chapter08/sample1 && ./gradlew test"
            }
        }
        
        stage("Code Coverage and Checkstyle") {
            // ONLY runs if a .java file is modified
            when {
                changeset "**/*.java"
            }
            steps {
                sh "cd Chapter08/sample1 && ./gradlew jacocoTestReport"
                sh "cd Chapter08/sample1 && ./gradlew jacocoTestCoverageVerification"
                sh "cd Chapter08/sample1 && ./gradlew checkstyleTest || true"
                sh "cd Chapter08/sample1 && ./gradlew checkstyleMain"
            }
        }

        stage("Package") {
            steps {
                // Runs every time
                sh "cd Chapter08/sample1 && ./gradlew build"
            }
        }
    }

    post {
        success {
            echo "pipeline ran perfectly"
        }
        failure {
            echo "pipeline failure"
        }
    }
}
