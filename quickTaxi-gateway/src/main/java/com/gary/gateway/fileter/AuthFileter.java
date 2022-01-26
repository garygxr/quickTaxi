package com.gary.gateway.fileter;


import com.gary.common.core.constant.HttpStatus;
import com.gary.common.core.util.ConvertUtil;
import com.gary.common.core.util.IpUtil;
import com.gary.common.core.util.ServletUtil;
import com.gary.common.core.util.TaxiStringUtil;
import com.gary.common.core.constant.SecurityConstants;
import com.gary.common.core.constant.TokenConstants;
import com.gary.common.core.util.JwtUtil;
import com.gary.gateway.config.IgnoreWhiteProperties;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Classname AuthFileter
 * @Description 认证拦截
 * @Date 2022/1/25 11:40
 * @Auth gary
 */
@Component
public class AuthFileter implements GlobalFilter, Ordered {
    @Autowired
    IgnoreWhiteProperties ignoreWhiteProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        // 如果是请求白名单url 放行
        if (TaxiStringUtil.matches(url, ignoreWhiteProperties.getWhites())) {
            return chain.filter(exchange);
        }

        String token = getToken(request);
        // 如果令牌为空
        if (StringUtils.isBlank(token)){
            return ServletUtil.webFluxResponseWriter(response,"令牌未空,未登陆", HttpStatus.UNAUTHORIZED);
        }

        Claims claims = JwtUtil.parseToken(token);
        if (claims==null){
            return ServletUtil.webFluxResponseWriter(response,"令牌校验失败",HttpStatus.UNAUTHORIZED);
        }

        String orignIpaddr = JwtUtil.getValue(claims, SecurityConstants.IP_ADDR);
        String currentIpAddr = IpUtil.getIpAddr(request);
        if (!StringUtils.equals(orignIpaddr,currentIpAddr)){
            return ServletUtil.webFluxResponseWriter(response,"ip地址变更,重新登陆",HttpStatus.UNAUTHORIZED);
        }

        Long expiretime = ConvertUtil.toLong(JwtUtil.getValue(claims, SecurityConstants.EXPIRE_TIME), 0l);
        if (expiretime<System.currentTimeMillis()){
            return ServletUtil.webFluxResponseWriter(response,"登陆过期, 重新登陆",HttpStatus.UNAUTHORIZED);
        }


        // 设置用户信息到请求头
        String userId = JwtUtil.getValue(claims, SecurityConstants.DETAILS_USER_ID);
        String username = JwtUtil.getValue(claims, SecurityConstants.DETAILS_USERNAME);

        addHeader(mutate,SecurityConstants.DETAILS_USER_ID,userId);
        addHeader(mutate,SecurityConstants.DETAILS_USERNAME,username);

        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, "");
            token = StringUtils.trim(token);
        }
        return token;
    }

    /**
     * 添加请求头
     * @return
     * @param mutate
     * @param key
     * @param value
     */
    private void addHeader(ServerHttpRequest.Builder mutate, String key, String value){
        mutate.header(key,value);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
