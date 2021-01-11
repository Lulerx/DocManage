package com.bazn.document.bean;

import java.util.List;

/**
 * @author itle
 * @version 1.0
 * @date 2020/11/26
 */
public class Navigation {
    private Long id;
    private String title;
    private String path;
    private String name;
    private String icon;
    private String component;
    private Integer parent_id;
    private boolean enabled;
    private List<Navigation> children;

    public List<Navigation> getChildren() {
        return children;
    }

    public void setChildren(List<Navigation> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
