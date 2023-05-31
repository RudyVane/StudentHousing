package com.example.studenthousing;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Value;


@Configuration
@EnableJpaRepositories(basePackages = {"com.example.studenthousing"})
public class AppConfig {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean factoryBean = new LocalEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("StudentHousing");

        return factoryBean;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }


    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    public class EmailConfig {
        private final String senderEmail = "codegorilla9@gmail.com"; // Replace with your sender email
        private final String senderPassword = "CGA@2023"; // Replace with your sender email password
        private final String smtpHost = "smtp.gmail.com"; // Replace with your SMTP host (e.g., "smtp.gmail.com")
        private final int smtpPort = 587; // Replace with your SMTP port (e.g., 587 for TLS)

        @Bean
        public EmailSender emailSender() {
            return new EmailSender(senderEmail, senderPassword, smtpHost, smtpPort);
        }
    }

}