package sogou.springboot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import sogou.springboot.sample.controller.SampleController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootWebApplication.class)
//@WebAppConfiguration
public class SpringBootSampleApplicationTests {
	
	@Autowired
	SampleController sampleController;

	@Test
	public void contextLoads() {

	}
	
	@Test
	public void testController() {
		try {
//			RestTemplate rest = new RestTemplate();
//			String response = rest.getForObject("http://localhost:35001/", String.class);
//			System.out.println("response: " + response);
//			Object o = sampleController.healthz();
//			System.out.println(o);
			System.out.print("test controller");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
