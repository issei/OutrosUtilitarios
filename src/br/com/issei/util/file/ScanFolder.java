package br.com.issei.util.file;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;


public class ScanFolder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String dir = args[0];
		File diretorio = new File(dir);
		LinkedList<File> result = scan(diretorio);
		for (File file : result) {
			System.out.println(file.getAbsolutePath()+"\tlastModified=\t" + new Date(file.lastModified()).toLocaleString()+"\tfile=\t"+file.getName());
		}

		

	}

	public static LinkedList<File> scan(File diretorio) {
		return orderByLastModified(scanFolder(diretorio));
	}

	public static LinkedList<File> scanFolder(File diretorio) {
		LinkedList<File> files = new LinkedList<>();
		File fileList[] = diretorio.listFiles();
//		System.out.println("Numero de arquivos/pastas no diretorio "+diretorio.getName()+": " + fileList.length );
		for ( int i = 0; i < fileList.length; i++ ){
			
			if(fileList[i].isDirectory())
			{
				files.addAll(scanFolder(fileList[i]));
			}else{
//				System.out.println(fileList[i].getParentFile().getName()+"");
				files.add(fileList[i]);
			}
		}
		return files;
	}

	public static LinkedList<File> orderByLastModified(List<File> files) {
		final LinkedList<File> result = new LinkedList<>();
		final Map<Date, File> map = new HashMap<>();
		for (File file : files) {
			map.put(new Date(file.lastModified()), file);
		}
		final SortedSet<Date> keys = new TreeSet<Date>(map.keySet());
		for (Date date : keys) {
			File file = map.get(date);
			result.add(file);
		}
		return result;
	}

}
