package br.edu.ufape.sguAuthService.comunicacao.controllers;

import br.edu.ufape.sguAuthService.models.Perfil;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import br.edu.ufape.sguAuthService.dados.PerfilRepository;

@RestController
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilRepository perfilRepository;

    public PerfilController(PerfilRepository perfilRepository) {
        this.perfilRepository = perfilRepository;
    }

    // Endpoint para atualizar a foto de perfil
    @PostMapping("/atualizarFoto")
    public String atualizarFoto(@RequestParam Long id, @RequestParam("foto") MultipartFile foto) {
        try {
            // Recupera o perfil pelo ID
            Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> new Exception("Perfil não encontrado"));
            
            // Converte a imagem em binário e armazena
            perfil.setFotoPerfil(foto.getBytes());
            perfilRepository.save(perfil);  // Salva a foto de perfil no banco
            
            return "Foto de perfil atualizada com sucesso!";
        } catch (IOException e) {
            return "Erro ao processar a imagem: " + e.getMessage();
        } catch (Exception e) {
            return "Erro ao atualizar a foto de perfil: " + e.getMessage();
        }
    }

    // Endpoint para visualizar a foto de perfil
    @GetMapping("/foto/{id}")
    public byte[] obterFoto(@PathVariable Long id) throws Exception {
        Perfil perfil = perfilRepository.findById(id)
            .orElseThrow(() -> new Exception("Perfil não encontrado"));
        
        // Retorna a imagem armazenada como array de bytes
        return perfil.getFotoPerfil();
    }
}
