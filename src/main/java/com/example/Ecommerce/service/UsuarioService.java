package com.example.Ecommerce.service;

import com.example.Ecommerce.model.Usuario;
import com.example.Ecommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UsuarioService {


    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {

        this.usuarioRepository = usuarioRepository;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setPassword( bCryptPasswordEncoder.encode(usuario.getPassword()) );
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
        newUsuario.setUsername(usuario.getUsername());
        newUsuario.setPassword(usuario.getPassword());
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

    public Usuario getUserLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return usuarioRepository.findByUsername(username);
    }
}
