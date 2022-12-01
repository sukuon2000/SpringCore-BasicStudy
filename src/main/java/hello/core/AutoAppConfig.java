package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 지금까지 스프링 빈(Bean)을 등록할 떄 구성파일에 @Bean을 사용했는데
// 관리할 Bean이 많아지면 관리하기 번거로워 진다

// @Component 와 @ComponentScan을 사용하면 굳이 Bean 설정파일을 만들지 않거나,
// @Bean을 안쓰고 Bean 등록을 할 수 있다

// 설정파일이 없다면 @Autowired를 사용해 자동 의존관계 주입을 한다
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // Configuration 클래스에는 이미 @Component 가 지정이 되어있기 때문에
        // @Configuration 을 명시한 클래스까지 ComponentScan 해버리면 앞서 만들어두었던 설정 정보도 함께 등록이 되어버린다.
        // 그래서 excludeFilters를 통해 @Configuration 을 명시한 설정정보는 스캔 대상에서 제외한다.
        // (보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서)

)
// ComponentScan : @Component를 가진 모든 대상을 가져와서 Bean에 등록한다
//                  (Bean 설정파일 + @Bean 을 통해 Bean을 하나하나 지정할 필요가 없음)
public class AutoAppConfig {

}
