dependencies {
    implementation(project(":orm-examples-domain"))
    implementation(project(":orm-examples-infrastructure:jpa"))
    // implementation(project(":orm-examples-infrastructure:exposed"))
    implementation(project(":orm-examples-common"))
    implementation(libs.spring.boot.starter.web)
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}
