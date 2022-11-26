package hello.core.singleton;

public class SingletoneService {

    // 여기서는 객체를 미리 생성해두는 가장 단순하고 안전한 방법을 선택
    // 싱글톤 패턴을 적용하면 곡객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
    // 이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
    // 하지만 싱글톤 패턴도 수 많은 문제점을 가지고 있다
    // 1. 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다
    // 2. 의존 관계상 클라이언트가 구체 클래스에 의존한다 >> DIP 위반
    // 3. 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다
    // 4. 테스트하기 어렵다
    // 5. 내부 속성을 변경하거나 초기화 하기 어렵다
    // 6. private 생성자로 자식 클래스를 만들기 어렵다
    // 7. 결론적으로 유연성이 떨어진다
    // 8. 안티패턴으로 불리기도 한다
    private static final SingletoneService instance = new SingletoneService();

    public static SingletoneService getInstance() {
        return instance;
    }

    private SingletoneService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    public static void main(String[] args) {

    }
}
