package br.com.issei.util.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import br.com.issei.util.infra.Exception;

/**
 * Classe utilitaria para gerao de arquivos compactados.
 * 
 * @author Valery Giscard
 * 
 */
public class ZipUtil {

	private ZipUtil() {

	}

	/**
	 * Create Target Compressed File.
	 * 
	 * @param file
	 * @return File
	 */
	public static File createTargetCompressedFile(final File file) {
		return new File(file.getAbsolutePath()
				.substring(0, file.getAbsolutePath().lastIndexOf('.'))
				.concat(".zip"));
	}

	/**
	 * Mtodo para compactar.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static long compactar(final File target, final File... files)
			throws Exception {
		return compactar(target, 6, files);
	}

	/**
	 * Mtodo para compactar. Level 0-9.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static long compactar(final File target, final int level,
			final File... files) {
		byte[] buffer = new byte[1024];
		ZipOutputStream zos = null;

		try {
			FileUtils.touch(target);

			CheckedOutputStream checksum = new CheckedOutputStream(
					new FileOutputStream(target), new CRC32());
			zos = new ZipOutputStream(checksum);
			zos.setLevel(level);

			for (File file : files) {
				if (file != null && file.exists()) {
					FileInputStream fis = new FileInputStream(file);

					zos.putNextEntry(new ZipEntry(StringUtils
							.removeAccents(file.getName())));

					int len;
					while ((len = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
					zos.closeEntry();

					fis.close();

				} else {
					System.out.println("File Vazio " + file);
				}

			}

			return checksum.getChecksum().getValue();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(zos);
		}
	}

	/**
	 * Mtodo para compactar.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static long compactarSeArquivosExistirem(final File target,
			final File... files) throws Exception {
		return compactarSeArquivosExistirem(target, 6, files);
	}

	/**
	 * Mtodo para compactar. Level 0-9.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public static long compactarSeArquivosExistirem(final File target,
			final int level, final File... files) {
		byte[] buffer = new byte[1024];
		ZipOutputStream zos = null;

		try {
			FileUtils.touch(target);

			CheckedOutputStream checksum = new CheckedOutputStream(
					new FileOutputStream(target), new CRC32());
			zos = new ZipOutputStream(checksum);
			zos.setLevel(level);

			for (File file : files) {
				if (file.exists()) {
					FileInputStream fis = new FileInputStream(file);

					zos.putNextEntry(new ZipEntry(StringUtils
							.removeAccents(file.getName())));

					int len;
					while ((len = fis.read(buffer)) > 0) {
						zos.write(buffer, 0, len);
					}
					zos.closeEntry();

					fis.close();
				}
			}

			return checksum.getChecksum().getValue();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		} finally {
			IOUtils.closeQuietly(zos);
		}
	}

	/**
	 * Extrai o unico arquivo zipado.
	 * 
	 * @author eder.borges
	 * @param arquivoZip
	 * @return byte[]
	 */
	public static byte[] extrairZipComArquivoUnico(byte[] arquivoZip) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				arquivoZip);
		ZipInputStream zis = new ZipInputStream(byteArrayInputStream);

		byte[] arquivoDescompactado = null;
		try {
			zis.getNextEntry();
			List<Integer> listaInt = new ArrayList<Integer>();
			for (int c = zis.read(); c != -1; c = zis.read()) {
				listaInt.add(c);
			}
			arquivoDescompactado = new byte[listaInt.size()];
			int posicao = 0;
			for (Integer valor : listaInt) {
				arquivoDescompactado[posicao] = valor.byteValue();
				posicao++;
			}
			zis.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return arquivoDescompactado;
	}

	/**
	 * Extrai o unico arquivo zipado.
	 * 
	 * @author eder.borges
	 * @param arquivoZip
	 * @return byte[]
	 */
	public static byte[] extrairZipComArquivoUnico(InputStream arquivoZip) {
		ZipInputStream zis = new ZipInputStream(arquivoZip);

		byte[] arquivoDescompactado = null;
		try {
			zis.getNextEntry();
			List<Integer> listaInt = new ArrayList<Integer>();
			for (int c = zis.read(); c != -1; c = zis.read()) {
				listaInt.add(c);
			}
			arquivoDescompactado = new byte[listaInt.size()];
			int posicao = 0;
			for (Integer valor : listaInt) {
				arquivoDescompactado[posicao] = valor.byteValue();
				posicao++;
			}
			zis.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return arquivoDescompactado;
	}

	/**
	 * Obtem o Nome do unico arquivo zipado
	 * 
	 * @author eder.borges
	 * @param arquivoZip
	 * @return String
	 */
	public static String obtemNomeArquivoUnicoZipado(byte[] arquivoZip) {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
				arquivoZip);
		ZipInputStream zis = new ZipInputStream(byteArrayInputStream);
		String nomeEntidadeZip = null;
		try {
			ZipEntry ze = zis.getNextEntry();
			nomeEntidadeZip = ze.getName();
			zis.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return nomeEntidadeZip;
	}

	/**
	 * Obtem o Nome do unico arquivo zipado
	 * 
	 * @author eder.borges
	 * @param arquivoZip
	 * @return String
	 */
	public static String obtemNomeArquivoUnicoZipado(InputStream arquivoZip) {
		ZipInputStream zis = new ZipInputStream(arquivoZip);
		String nomeEntidadeZip = null;
		try {
			ZipEntry ze = zis.getNextEntry();
			nomeEntidadeZip = ze.getName();
			zis.close();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return nomeEntidadeZip;
	}

	/**
	 * Obtem o Nome do unico arquivo zipado
	 * 
	 * @author eder.borges
	 * @param arquivoZip
	 * @return String
	 */
	public static String obtemNomeArquivoUnicoZipado(File arquivoZip) {
		try {
			InputStream inputStream = new FileInputStream(arquivoZip);
			ZipInputStream zis = new ZipInputStream(inputStream);
			String nomeEntidadeZip = null;
			ZipEntry ze = zis.getNextEntry();
			nomeEntidadeZip = ze.getName();
			zis.close();
			return nomeEntidadeZip;
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
	}

	/**
	 * Compacta um arquivo passando o array de byte e seu nome e retorna o zip
	 * em byte
	 * 
	 * @author eder.borges
	 * @param arquivo
	 *            , nomeArquivo
	 * @return zip
	 */
	public static byte[] compactarZipComArquivoUnico(byte[] arquivo,
			String nomeArquivo) {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream);
		byte[] arquivoCompactado = null;
		try {
			zos.setLevel(6);
			zos.putNextEntry(new ZipEntry(nomeArquivo));
			zos.write(arquivo);
			zos.closeEntry();
			zos.close();
			arquivoCompactado = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new Exception(e.getMessage(), e);
		}
		return arquivoCompactado;
	}

	/**
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(final String[] args) throws Exception {

		File f1 = new File("/tmp/AplicaoDotal_15032011.pdf");
		File f2 = new File("/tmp/Jos da Si15032011.pdf");

		File target = new File("/tmp/compactado.zip");

		System.out.println(compactar(target, f1, f2));
	}
}
