package com.kaleyra.springbootdemo;

import lombok.Data;

import java.util.Date;

@Data
public class Order {

    Long id;
    Date date;
    OrderUser user;
}
