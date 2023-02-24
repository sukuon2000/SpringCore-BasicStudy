package hello.core.lifecycle;

//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient //implements InitializingBean, DisposableBean
    {
    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public  void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출 메서드
    public void connect() {
        System.out.println("connect : " + url);
    }
    public void call(String message) {
        System.out.println("call : " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close " + url);
    }

    // 상속받은 메서드
    // 의존관계 주입이 끝나면 호출
    @PostConstruct // 생성이 된 이후에
    public void init()  {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy // 소멸되기 전에
    public void close()  {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
