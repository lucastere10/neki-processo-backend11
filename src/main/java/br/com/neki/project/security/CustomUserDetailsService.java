package br.com.neki.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.neki.project.repository.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Metodo que vai obter dinamicamente o usuario no banco pelo username, que np
        // nosso caso é o e-mail.
        return usuarioRepository.findByEmail(username).get();
    }

    public UserDetails obterUsuarioPeloId(Long id) {
        return usuarioRepository.findById(id).get();
    }

}
