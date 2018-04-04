package com.msg.mc.model.qbzz;

/**
 * @author cloudy ［Cloudy.liu@qibaozz.com］
 * @version 1.0
 * @company 前海企保科技（深圳）有限公司
 * @date 17/6/7 上午10:14
 */
public class Users {
    private Integer id;
    private String email;
    private String name;
    private String cellphone;
    private String status;
    private String companiesWorked;
    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompaniesWorked() {
        return companiesWorked;
    }

    public void setCompaniesWorked(String companiesWorked) {
        this.companiesWorked = companiesWorked;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Users [id=" + id
            + ",email=" + email
            + ",name=" + name
            + ",cellphone=" + cellphone
            + ",status=" + status
            + ",companiesWorked=" + companiesWorked
            + ",companyId=" + companyId
            + "]";
    }
}
