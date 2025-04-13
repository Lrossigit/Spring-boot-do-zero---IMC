package br.gov.sp.cps;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Controller
@RequestMapping("/")
public class app {
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    @Qualifier("classificacao")
    private List<Map<String, Object>> classificacoes;

    @PostMapping("/calcular")
    @ResponseBody
    public Map<String, Object> calcularIMC(@RequestBody Map<String, Object> payload) {
        String nome = String.valueOf(payload.get("nome"));
        double peso = ((Number) payload.get("peso")).doubleValue();
        double altura = ((Number) payload.get("altura")).doubleValue();
        double imc = peso / (altura * altura);

        String classificacao = "Classificação não encontrada";
        for (Map<String, Object> faixa : classificacoes) {
            double min = (double) faixa.get("min");
            double max = (double) faixa.get("max");
            if (imc >= min && imc < max) {
                classificacao = (String) faixa.get("msg");
                break;
            }
        }

        Map<String, Object> resposta = new HashMap<>();
        String mensagem = "Olá " + nome + ", conforme seu peso (" + peso + ") e altura (" + altura + "), repassaremos o resultado de seu IMC:";
        resposta.put("mensagem", mensagem);
        resposta.put("imc", imc);
        resposta.put("classificacao", classificacao);

        return resposta;
    }

}
