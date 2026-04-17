package org.dromara.system.domain.vo;

import org.dromara.system.domain.SysMenu;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 菜单权限视图对象 sys_menu
 *
 * @author Michelle.Chung
 */
@Data
@AutoMapper(target = SysMenu.class)
public class SysMenuVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String queryParam;

    /**
     * 是否为外链（0是 1否）
     */
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private String isCache;

    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    private String status;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单标题（多语言 key）
     */
    private String title;

    /**
     * 激活图标
     */
    private String activeIcon;

    /**
     * 激活路径
     */
    private String activePath;

    /**
     * 是否固定标签页（0 否 1 是）
     */
    private Integer affixTab;

    /**
     * 固定标签页顺序
     */
    private Integer affixTabOrder;

    /**
     * 徽标内容
     */
    private String badge;

    /**
     * 徽标类型（dot/normal）
     */
    private String badgeType;

    /**
     * 徽标颜色
     */
    private String badgeVariants;

    /**
     * 是否隐藏子菜单（0 否 1 是）
     */
    private Integer hideChildren;

    /**
     * 是否隐藏面包屑（0 否 1 是）
     */
    private Integer hideBreadcrumb;

    /**
     * 是否隐藏标签栏（0 否 1 是）
     */
    private Integer hideTab;

    /**
     * 外链/内嵌地址
     */
    private String linkSrc;

    /**
     * 最大打开标签数
     */
    private Integer maxOpenTab;

    /**
     * 重定向地址
     */
    private String redirect;

    /**
     * 是否在菜单中隐藏（0 否 1 是）
     */
    private Integer hideInMenu;

    /**
     * 创建部门
     */
    private Long createDept;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 子菜单
     */
    private List<SysMenuVo> children = new ArrayList<>();

}
