package com.uninventario.uninventario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.uninventario.uninventario.domain.database.Database;
import com.uninventario.uninventario.domain.maquina.Maquina;
import com.uninventario.uninventario.domain.maquina.MaquinaRepository;

public class MaquinaTests {
    @Test
	void adicionarMaquinaTest() {
        Database.resetarMaquina();
        MaquinaRepository maquinaRepository = new MaquinaRepository();
        
		Maquina maquina = new Maquina(1, "Máquina 1", "Dell Latitude E6540", "Intel Core i5-5200U", "8 GB", "500 GB", "Laboratório 20");
		maquinaRepository.adicionarMaquina(maquina);

		Maquina[] resultado = Database.buscarTodasMaquinas();
		assertEquals(1, resultado.length);
	}

    @Test
	void editarMaquina() {
		Database.resetarMaquina();
		MaquinaRepository maquinaRepository = new MaquinaRepository();

		// Inserção
		Maquina maquina = new Maquina(1, "Máquina 1", "Dell Latitude E6540", "Intel Core i5-5200U", "8 GB", "500 GB", "Laboratório 20");
		maquinaRepository.adicionarMaquina(maquina);

        // Editar
		maquina.setMemoriaRam("16 GB");
		maquinaRepository.editarMaquina(maquina);

		assertEquals("16 GB", Database.buscarUmaMaquina(1).getMemoriaRam());
	}

	@Test
	void excluirMaquina() {
		Database.resetarMaquina();
		MaquinaRepository maquinaRepository = new MaquinaRepository();

		// Inserção
		Maquina maquina = new Maquina(1, "Máquina 1", "Dell Latitude E6540", "Intel Core i5-5200U", "8 GB", "500 GB", "Laboratório 20");
		maquinaRepository.adicionarMaquina(maquina);

		// Editar 
		maquinaRepository.deletarMaquina(1);

		assertEquals(0, Database.buscarTodasMaquinas().length);
	}

	@Test
	void buscarUmMaquinaExistente() {
		Database.resetarMaquina();
		MaquinaRepository maquinaRepository = new MaquinaRepository();

		// Inserção 
		Maquina maquina = new Maquina(1, "Máquina 1", "Dell Latitude E6540", "Intel Core i5-5200U", "8 GB", "500 GB", "Laboratório 20");
		maquinaRepository.adicionarMaquina(maquina);

		// Buscar
		Maquina resultadoBusca = maquinaRepository.buscarUmaMaquina(maquina.getId());

		assertNotNull(resultadoBusca);
	}

	@Test
	void buscarUmMaquinaInexistente() {
		Database.resetarMaquina();
		MaquinaRepository maquinaRepository = new MaquinaRepository();

		// Buscar
		Maquina resultadoBusca = maquinaRepository.buscarUmaMaquina(1);

		assertNull(resultadoBusca);
	}
}
