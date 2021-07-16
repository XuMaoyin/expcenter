package cn.ncist.glx.expcenter.home;

import cn.ncist.glx.expcenter.home.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class HomeApplicationTests {

    @Resource
    CourseService courseService;

    @Test
    void contextLoads() {

        courseService.today();
    }

}
