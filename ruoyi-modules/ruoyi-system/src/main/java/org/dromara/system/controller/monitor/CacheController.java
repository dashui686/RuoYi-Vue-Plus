package org.dromara.system.controller.monitor;

import cn.dev33.satoken.annotation.SaCheckPermission;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.constant.CacheConstants;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.system.domain.SysCache;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 缓存监控
 *
 * @author Lion Li
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/monitor/cache")
public class CacheController {

    private final RedissonConnectionFactory connectionFactory;

    private static final List<SysCache> caches = new ArrayList<>();

    static {
        caches.add(new SysCache(CacheConstants.ONLINE_TOKEN_KEY, "用户信息"));
        caches.add(new SysCache(CacheConstants.SYS_CONFIG_KEY, "配置信息"));
        caches.add(new SysCache(CacheConstants.SYS_DICT_KEY, "数据字典"));
        caches.add(new SysCache(CacheConstants.PWD_ERR_CNT_KEY, "密码错误次数"));
    }

    /**
     * 获取缓存名称列表
     */
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/getNames")
    public R<List<SysCache>> cache() {
        return R.ok(caches);
    }

    /**
     * 获取缓存键名列表
     */
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/getKeys/{cacheName}")
    public R<Collection<String>> getCacheKeys(@PathVariable String cacheName) {
        Collection<String> keys = RedisUtils.keys(cacheName + "*");
        return R.ok(keys);
    }

    /**
     * 获取缓存内容
     */
    @SaCheckPermission("monitor:cache:list")
    @GetMapping("/getValue/{cacheName}/{cacheKey}")
    public R<SysCache> getCacheValue(@PathVariable String cacheName,
                                     @PathVariable String cacheKey) {
        Object cacheValue = RedisUtils.getCacheObject(cacheKey);
        SysCache sysCache = new SysCache(cacheName, cacheKey, String.valueOf(cacheValue));
        return R.ok(sysCache);
    }

    /**
     * 清理指定名称缓存
     */
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheName/{cacheName}")
    public R<Void> clearCacheName(@PathVariable String cacheName) {
        RedisUtils.deleteKeys(cacheName + "*");
        return R.ok();
    }

    /**
     * 清理指定键名缓存
     */
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheKey/{cacheKey}")
    public R<Void> clearCacheKey(@PathVariable String cacheKey) {
        RedisUtils.deleteObject(cacheKey);
        return R.ok();
    }

    /**
     * 清理全部缓存
     */
    @SaCheckPermission("monitor:cache:list")
    @DeleteMapping("/clearCacheAll")
    public R<Void> clearCacheAll() {
        RedisUtils.deleteKeys("*");
        return R.ok();
    }

    /**
     * 获取缓存监控列表
     */
    @SaCheckPermission("monitor:cache:list")
    @GetMapping()
    public R<CacheListInfoVo> getInfo() throws Exception {
        RedisConnection connection = connectionFactory.getConnection();
        try {
            Properties commandStats = connection.commands().info("commandstats");
            List<Map<String, String>> pieList = new ArrayList<>();
        if (commandStats != null) {
            commandStats.stringPropertyNames().forEach(key -> {
                Map<String, String> data = new HashMap<>(2);
                String property = commandStats.getProperty(key);
                data.put("name", StringUtils.removeStart(key, "cmdstat_"));
                data.put("value", StringUtils.substringBetween(property, "calls=", ",usec"));
                pieList.add(data);
            });
        }
        return R.ok(new CacheListInfoVo(
            connection.commands().info(),
            connection.commands().dbSize(), pieList));
        } finally {
            // 归还连接给连接池
            RedisConnectionUtils.releaseConnection(connection, connectionFactory);
        }
    }

    /**
     * 缓存监控列表信息
     *
     * @param info         信息
     * @param dbSize       数据库
     * @param commandStats 命令统计
     */
    public record CacheListInfoVo(Properties info, Long dbSize, List<Map<String, String>> commandStats) {}

}
