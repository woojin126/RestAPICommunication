package study.RestApiCommunicate.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseUserDto<T> {

    private int statusCode;
    private T data;

    public ResponseUserDto(int statusCode) {
        this.statusCode = statusCode;
    }

    public ResponseUserDto(int statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }
}
