package com.mkdika.zkspringhb.ui.web;


import com.mkdika.zkspringhb.entity.PersonJob;
import com.mkdika.zkspringhb.helper.AppUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;


public class Menu01Vm {
    
    private final String appInfo = "ZK Spring Hibernate CRUD Single Table";

    private PersonJob selected;
    private List<PersonJob> lists;

    public Menu01Vm(){}
    
    @Init
    public void init() {
        getLists();
    }

    @Command
    @NotifyChange({"selected", "lists"})
    public void addClick() {
        setSelected(new PersonJob());
    }

    @Command
    @NotifyChange({"selected", "lists"})
    public void delClick() {
        if (getSelected() != null) {
            try {
                if (AppUtil.getWebService().delete(getSelected())) {
                    setSelected(null);
                    Clients.showNotification("Kamu Jelek.");
                } else {
                    Clients.showNotification("Delete failed.");
                }
            } catch (Exception e) {
                Clients.showNotification("Delete failed.\n" + e.getLocalizedMessage());
            }
        } else {
            Clients.showNotification("Record not found.");
        }
    }

    @Command
    @NotifyChange({"selected", "lists"})
    public void saveClick() {
        try {
            if (AppUtil.getWebService().save(getSelected())) {
                setSelected(null);
                getLists();
                Clients.showNotification("Save successful.");
            }
        } catch (Exception e) {
            Clients.showNotification("Save failed.\n" + e.getLocalizedMessage());
        }
    }

    @Command
    @NotifyChange({"selected", "lists"})
    public void cancelClick() {
        setSelected(null);
    }

    @Command
    public void infoClick() {
        Clients.showNotification(appInfo + "<br/>" + "First version writen by:<br/>Maikel Chandika (mkdika@gmail.com)");
    }

    @Command
    @NotifyChange("lists")
    public void refreshClick() {
        setLists(AppUtil.getWebService().getPersonJobs());
    }

    // ============== Setter & Getter ====================
    public PersonJob getSelected() {
        return selected;
    }

    public void setSelected(PersonJob selected) {
        this.selected = selected;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public List<PersonJob> getLists() {
        lists = AppUtil.getWebService().getPersonJobs();
        return lists;
    }

    public void setLists(List<PersonJob> lists) {
        this.lists = lists;
    }
}
