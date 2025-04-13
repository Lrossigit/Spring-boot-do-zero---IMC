package br.gov.sp.cps;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Configs {

    @Bean(name = "classificacao")
    public List<Map<String, Object>> classificacoesIMC() {
        return List.of(
                Map.of("min", 0.0, "max", 18.5, "msg", "Você está abaixo do peso, procure acompanhamento"),
                Map.of("min", 18.5, "max", 25.0, "msg", "Você está com o peso normal, parabéns!"),
                Map.of("min", 25.0, "max", 30.0, "msg", "Você está em Sobrepeso, cuide-se"),
                Map.of("min", 30.0, "max", 35.0, "msg", "Você está em Obesidade grau I, procure um médico"),
                Map.of("min", 35.0, "max", 40.0, "msg", "Você está em Obesidade grau II, cuide-se urgentemente"),
                Map.of("min", 40.0, "max", Double.MAX_VALUE, "msg", "Você está em Obesidade grau III, busque ajuda imediatamente")
        );
    }
}
