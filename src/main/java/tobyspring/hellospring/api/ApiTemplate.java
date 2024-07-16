package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {
    // 템플릿
    public BigDecimal getExRate(String url, ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
        // 1. URI를 준비하고 예외처리 작업
        URI uri;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        // 2. API 실행 및 서버로 부터 받은 응답 가져오기
        String response;
        try {
            response = apiExecutor.execute(uri); // 콜백
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 3. JSON 문자열 파싱 및 필요한 정보 추출
        try {
            return exRateExtractor.extract(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
