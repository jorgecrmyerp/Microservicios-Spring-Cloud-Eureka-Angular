package com.jgr.micro.app.micro.zuul.spring.gateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

// TODO: Auto-generated Javadoc
/**
 * The Class SpringGatewayFilters.
 * Filtros
 */
@Component
public class SpringGatewayFilters implements GlobalFilter,Ordered{
	
	private final Logger logger = LoggerFactory.getLogger(SpringGatewayFilters.class);

	/**
	 * Filter.
	 * creamos objeto reactivo Mono ,crea una cookie que se llama color y desde aqui puedo 
	 * cambiar que la respuesta sea un texto plano en vez de un json
	 * @param exchange the exchange
	 * @param chain the chain
	 * @return the mono
	 */
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		// TODO Auto-generated method stub
		logger.info("Spring-Gateway->SpringGatewayFilters");
		//
		return chain.filter(exchange).then(Mono.fromRunnable(()->{
			exchange.getResponse().getCookies().add("color", ResponseCookie.from("color", "rojo").build());
			//exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
			
			//si dejamos esto,solo saca json,pero por ejemplo no sacaria fotos
			//exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
			
			
		}));
	}
	
	@Override
	public int getOrder() {
		return 1;
	}
	
	

}
