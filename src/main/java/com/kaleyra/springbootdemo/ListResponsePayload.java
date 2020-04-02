package com.kaleyra.springbootdemo;

import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * {
 *     "size": 30,
 *     "payload": [{..}, {...}]
 * }
 *
 *
 *
 * @param <T>
 */
@ResponseBody
//@JacksonXmlRootElement
@Data
public class ListResponsePayload<T> {

    int payloadSize;

    List<T> payload;
}
