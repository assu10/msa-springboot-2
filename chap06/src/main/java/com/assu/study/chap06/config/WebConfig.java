package com.assu.study.chap06.config;

import com.assu.study.chap06.converter.HotelRoomNumberConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // 직렬화 과정에서 NON_EMPTY 로 설정한 ObjectMapper 객체를 생성
  // @Bean 과 @Primary 애너테이션을 사용하여 설정한 ObjectMapper 는 애플리케이션에 가장 우선적으로 스프링 빈으로 생성됨
  @Bean
  @Primary
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    return objectMapper;
  }

  //  @Override
//  public void configurePathMatch(PathMatchConfigurer configurer) {
//    configurer
//        // @RestController 애너테이션으로 정의된 컨트롤러 클래스들의 핸들러 메서드에 /v2 머릿말 자동으로 설정
//        .addPathPrefix("/v2", HandlerTypePredicate.forAnnotation(RestController.class))
//        .setPathMatcher(new AntPathMatcher())
//        .setUrlPathHelper(new UrlPathHelper());
//  }
//
//  // Accept 헤더 대신 URI 의 파라메터를 사용하여 request 분석
//  @Override
//  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//    configurer
//        // 컨텐츠 협상 기능을 사용하기 위한 URI 파라메터명을 contentType 으로 설정
//        .parameterName("contentType")
//        // Accept 헤더를 사용한 컨텐츠 협상 기능은 사용하지 않음
//        .ignoreAcceptHeader(true)
//        // 컨텐츠 협상 과정에서 적합한 값을 찾지 못하면 기본값 application/json 으로 설정
//        .defaultContentType(MediaType.APPLICATION_JSON)
//        // URI 파라메터 contentType 값이 json 이면 application/json 으로 간주
//        .mediaType("json", MediaType.APPLICATION_JSON)
//        // URI 파라메터 contentType 값이 xml 이면 application/xml 로 간주
//        .mediaType("xml", MediaType.APPLICATION_XML);
//  }
//
//  // 비동기 서블릿 설정
  @Override
  public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();

    // 스레드 풀의 이름은 Async-Executor 로 시작
    taskExecutor.setThreadNamePrefix("Async-Executor");
    // 기본 개수는 50개
    taskExecutor.setCorePoolSize(50);
    // 최대 100개까지 늘어남
    taskExecutor.setMaxPoolSize(100);
    // 스레드 풀의 대기열 크기는 300개
    taskExecutor.setQueueCapacity(300);
    // ThreadPoolTaskExecutor 스레드 풀을 초기화하려면 반드시 initialize() 호출
    taskExecutor.initialize();

    // 생성한 스레드 풀 셋팅
    configurer.setTaskExecutor(taskExecutor);
    // 비동기 처리 타임아웃
    configurer.setDefaultTimeout(10_000L);
  }

  //
//  // '/css' 경로로 요청하는 모든 정적 파일을 코드 베이스의 '/static/css' 경로에서 찾는 예시
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry.addResourceHandler("/css/**")
//        .addResourceLocations("classpath:/static/css");
//    registry.addResourceHandler("/html/**")
//        .addResourceLocations("classpath:/static/html");
//  }
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry
//        // 모든 리소스에 대해 CORS 적용
//        .addMapping("/**")
//        // 허용하는 출처는 www.spring.io
//        .allowedOrigins("www.spring.io")
//        .allowedMethods("GET", "POST")
//        .allowedHeaders("*")
//        .maxAge(24 * 60 * 60);
//  }
//
//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//    // 요청 URI 가 / 일 때 처리하는 뷰 이름은 main
//    registry.addViewController("/").setViewName("main");
//  }
//
  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new HotelRoomNumberConverter());
  }
//
//  @Override
//  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//    resolvers.add(new ClientInfoArgumentResolver());
//  }
//
//  @Bean(value = "localeResolver")
//  public LocaleResolver localeResolver() {
//    AcceptHeaderLocaleResolver acceptHeaderLocaleResolver = new AcceptHeaderLocaleResolver();
//    acceptHeaderLocaleResolver.setDefaultLocale(Locale.KOREAN);
//    return acceptHeaderLocaleResolver;
//  }

  // Accept 헤더값에 따라 json, xml 로 응답
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    converters.add(new MappingJackson2HttpMessageConverter());  // JSON 처리
    converters.add(new MappingJackson2XmlHttpMessageConverter()); // XML 처리
  }
}
