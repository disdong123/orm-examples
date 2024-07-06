dependencies {
    implementation(project(":orm-examples-domain"))
    implementation(libs.spring.boot.starter.data.jdbc)
    runtimeOnly(libs.mysql.connector.java)
}
