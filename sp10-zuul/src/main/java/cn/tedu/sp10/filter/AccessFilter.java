package cn.tedu.sp10.filter;

import cn.tedu.web.util.JsonResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

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
        /**
         * 前置过滤器中已经存在5个默认的过滤器，
         * 在第5个过滤器中，向上下午文对象添加了"service-id"属性
         * */
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
        //http://localhost:3001/item-service/y4y433  没有登陆不允许访问
        //http://localhost:3001/item-service/y4y433?token=78oi6i544 有token认为已登陆，可以访问

        //获取request
        RequestContext ctx=RequestContext.getCurrentContext();
        HttpServletRequest request=ctx.getRequest();

        //用request接收token参数
        String token = request.getParameter("token");

        //如果token参数为空，阻止继续访问,返回登陆提示
        if(StringUtils.isBlank(token)){
            //阻止继续访问
            ctx.setSendZuulResponse(false);
            //JsonResult-->"{code:400,msg:not log in,data:null}"
            String json = JsonResult.err()
                    .code(JsonResult.NOT_LOGIN)
                    .msg("Not login")
                    .toString();
            ctx.setResponseStatusCode(JsonResult.NOT_LOGIN);
            //加上后返回信息可以有中文
            //ctx.addZuulResponseHeader("Content-Type", "application/json;charset=utf-8");
            ctx.setResponseBody(json);
        }
        return null;//当前zuul版本中,无任何作用
    }
}
