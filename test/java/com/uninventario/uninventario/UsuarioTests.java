package com.uninventario.uninventario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.uninventario.uninventario.domain.database.Database;
import com.uninventario.uninventario.domain.usuario.Usuario;
import com.uninventario.uninventario.domain.usuario.UsuarioRepository;

public class UsuarioTests {
    	
    @Test
	void adicionarUsuarioTest() {
        Database.resetarUsuario();
        UsuarioRepository usuarioRepository = new UsuarioRepository();

		Usuario usuario = new Usuario(1, "Daniel Silva Pereira", "Tecnico de informática", 12567);
		Usuario usuario2 = new Usuario(2, "Catarina Romano", "Supervisora de informática", 65738);
        usuarioRepository.adicionarUsuario(usuario);
        usuarioRepository.adicionarUsuario(usuario2);

		Usuario[] resultado = Database.buscarTodosUsuarios();
		assertEquals(2, resultado.length);
	}

    @Test
	void editarUsuario() {
		Database.resetarUsuario();
		UsuarioRepository usuarioRepository = new UsuarioRepository();

		// Inserção
		Usuario usuario = new Usuario(1, "Daniel Silva Pereira", "Tecnico de informática", 12567);
		usuarioRepository.adicionarUsuario(usuario);

        // Editar
		usuario.setCargo("Tecnico de informática Senior");
		usuarioRepository.editarUsuario(usuario);

		assertEquals("Tecnico de informática Senior", Database.buscarUmUsuario(1).getCargo());
	}

	@Test
	void excluirUsuario() {
		Database.resetarUsuario();
		UsuarioRepository usuarioRepository = new UsuarioRepository();

		// Inserção
		Usuario usuario = new Usuario(1, "Daniel Silva Pereira", "Tecnico de informática", 12567);
		usuarioRepository.adicionarUsuario(usuario);

		// Editar 
		usuarioRepository.deletarUsuario(1);

		assertEquals(0, Database.buscarTodosUsuarios().length);
	}

	@Test
	void buscarUmUsuarioExistente() {
		Database.resetarUsuario();
		UsuarioRepository usuarioRepository = new UsuarioRepository();

		// Inserção 
		Usuario usuario = new Usuario(1, "Daniel Silva Pereira", "Tecnico de informática", 12567);
		usuarioRepository.adicionarUsuario(usuario);

		// Buscar
		Usuario resultadoBusca = usuarioRepository.buscarUmUsuario(usuario.getId());

		assertNotNull(resultadoBusca);
	}

	@Test
	void buscarUmUsuarioInexistente() {
		Database.resetarUsuario();
		UsuarioRepository usuarioRepository = new UsuarioRepository();

		// Buscar
		Usuario resultadoBusca = usuarioRepository.buscarUmUsuario(1);

		assertNull(resultadoBusca);
	}
}
