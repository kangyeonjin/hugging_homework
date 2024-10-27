package com.yeonjin.hugging_intelij.section01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class WebClientService {

    private final WebClient webClient;

    private final String FAST_API_SERVER_URL = "http://localhost:8000";  // application.yml의 값을 직접 주입

    public WebClientService(WebClient.Builder webClientBuilder) {
        log.info("Translation API URL: {}", FAST_API_SERVER_URL);  // URL 확인용 로그

        this.webClient = webClientBuilder
                .baseUrl(FAST_API_SERVER_URL)
                .build();
    }

    public ResponseDTO translateText(RequestDTO requestDTO) {
        log.info("번역 요청 시작 - text: {}, lang: {}", requestDTO.getText(), requestDTO.getLang());

        return webClient.post()
                .uri("/translate")              // base url + uri에 해당하는 곳으로 요청을 보낸다.
                .bodyValue(requestDTO)              // body에 담을 값
                .retrieve()                         // 요청 보내기
                .bodyToMono(ResponseDTO.class)      // 응답 본문을 지정된 타입으로 변환 (JSON -> ResponseDTO) 역직렬화
                .doOnSuccess(response -> log.info("번역 완료 - result: {}", response.getResult()))
                // 성공시 수행할 적업 (대부분 로깅 후 처리에 사용한다.)
                .doOnError(error -> log.error("번역 API 호출 중 오류 발생", error))
                // 오류 발생시 처리할 작업 (로깅 및 예외처리에 사용한다.)
                .block();  // 비동기 작업을 동기식으로 변환, 결과가 반환될 때까지 대기
    }

}
