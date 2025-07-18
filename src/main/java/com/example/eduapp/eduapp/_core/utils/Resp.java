package com.example.eduapp.eduapp._core.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * ✅ 공통 응답 객체 (Generic)
 *
 * <p>
 * 컨트롤러에서 일관된 응답 포맷을 제공하기 위한 클래스입니다.
 * status, msg, data 세 가지 필드를 포함하며,
 * 성공과 실패 응답을 쉽게 만들 수 있는 정적 메서드를 제공합니다.
 * </p>
 *
 * @param <T> 응답 데이터의 타입
 */
@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T data;

    public static <B> ResponseEntity<Resp<B>> ok(B data) {
        Resp<B> resp = new Resp<>(HttpStatus.OK.value(), "성공", data);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    public static ResponseEntity<Resp<?>> fail(HttpStatus status, String msg) {
        Resp<?> resp = new Resp<>(status.value(), msg, null);
        return new ResponseEntity<>(resp, status);
    }

    public static Resp<?> fail(Integer status, String msg) {
        Resp<?> resp = new Resp<>(status, msg, null);
        return resp;
    }
}