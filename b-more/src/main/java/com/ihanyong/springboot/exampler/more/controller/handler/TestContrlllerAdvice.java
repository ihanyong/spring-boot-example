package com.ihanyong.springboot.exampler.more.controller.handler;/**
 * Created by hanyong on 2017/6/6.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hanyong
 * @Date 2017/6/6
 */
@ControllerAdvice(basePackages = "com.ihanyong.springboot.exampler.more.controller")
public class TestContrlllerAdvice extends ResponseEntityExceptionHandler {
//public class TestContrlllerAdvice  {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestContrlllerAdvice.class);



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Resp handleConstraintViolationException(MethodArgumentNotValidException e) {

        LOGGER.warn("param not correct: {} ", e.getMessage());
        return new Resp("00001", "参数不正确");
    }

    public static class Resp {
        private String code;
        private String desc;

        public Resp(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}

