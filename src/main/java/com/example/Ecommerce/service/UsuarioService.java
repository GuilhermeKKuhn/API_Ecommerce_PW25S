package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.RuntimeErrorException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario save(Usuario usuario) {
        usuario = usuarioRepository.save(usuario);
        return usuario;
    }

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException(
                "Usuario não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName()
        ));
    }

    @Transactional
    public Usuario update(Usuario usuario) {
        Usuario newUsuario = findById(usuario.getId());
        newUsuario.setNome(usuario.getNome());
        newUsuario.setSenha(usuario.getSenha());
        return usuarioRepository.save(newUsuario);
    }

    public void delete(Long id) {
        findById(id);
        try {
            this.usuarioRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Não é possivel excluir pois está relacionado com outra entidade");
        }
    }
}
