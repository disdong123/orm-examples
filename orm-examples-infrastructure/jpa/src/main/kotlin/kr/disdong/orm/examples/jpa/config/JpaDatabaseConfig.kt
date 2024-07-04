package kr.disdong.orm.examples.jpa.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "jpaEntityManagerFactory",
    transactionManagerRef = "jpaTransactionManager",
    basePackages = ["kr.disdong.orm.examples.jpa"]
)
class JpaDatabaseConfig {

    companion object {
        private const val BASE_PACKAGE = "kr.disdong.orm.examples.jpa"
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.jpa-datasource")
    fun jpaDataSource(): DataSource =
        DataSourceBuilder.create().build()

    @Bean(name = ["jpaEntityManagerFactory"])
    fun jpaEntityManagerFactory(
        builder: EntityManagerFactoryBuilder,
        @Qualifier("jpaDataSource") dataSource: DataSource?,
    ): LocalContainerEntityManagerFactoryBean {
        return builder.dataSource(dataSource).packages(BASE_PACKAGE).build()
    }

    @Bean(name = ["jpaTransactionManager"])
    fun jpaTransactionManager(
        @Qualifier("jpaEntityManagerFactory") mfBean: LocalContainerEntityManagerFactoryBean,
    ): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = mfBean.getObject()
        return transactionManager
    }
}
