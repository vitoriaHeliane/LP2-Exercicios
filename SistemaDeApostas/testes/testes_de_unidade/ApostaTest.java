package testes_de_unidade;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes.Aposta;

public class ApostaTest {
	private static final String ERRO_APOSTA = "Apostador nao pode ser vazio ou nulo";
	private static final String ERRO_PREVISAO = "Previsao nao pode ser vazia ou nula";
	private static final String ERRO_PREVISAO_INVALIDA = "Previsao invalida";
	private static final String ERRO_VALOR = "Valor nao pode ser menor ou igual a zero";
	private static final String FAIL = "Deveria ter lancado excecao";
	private Aposta aposta;
	
	@Before
	public void setUp() {
		aposta = new Aposta("Vitoria", 100, "VAI ACONTECER");
	}

	@Test
	public void testAposta() {
		Aposta a;
		try {
			a = new Aposta(null, 100, "VAI ACONTECER"); 
			fail(FAIL);
		} catch(NullPointerException e) {
			assertEquals(ERRO_APOSTA, e.getMessage());
		}
		try {
			a = new Aposta("     ", 100, "VAI ACONTECER");
			fail(FAIL);
		} catch(IllegalArgumentException e) {
			assertEquals(ERRO_APOSTA, e.getMessage());
		}
		try {
			a = new Aposta("Vitoria", 100, null); 
			fail(FAIL);
		} catch(NullPointerException e) {
			assertEquals(ERRO_PREVISAO, e.getMessage());
		}
		try {
			a = new Aposta("Vitoria", 100, ""); 
			fail(FAIL);
		} catch(IllegalArgumentException e) {
			assertEquals(ERRO_PREVISAO, e.getMessage());
		}
		try {
			a = new Aposta("Vitoria", 100, "Quero que aconteca"); 
			fail(FAIL);
		} catch(IllegalArgumentException e) {
			assertEquals(ERRO_PREVISAO_INVALIDA, e.getMessage());
		}
		try {
			a = new Aposta("Vitoria", -100, "N VAI ACONTECER"); 
		} catch(IllegalArgumentException e) {
			assertEquals(ERRO_VALOR, e.getMessage());
		}
		try {
			a = new Aposta("Vitoria", 0, "N VAI ACONTECER");
			fail(FAIL);
		} catch(IllegalArgumentException e) {
			assertEquals(ERRO_VALOR, e.getMessage());
		}
		a = new Aposta("Vitoria", 100, "VAI ACONTECER");
		assertEquals("Vitoria", a.getNome());
		assertEquals(100, a.getValor(), 0.000001);
		assertEquals("VAI ACONTECER", a.getPrevisao());
	}

	@Test
	public void testToString() {
		assertEquals("Vitoria - R$1.0 - VAI ACONTECER", aposta.toString());
	}

	@Test
	public void testEquals() {
		assertEquals(false, aposta.equals(new Aposta("Vitoria", 100, "VAI ACONTECER")));
		assertEquals(false, aposta.equals(null));
		assertEquals(false, aposta.equals("Vitoria"));
	}

}
