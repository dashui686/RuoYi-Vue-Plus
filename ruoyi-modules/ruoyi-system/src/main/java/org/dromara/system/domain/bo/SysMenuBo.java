package org.dromara.system.domain.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.constant.RegexConstants;
import org.dromara.common.json.validate.JsonPattern;
import org.dromara.common.json.validate.JsonType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.system.domain.SysMenu;

/**
 * 菜单权限业务对象 sys_menu
 *
 * @author Michelle.Chung
 */

@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SysMenu.class, reverseConvertGenerate = false)
public class SysMenuBo extends BaseEntity {

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过{max}个字符")
    private String menuName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Size(min = 0, max = 200, message = "路由地址不能超过{max}个字符")
    private String path;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 200, message = "组件路径不能超过{max}个字符")
    private String component;

    /**
     * 路由参数
     */
    @JsonPattern(type = JsonType.OBJECT, message = "路由参数必须符合JSON格式")
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
    @NotBlank(message = "菜单类型不能为空")
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 0, max = 100, message = "权限标识长度不能超过{max}个字符")
    @Pattern(regexp = RegexConstants.PERMISSION_STRING, message = "权限标识必须符合 tool:build:list 格式")
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
     * 是否在菜单中隐藏（0 否 1 是，与 visible 区分）
     */
    private Integer hideInMenu;

    /**
     * 备注
     */
    private String remark;

}
