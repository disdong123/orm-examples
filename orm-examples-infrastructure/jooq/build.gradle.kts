plugins {
    id("nu.studer.jooq") version "9.0"
}

dependencies {
    implementation(project(":orm-examples-domain"))
    jooqGenerator(libs.mysql.connector.java)
    implementation("org.jooq:jooq")
    implementation("org.jooq:jooq-meta")
    implementation("org.jooq:jooq-codegen")
}

jooq {
    version.set("3.18.4")

    configurations {
        create("main") {
            generateSchemaSourceOnCompilation.set(true)
            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = "jdbc:mysql://localhost:3306/orm_examples"
                    user = "root"
                    password = ""
                }
                generator.apply {
                    name = "org.jooq.codegen.KotlinGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        // inputSchema = "public"
                    }
                    generate.apply {
                        isDeprecated = false
                        isFluentSetters = true
                    }
                    target.apply {
                        packageName = "kr.disdong.orm.examples.jooq"
                        directory = "build/generated/jooq"
                    }
                    strategy.apply {
                        name = "org.jooq.codegen.DefaultGeneratorStrategy"
                    }
                }
            }
        }
    }
}
