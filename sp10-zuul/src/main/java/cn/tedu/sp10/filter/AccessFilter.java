package cn.tedu.sp10.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

/**
 * @author wxh
 * @date 2020/10/30 11:55
 * @description
 */
@Component
public class AccessFilter extends ZuulFilter {
    //配置过滤器类型：pre,routes,post,error
    @Override
    public String filterType() {
        //return "pre"
        return FilterConstants.PRE_TYPE;//配置前置过滤器
    }

    //当前过滤器添加到哪个位置，返回一个顺序号
    @Override
    public int filterOrder() {
        //默认存在5个过滤器
        return 6;
    }

    //针对当前请求进行判断，是否执行过滤（run方法）
    @Override
    public boolean shouldFilter() {
        //判断当前请求调用的是否是item-service
        //如果请求item，执行过滤代码，否则跳过
        RequestContext ctx=RequestContext.getCurrentContext();//zuul请求上下文对象
        //从上下文对象获取"服务id"属性的值
        String serviceId =(String) ctx.get(FilterConstants.SERVICE_ID_KEY);
        return "item-service".equalsIgnoreCase(serviceId);//忽略大小写
    }

    //过滤方法，权限判断写在这里
    @Override
    public Object run() throws ZuulException {
        return null;
    }
}
