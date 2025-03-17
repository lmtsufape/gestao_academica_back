package br.edu.ufape.sguAuthService.servicos.tasks;


import br.edu.ufape.sguAuthService.fachada.Fachada;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
@AllArgsConstructor
public class CleanupUnverifiedUsers {
    private static final Logger log = LoggerFactory.getLogger(CleanupUnverifiedUsers.class);
    private final Fachada fachada;

    @Scheduled(fixedRate = 3600000) // Executa a cada 1 hora (em milissegundos)
    public void cleanupUnverifiedUsers() {
        log.info("Iniciando limpeza de usuários não verificados...");
        try {
            fachada.removerUsuariosNaoVerificados(24); // Remove após 24 horas
            log.info("Limpeza de usuários não verificados concluída.");
        } catch (Exception e) {
            log.error("Erro durante a limpeza de usuários não verificados: {}", e.getMessage(), e);
        }
    }


}
