package com.msg.mc.model.qbzz;


/**
 * @author cloudy
 * @version 1.0
 * @date 17/6/7 上午10:14
 */
public class BaseCompany {
    private Integer id;
    private Integer cid;
    private String name;
    private String abbr;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "BaseCompany [id=" + id
                + ",cid=" + cid
                + ",name=" + name
                + ",abbr=" + abbr
                + ",address=" + address
                + "]";
    }
}
