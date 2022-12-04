package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// 지금까지 스프링 빈(Bean)을 등록할 떄 구성파일에 @Bean을 사용했는데
// 관리할 Bean이 많아지면 관리하기 번거로워 진다

// @Component 와 @ComponentScan을 사용하면 굳이 Bean 설정파일을 만들지 않거나,
// @Bean을 안쓰고 Bean 등록을 할 수 있다

// 설정파일이 없다면 @Autowired를 사용해 자동 의존관계 주입을 한다
// **탐색 위치와 기본 스캔 대상 권장 방법**
// 패키지 위치를 지정하지 않고, 성정 정보 클래스 위치를 프로젝트 최상단에 둔다
@Configuration
@ComponentScan(
        // basePackages : 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 하위 패키지를 모두 탐색한다
        basePackages = "hello.core.member",
        // basePackageClasses : 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다
        basePackageClasses = AutoAppConfig.class,
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        // Configuration 클래스에는 이미 @Component 가 지정이 되어있기 때문에
        // @Configuration 을 명시한 클래스까지 ComponentScan 해버리면 앞서 만들어두었던 설정 정보도 함께 등록이 되어버린다.
        // 그래서 excludeFilters를 통해 @Configuration 을 명시한 설정정보는 스캔 대상에서 제외한다.
        // (보통 설정 정보를 컴포넌트 스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 유지하기 위해서)

)
// ComponentScan : @Component를 가진 모든 대상을 가져와서 Bean에 등록한다
//                  (Bean 설정파일 + @Bean 을 통해 Bean을 하나하나 지정할 필요가 없음)

// 컴포넌트 스캔 기본 대상
// 컴포넌트 스캔은 @Component 뿐만 아니라 다음 내용도 추가로 대상에 포함한다
// @Component : 컴포넌트 스캔에서 사용
// @Controller : 스프링 MVC 컨트롤러에서 사용, 스프링 MVC 컨트롤러로 인식
// @Service : 스프링 비즈니스 로직에서 사용, 사실 @Service는 특별한 처리를 하지 않는다. 대신 개발자들이 핵심 비즈니스 로직이 여기있겠구나 라고 비즈니스 계층을 인식하는데 도움
// @Repository : 스프링 데이터 접근 계층에서 사용, 스프링 데이터 접근 계층으로 인식하고, 데이터 계증의 예외를 스프링 예외로 반환해준다
// @Configuration : 스프링 설정 정보에서 사용, 스프링 설정 정보로 인식하고, 스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다
public class AutoAppConfig {

}
