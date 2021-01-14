package io.github.marconefreitas.agendaapi.model.api.rest;

import io.github.marconefreitas.agendaapi.model.entity.Contato;
import io.github.marconefreitas.agendaapi.model.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contatos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContatoController {

    private final ContatoRepository contatoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Contato save(@RequestBody Contato con){
        return contatoRepository.save(con);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id){
        contatoRepository.deleteById(id);
    }

    @GetMapping
    public Page<Contato> lista(
            @RequestParam(value = "page", defaultValue = "0") Integer pagina,
            @RequestParam(value = "size", defaultValue = "2") Integer tamanho){
        PageRequest pg = PageRequest.of(pagina, tamanho);
        return contatoRepository.findAll(pg);
    }

    @PatchMapping("{id}/favorito")
    public void favorite(@PathVariable Integer id){
        Optional<Contato> contato = contatoRepository.findById(id);
        contato.ifPresent(c -> {
            boolean fav = c.getFavorito() == Boolean.TRUE;
            c.setFavorito(!fav);
            contatoRepository.save(c);
        });
    }


    @PutMapping("{id}/foto")
    public byte[] addFoto(@PathVariable Integer id,
                          @RequestParam("foto") Part arquivo){
        Optional<Contato> con = contatoRepository.findById(id);

        return con.map(c -> {
            try {
                InputStream in = arquivo.getInputStream();
                byte[] arr = new byte[(int)arquivo.getSize()];
                IOUtils.readFully(in, arr);
                c.setFoto(arr);
                contatoRepository.save(c);
                in.close();
                return arr;
            }catch (IOException e){
                return null;
            }
        }).orElse(null);

    }

}
