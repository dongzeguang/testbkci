package sogou.springboot.sample.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleController {
	@Autowired
	private RestTemplate restTemplate;
	@Value("${application.healthz-min-time}")
	private Integer minTime;
	@Value("${application.healthz-max-time}")
	private Integer maxTime;

	private static final String[] HeaderKeys = { "x-request-id", "x-b3-traceid", "x-b3-spanid", "x-b3-parentspanid",
			"x-b3-sampled", "x-b3-flags", "x-ot-span-context" };

	@RequestMapping("/")
	public Object home(@RequestHeader HttpHeaders headers) {
		System.out.println("############/::headers======" + headers.toSingleValueMap().toString());
		return "{\"name\":\"sample-web:35001\",\"version\":\"1.0.0\",\"message\":\"This is sample web application!\"}";
	}

	private HttpHeaders passTracingHeader(HttpHeaders headers) {
		HttpHeaders tracingHeaders = new HttpHeaders();
		for (String headerKey : HeaderKeys) {
			extractHeader(headers, tracingHeaders, headerKey);
		}

		return tracingHeaders;
	}

	private void extractHeader(HttpHeaders headers, HttpHeaders extracted, String key) {
		List<String> vals = headers.get(key);
		if (vals != null && !vals.isEmpty()) {
			extracted.put(key, Arrays.asList(vals.get(0)));
		}
	}

	private Map<String, String> passTracingHeader2Map(HttpHeaders headers) {
		Map<String, String> headerMap = new HashMap<>();
		for (String headerKey : HeaderKeys) {
			extractHeader2Map(headers, headerMap, headerKey);
		}
		return headerMap;
	}

	private void extractHeader2Map(HttpHeaders headers, Map<String, String> headerMap, String key) {
		List<String> vals = headers.get(key);
		if (vals != null && !vals.isEmpty()) {
			headerMap.put(key, vals.get(0));
		}
	}

	@RequestMapping("/healthz")
	public Object healthz() throws InterruptedException {
		long begin = System.currentTimeMillis();
		int bound = maxTime - minTime;
		if (bound > 0) {
			int threadSleepMilliseconds = new Random().nextInt(bound) + minTime;
			Thread.sleep(threadSleepMilliseconds);
		}
		return Collections.singletonMap("costTime(ms)", System.currentTimeMillis() - begin);
	}

}
