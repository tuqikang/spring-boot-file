package cn.tqktqk.study.bootfiledemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootFileDemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(System.getProperties().stringPropertyNames());
    }

}
