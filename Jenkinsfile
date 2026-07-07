pipeline {
    agent {
        docker {
            image 'eclipse-temurin:11-jdk'
        }
    }

    stages {
        stage("Static Analysis") {
            when { not { branch 'master' } }
            steps {
                echo 'Running Static Analysis...'
                sh "cd Chapter08/sample1 && ./gradlew checkstyleMain"
                sh "cd Chapter08/sample1 && ./gradlew checkstyleTest || true"
            }
        }

        stage("Unit & Integration Tests") {
            when { not { branch 'master' } }
            steps {
                echo 'Running Unit Tests...'
                sh "cd Chapter08/sample1 && ./gradlew compileJava"
                sh "cd Chapter08/sample1 && ./gradlew test"
            }
        }

        stage("Code Coverage") {
            when { branch 'master' }
            steps {
                echo 'Running Jacoco Code Coverage...'
                sh "cd Chapter08/sample1 && ./gradlew jacocoTestReport"
                sh "cd Chapter08/sample1 && ./gradlew jacocoTestCoverageVerification"
            }
        }

        stage("Package") {
            steps {
                sh "cd Chapter08/sample1 && ./gradlew build"
            }
        }
    }

    post {
        always {
            jacoco execPattern: 'Chapter08/sample1/build/jacoco/**/*.exec',
                   classPattern: 'Chapter08/sample1/build/classes/java/main',
                   sourcePattern: 'Chapter08/sample1/src/main/java'
        }
        success {
            echo "tests pass!"
        }
        failure {
            echo "tests fail!"
        }
    }
}
