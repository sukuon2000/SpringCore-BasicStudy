package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterScan() {
        ApplicationContext ac =
                new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        assertThat(beanA).isNotNull();

        assertThrows(
                NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB", BeanB.class)
        );
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {}

    // FilterType 옵션
    // ANNOTATION: 기본값, 애노테이션을 인식해서 동작한다 ( org.example.SomeAnnotation )
    // ASSIGNABLE_TYPE : 지정한 타입과 자식 타입을 인식해서 동작한다 ( org.example.SomeClass) )
    // ASPECTJ : AspectJ 패턴 사용 ( org.example..*Service+ )
    // REGEX : 정규표현식 ( org\.example\.Default.* )
    // CUSTOM : TypeFilter 이라는 인터페이스를 구현해서 처리 ( org.example.MyTypeFilter )

    // 참고
    // @Component면 충분하기 떄문에, includeFilters 를 사용할 일은 거의 없다
    // @excludeFilters 는 여러가지 이유로 간혹 사용할 때가 있지만 많지는 않다

    // 최근 스프링 부트는 컴포넌트 스캔을 기본적으로 사용하는데
    // 김영한님 개인적으로는 옵션을 변경하면서 사용하기 보다는
    // 스프링의 기본 설정에 최대한 맞추어서 사용하는 것을 권장하고 선호하는 편이다
}
