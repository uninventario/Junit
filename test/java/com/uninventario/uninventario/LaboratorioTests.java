package com.uninventario.uninventario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.uninventario.uninventario.domain.database.Database;
import com.uninventario.uninventario.domain.laboratorio.*;

@SpringBootTest
class LaboratorioTests {

	@Test
	 void adicionarLaboratorioTest() {
		Database.resetarLaboratorio();
		LaboratorioRepository laboratorioRepository = new LaboratorioRepository();
	
		Laboratorio laboratorio = new Laboratorio(1, "Laboratório de informática 1", "Predio Central", 2);
		laboratorioRepository.adicionarLaboratorio(laboratorio);

		Laboratorio[] resultado = Database.buscarTodosLaboratorios();
		assertEquals(1, resultado.length);
	}
	
	@Test
	void editarLaboratorio() {
		Database.resetarLaboratorio();
		LaboratorioRepository laboratorioRepository = new LaboratorioRepository();

		// Inserção
		Laboratorio laboratorio = new Laboratorio(1, "Laboratório de informática 1", "Predio Central", 2);
		laboratorioRepository.adicionarLaboratorio(laboratorio);

        // Editar
		laboratorio.setNome("Laboratório de informática 2");
		laboratorioRepository.editarLaboratorio(laboratorio);

		assertEquals("Laboratório de informática 2", Database.buscarUmLaboratorio(1).getNome());
	}

	@Test
	void excluirLaboratorio() {
		Database.resetarLaboratorio();
		LaboratorioRepository laboratorioRepository = new LaboratorioRepository();

		// Inserção
		Laboratorio laboratorio = new Laboratorio(1, "Laboratório de informática 1", "Predio Central", 2);
		laboratorioRepository.adicionarLaboratorio(laboratorio);

		// Editar 
		laboratorioRepository.deletarLaboratorio(1);

		assertEquals(0, Database.buscarTodosLaboratorios().length);
	}

	@Test
	void buscarUmLaboratorioExistente() {
		Database.resetarLaboratorio();
		LaboratorioRepository laboratorioRepository = new LaboratorioRepository();

		// Inserção 
		Laboratorio laboratorio = new Laboratorio(1, "Laboratório de informática 1", "Predio Central", 2);
		laboratorioRepository.adicionarLaboratorio(laboratorio);

		// Buscar
		Laboratorio resultadoBusca = laboratorioRepository.buscarUmLaboratorio(laboratorio.getId());

		assertNotNull(resultadoBusca);
	}

	@Test
	void buscarUmLaboratorioInexistente() {
		Database.resetarLaboratorio();
		LaboratorioRepository laboratorioRepository = new LaboratorioRepository();

		// Buscar
		Laboratorio resultadoBusca = laboratorioRepository.buscarUmLaboratorio(1);

		assertNull(resultadoBusca);
	}
}
