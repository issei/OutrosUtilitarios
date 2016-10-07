package br.com.issei.util.infra;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class TrataException {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Throwable e = null;
		try {
			throw new Exception("fake exception!!!!");
		} catch (Exception e2) {
			e = e2;
		}
		StringBuffer sErrorLine = getStackTrace(e);
		
		System.out.println(sErrorLine.toString());
		

	}

	public static StringBuffer getStackTrace(Throwable e) {
		ByteArrayOutputStream baosTmp = new ByteArrayOutputStream();
		PrintWriter pwTmp = new PrintWriter(baosTmp);
		e.printStackTrace(pwTmp);
		while(e.getCause()!=null)
		{
			Throwable cause = e.getCause();
			cause.printStackTrace(pwTmp);
			e = cause;
		}
		pwTmp.flush();
		pwTmp.close();
		ByteArrayInputStream baisSaveStack = new ByteArrayInputStream(baosTmp.toByteArray());
		StringBuffer sErrorLine = new StringBuffer();
		try {
			InputStreamReader file = new InputStreamReader(baisSaveStack);
			BufferedReader bf = new BufferedReader(file);
			while (true) {
				String sLine = bf.readLine();
				if (sLine == null)
					break;
				sErrorLine.append(sLine);
			}
			baosTmp.close();
			baisSaveStack.close();
			file.close();
			bf.close();
		} catch (java.io.IOException e1) {
		}
		
		return sErrorLine;
	}

}
