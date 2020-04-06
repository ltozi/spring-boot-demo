package com.kaleyra.springbootdemo;

import lombok.Data;

/**
 * //        {
 * //            "id": 0,
 * //                "category": {
 * //            "id": 0,
 * //                    "name": "string"
 * //        },
 * //            "name": "doggie",
 * //                "photoUrls": [
 * //            "string"
 * //         ],
 * //            "tags": [
 * //            {
 * //                "id": 0,
 * //                    "name": "string"
 * //            }
 * //         ],
 * //            "status": "available"
 * //        }
 */
@Data
public class Pet {

    Long id;

    Category category;

    String name;

    String[] photoUrls;

    Tag[] tags;

    String status;


}



