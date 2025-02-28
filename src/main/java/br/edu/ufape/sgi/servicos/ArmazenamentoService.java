package br.edu.ufape.sgi.servicos;

import br.edu.ufape.sgi.models.Documento;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ArmazenamentoService implements br.edu.ufape.sgi.servicos.interfaces.ArmazenamentoService {
    private final List<String> tiposPermitidos = List.of("application/pdf", "image/jpeg", "image/png", "image/jpg");
    @Value("${arquivo.diretorio-upload}")
    private String uploadDir;

    @Transactional
    @Override
    public List<Documento> salvarArquivo(MultipartFile[] arquivos) {
        List<Documento> documentosSalvos = new ArrayList<>();
        for (MultipartFile arquivo : arquivos) {
            if (!tiposPermitidos.contains(arquivo.getContentType())) {
                throw new IllegalArgumentException("Tipo de arquivo não permitido!");
            }
            String uuid = UUID.randomUUID().toString();
            String extensao = FilenameUtils.getExtension(arquivo.getOriginalFilename());
            String nomeArquivoComUUID = uuid + "." + extensao;

            try {
                // Salva o arquivo no diretório desejado

                Path caminho = Paths.get(uploadDir, nomeArquivoComUUID);
                Files.copy(arquivo.getInputStream(), caminho);

                // Cria uma nova instância de Documento
                Documento documento = new Documento();
                documento.setNome(nomeArquivoComUUID); // Armazena o nome com UUID
                documento.setPath(caminho.toString());
                documentosSalvos.add(documento);
            } catch (IOException e) {
                throw new RuntimeException("Falha ao salvar arquivo!");

            }
        }
        return documentosSalvos;
    }

    @Override
    public Resource carregarArquivoZip(List<Documento> documentos) throws IOException {
        Path zipPath = Files.createTempFile("documentos", ".zip");
        try (ZipOutputStream zipOut = new ZipOutputStream(Files.newOutputStream(zipPath))) {
            for (Documento documento : documentos) {
                Path filePath = Paths.get(uploadDir).resolve(documento.getPath()).normalize();
                if (Files.exists(filePath)) {
                    ZipEntry zipEntry = new ZipEntry(documento.getNome());
                    zipOut.putNextEntry(zipEntry);
                    Files.copy(filePath, zipOut);
                    zipOut.closeEntry();
                }
            }
        }
        return new UrlResource(zipPath.toUri());
    }
}
