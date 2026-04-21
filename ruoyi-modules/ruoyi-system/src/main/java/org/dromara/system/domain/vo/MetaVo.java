package org.dromara.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MetaVo {

    private String title;
    private String icon;
    private Boolean noCache;
    private String link;

    /** 激活图标 */
    private String activeIcon;
    /** 激活路径（菜单高亮） */
    private String activePath;
    /** 是否固定标签页 */
    private Boolean affixTab;
    /** 固定标签页排序 */
    private Integer affixTabOrder;
    /** 徽标内容 */
    private String badge;
    /** 徽标类型（dot/normal） */
    private String badgeType;
    /** 徽标颜色 */
    private String badgeVariants;
    /** 是否隐藏子菜单 */
    private Boolean hideChildrenInMenu;
    /** 是否隐藏面包屑 */
    private Boolean hideInBreadcrumb;
    /** 是否隐藏标签栏 */
    private Boolean hideInTab;
    /** 内嵌页面地址 */
    private String iframeSrc;
    /** 最大打开标签数 */
    private Integer maxNumOfOpenTab;
    /** 是否在菜单中隐藏 */
    private Boolean hideInMenu;
    /** 菜单排序 */
    private Integer order;

    public MetaVo(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public MetaVo(String title, String icon, Boolean noCache) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
    }

    public MetaVo(String title, String icon, String link) {
        this.title = title;
        this.icon = icon;
        this.link = link;
    }

    public MetaVo(String title, String icon, Boolean noCache, String link) {
        this.title = title;
        this.icon = icon;
        this.noCache = noCache;
        if (link != null && (link.startsWith("http://") || link.startsWith("https://"))) {
            this.link = link;
        }
    }

    public MetaVo() {

    }
}
