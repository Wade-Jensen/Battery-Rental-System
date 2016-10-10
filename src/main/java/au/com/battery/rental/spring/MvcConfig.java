package au.com.battery.rental.spring;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import au.com.battery.rental.validation.EmailValidator;
import au.com.battery.rental.validation.PasswordMatchesValidator;
import nz.net.ultraq.thymeleaf.LayoutDialect;

@Configuration
@ComponentScan(basePackages = { "au.com.battery.rental.web", "au.com.battery.rental.rest" })
@PropertySource({ "classpath:application.properties" })
@PropertySource({"classpath:version.properties" })
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter   {
	
    @Autowired
    private Environment env;
	
    public MvcConfig() {
        super();
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.addViewController("/security/login");
        registry.addViewController("/security/register.html");
        registry.addViewController("/security/logout.html");        
        registry.addViewController("/security/expiredAccount.html");
        registry.addViewController("/security/badUser.html");
        registry.addViewController("/security/emailError.html");
        registry.addViewController("/index.html");
        registry.addViewController("/security/invalidSession.html");        
        registry.addViewController("/security/successRegister.html");
        registry.addViewController("/security/forgetPassword.html");
        registry.addViewController("/security/updatePassword.html");
        registry.addViewController("/security/changePassword.html");
        registry.addViewController("/dashboard/dashboard.html");
        registry.addViewController("/greeting/greeting.html"); //TODO remove
        registry.addViewController("/testForm/testForm.html"); //TODO remove
        //registry.addViewController("/greeting/result.html");
        //registry.addViewController("/builder/newbuildplan/project/{projectId}/company/{companyId}");
    }

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }


    @Bean
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
        cookieLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        return cookieLocaleResolver;
    }

    @Bean
    public MessageSource messageSource() {
    	final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
    	messageSource.setBasename("classpath:messages");
    	messageSource.setUseCodeAsDefaultMessage(true);
    	messageSource.setDefaultEncoding("UTF-8");
    	messageSource.setCacheSeconds(0);
    	return messageSource;
    }

    @Bean
    public EmailValidator usernameValidator() {
        return new EmailValidator();
    }

    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }
    
    @Bean
    @Description("Thymeleaf template resolver serving HTML 5")
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        
        //TODO: This should be driven from the properties file
        templateResolver.setTemplateMode("LEGACYHTML5");
        
        String cache = env.getProperty("spring.thymeleaf.cache");
        
        if (cache == null || cache.toUpperCase().equals("TRUE"))
        	templateResolver.setCacheable(true);
        else
        	templateResolver.setCacheable(false);
                
        return templateResolver;
    }    
    
    @Bean
    @Description("Thymeleaf template engine with Spring integration")
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
    
    @Bean
    @Description("Thymeleaf view resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        
        return viewResolver;
    }
    
    @Bean(name = "version")
    public String getBuildNumber() {
    	
    	String version = "Build Version Unknown";
    	
    	try {
    		version = env.getProperty("version");
    	} catch (Exception ex) { }
    	
    	return version;
    }   
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
    }
    
}