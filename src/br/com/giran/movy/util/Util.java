package br.com.giran.movy.util;

import java.io.File;
import java.util.Calendar;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;

public class Util {

	private static final String[] ARQUIVOS_PERMITIDOS = { ".jpg", ".jpeg", ".gif", ".bmp", ".png" };
	private static final Calendar CALENDAR = Calendar.getInstance();

	public static int getAnoAtual() {
		return CALENDAR.get(Calendar.YEAR);
	}

	public static boolean isExtensaoValida(String extensao) {
		int tam = extensao.length();

		if (tam == 4 || (tam == 5 && extensao.equalsIgnoreCase(".jpeg"))) {
			extensao = extensao.toLowerCase();

			for (int i = 0; i < ARQUIVOS_PERMITIDOS.length; i++) {
				if (extensao.endsWith(ARQUIVOS_PERMITIDOS[i])) {
					return true;
				}
			}
		}

		return false;
	}

	public static String recuperarExtensao(String fileName) {
		String extensao = (fileName.lastIndexOf(".") > 0) ? fileName.substring(fileName.lastIndexOf(".")) : "";
		return (extensao.equalsIgnoreCase(".jpeg") ? ".jpg" : extensao).toLowerCase();
	}
	
	public static void removerFotoDuplicada(UploadedFile imagem, String path, String fotoAntiga, String fotoNova) {
		if (imagem != null && !fotoAntiga.equalsIgnoreCase("default.jpg")) {
			
			String extensaoAntiga = recuperarExtensao(fotoNova);
			String extensaoNova = recuperarExtensao(fotoAntiga);

			if (!extensaoNova.equalsIgnoreCase(extensaoAntiga)) {
				File foto = new File(path, fotoAntiga);

				if (foto.exists()) {
					foto.delete();
				}
			}
		}
	}

}