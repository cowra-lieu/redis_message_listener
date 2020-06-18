package com.btw.demo.redis_message_listener;

import java.util.List;

public class IcerUpdate {

    private boolean smallUpdate = false;
    private List<String> rawDataList;

    public boolean isSmallUpdate() {
        return smallUpdate;
    }

    public void setSmallUpdate(boolean smallUpdate) {
        this.smallUpdate = smallUpdate;
    }

    public List<String> getRawDataList() {
        return rawDataList;
    }

    public void setRawDataList(List<String> rawDataList) {
        this.rawDataList = rawDataList;
    }

}
