package com.yeonjin.hugging_intelij.section01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestTemplateService {

    private final RestTemplate restTemplate;

    private final String FAST_API_SERVER_URL = "http://localhost:8000/translate";

    public RestTemplateService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public ResponseDTO translateText (RequestDTO requestDTO){
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<RequestDTO> entity = new HttpEntity<>(requestDTO, headers);

            ResponseEntity<ResponseDTO> response = restTemplate.exchange(
                    FAST_API_SERVER_URL, // 요청 url
                    HttpMethod.POST,     // http 요청 메서드
                    entity,              // 요청 entity (헤더 + 본문)
                    ResponseDTO.class    // 응답 본문을 변환할 타입 (JSON -> ResponseDTO)
            );

            log.info("=== 번역 서비스 응답 데이터 ===");
            log.info("번역 결과: {}", response.getBody().getResult());

            return response.getBody();
        } catch (RestClientException e) {
            log.error("번역 api 호출중 오류 발생");
            throw new RuntimeException(e);
        }
    }

}
