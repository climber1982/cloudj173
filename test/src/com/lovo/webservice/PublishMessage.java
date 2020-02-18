package com.lovo.webservice;

import javax.jws.WebService;

@WebService
public class PublishMessage implements IPublishMessage {
    @Override
    public String publishInfo() {
        return "hello ms";
    }

    @Override
    public String getInfo(int tag) {
        switch (tag) {
            case 1:
                return "hello zy";
            case 2:
                return "hello zf";
        }
        return "hello no message";
    }
}
