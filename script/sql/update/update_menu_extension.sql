-- =============================================
-- 菜单表扩展字段 SQL
-- 适用于：Vben Admin 前端框架
-- 数据库：MySQL
-- =============================================

-- 路由参数（JSON格式，如：{"id": 1}）
ALTER TABLE sys_menu ADD COLUMN query_param varchar(500) DEFAULT NULL COMMENT '路由参数';

-- 菜单标题（多语言key或直接文本，与menuName区分：menuName用于路由name，title用于显示）
ALTER TABLE sys_menu ADD COLUMN title varchar(100) DEFAULT NULL COMMENT '菜单标题';

-- 激活图标（菜单激活时显示的图标）
ALTER TABLE sys_menu ADD COLUMN active_icon varchar(200) DEFAULT NULL COMMENT '激活图标';

-- 激活路径（作为路由时需要激活的菜单路径）
ALTER TABLE sys_menu ADD COLUMN active_path varchar(200) DEFAULT NULL COMMENT '激活路径';

-- 是否固定标签页（0否 1是）
ALTER TABLE sys_menu ADD COLUMN affix_tab tinyint(1) DEFAULT 0 COMMENT '是否固定标签页';

-- 固定标签页顺序
ALTER TABLE sys_menu ADD COLUMN affix_tab_order int DEFAULT 0 COMMENT '固定标签页顺序';

-- 徽标内容
ALTER TABLE sys_menu ADD COLUMN badge varchar(50) DEFAULT NULL COMMENT '徽标内容';

-- 徽标类型（dot点 normal普通）
ALTER TABLE sys_menu ADD COLUMN badge_type varchar(20) DEFAULT NULL COMMENT '徽标类型';

-- 徽标颜色（default/primary/success/warning/destructive）
ALTER TABLE sys_menu ADD COLUMN badge_variants varchar(50) DEFAULT NULL COMMENT '徽标颜色';

-- 是否隐藏子菜单（0否 1是）
ALTER TABLE sys_menu ADD COLUMN hide_children tinyint(1) DEFAULT 0 COMMENT '是否隐藏子菜单';

-- 是否在面包屑中隐藏（0否 1是）
ALTER TABLE sys_menu ADD COLUMN hide_breadcrumb tinyint(1) DEFAULT 0 COMMENT '是否在面包屑中隐藏';

-- 是否在标签栏中隐藏（0否 1是，与visible区分：visible控制菜单显隐，hideTab控制标签栏显隐）
ALTER TABLE sys_menu ADD COLUMN hide_tab tinyint(1) DEFAULT 0 COMMENT '是否在标签栏中隐藏';

-- 外链/内嵌地址（用于link和embedded类型）
ALTER TABLE sys_menu ADD COLUMN link_src varchar(500) DEFAULT NULL COMMENT '外链/内嵌地址';

-- 最大打开标签数
ALTER TABLE sys_menu ADD COLUMN max_open_tab int DEFAULT 0 COMMENT '最大打开标签数';

-- 重定向地址
ALTER TABLE sys_menu ADD COLUMN redirect varchar(200) DEFAULT NULL COMMENT '重定向地址';

-- 是否在菜单中隐藏（0 否 1 是，与 visible 区分：visible 控制菜单显隐，hideInMenu 控制是否在菜单列表中显示）
ALTER TABLE sys_menu ADD COLUMN hide_in_menu tinyint(1) DEFAULT 0 COMMENT '是否在菜单中隐藏';


-- =============================================
-- 字段说明文档
-- =============================================
--
-- 【基础字段与扩展字段的关系】
--
-- 1. menuName vs title
--    - menuName: 路由名称（name属性），用于路由跳转和权限匹配
--    - title: 显示标题，支持多语言key或直接文本
--
-- 2. visible vs hideTab vs hideBreadcrumb
--    - visible: 控制菜单在侧边栏的显隐（0显示 1隐藏）
--    - hideTab: 控制页面在标签栏的显隐（0显示 1隐藏）
--    - hideBreadcrumb: 控制在面包屑中的显隐（0显示 1隐藏）
--
-- 3. isCache
--    - 0: 缓存页面（keepAlive=true）
--    - 1: 不缓存页面（keepAlive=false）
--
-- 4. isFrame 与 linkSrc
--    - isFrame='0': 外链，path存储外链地址
--    - linkSrc: 内嵌页面(iframe)地址
--
-- 5. menuType 映射
--    - M: 目录（catalog）
--    - C: 菜单（menu）或内嵌页面（embedded）
--    - F: 按钮（button）
--    - 外链类型：isFrame='0' 且 path以http开头时为link类型
--
-- 6. badge 徽标系统
--    - badge: 徽标内容文本
--    - badgeType: dot(红点) 或 normal(文本)
--    - badgeVariants: 颜色主题
--
-- =============================================
