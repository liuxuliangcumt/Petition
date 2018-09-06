package com.realpower.petition.net.param;

/**
 * Created by Administrator on 2017/12/15.
 */

public class AssociateParam {

    public AssociateParam(String address, int areaId, String idcard, int monitoredId, String name) {

        this.address = address;
        this.areaId = areaId;
        this.idcard = idcard;
        this.monitoredId = monitoredId;
        this.name = name;
    }

    /**
     * address : string
     * areaId : 0
     * idcard : string
     * monitoredId : 0
     * name : string
     */

    private String address;
    private int areaId;
    private String idcard;
    private int monitoredId;
    private String name;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public int getMonitoredId() {
        return monitoredId;
    }

    public void setMonitoredId(int monitoredId) {
        this.monitoredId = monitoredId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
