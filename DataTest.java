package exercicio5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

class DataTest {

	@Test
	void testDataSistema() {
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int sysDia = calendar.get(Calendar.DATE);
		int sysMes = calendar.get(Calendar.MONTH) + 1;
		int sysAno = calendar.get(Calendar.YEAR);
		Data dataSistema = new Data();
		String strDataSistema = dataSistema.toString();
		String sysZeroDia = "";
		String sysZeroMes = "";
		if (sysDia < 10)
			sysZeroDia = "0";
		if (sysMes < 10)
			sysZeroMes = "0";
		String testeDataSistema = sysZeroDia + sysDia + "/" + sysZeroMes + sysMes + "/" + sysAno;
		assertEquals(testeDataSistema, strDataSistema);
	}

	@Test
	void test() {
		Data dataNormal = new Data(21, 8, 2019);
		Data dataErrada = new Data(5, 7, 0);
		Data dataComAnoBissexto = new Data(29, 2, 1964);
		Data dataFimDoAno = new Data(31, 12, 1999);

		String strDataNormal = dataNormal.toString();
		String strDataErrada = dataErrada.toString();
		String strDataComAnoBissexto = dataComAnoBissexto.toString();
		String strDataFimDoAno = dataFimDoAno.toString();

		assertEquals("21/08/2019", strDataNormal);
		assertEquals("01/01/2000", strDataErrada);
		assertEquals("29/02/1964", strDataComAnoBissexto);
		assertEquals("31/12/1999", strDataFimDoAno);

		dataNormal.avancaData();
		dataErrada.avancaData();
		dataComAnoBissexto.avancaData();
		dataFimDoAno.avancaData();

		strDataNormal = dataNormal.toString();
		strDataErrada = dataErrada.toString();
		strDataComAnoBissexto = dataComAnoBissexto.toString();
		strDataFimDoAno = dataFimDoAno.toString();

		assertEquals("22/08/2019", strDataNormal);
		assertEquals("02/01/2000", strDataErrada);
		assertEquals("01/03/1964", strDataComAnoBissexto);
		assertEquals("01/01/2000", strDataFimDoAno);

		dataErrada.setAno(-5);
		dataErrada.setAno(1947);
		strDataErrada = dataErrada.toString();
		assertEquals("02/01/1947", strDataErrada);
	}

}
