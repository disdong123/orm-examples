dependencies {
    implementation(project(":template-domain"))
    implementation(project(":template-infrastructure:jpa"))
    implementation(project(":template-common"))
    implementation(libs.spring.boot.starter.web)
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
