package com.kaleyra.springbootdemo;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
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
@JacksonXmlRootElement
@Data
public class ListResponsePayload<T> {

    int payloadSize;

    List<T> payload;
}
