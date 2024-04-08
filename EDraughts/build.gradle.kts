plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    //DriverManager library mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.33")
    //mySQL Server Driver
    implementation("com.microsoft.sqlserver:mssql-jdbc:6.1.0.jre7")
}

tasks.test {
    useJUnitPlatform()
}