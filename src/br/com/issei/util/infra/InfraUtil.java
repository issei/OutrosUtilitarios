package br.com.issei.util.infra;


import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
/**
 * Contem funcoes utilizadas por varios pacotes
 */
public class InfraUtil
{

    /**
     * Indica se as impressoes realizadas por SgInfra.out e SgInfra.err sao normais ou reduzidas(sem o pacote)
     */
    private static boolean printReduced = false;
    /**
     * Indica se as impressoes realizadas por SgInfra.out e SgInfra.err devem vir acompanhadas de todo STACK
     */
    private static boolean fullStack = false;    
    /**
     * Lista de caracteres, ou conjunto de caracteres, que podem ser utilizados como indicadores de geracao
     * de sequencia nas telas ou paginas. 
     */
	private static Vector sequenceChars ;
	

	private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	
	
	
	/**
	 * Retorna um OutputStream a partir do StackTrace de uma Exception mas sem as classes de
	 * infra-estrutura do servidor de aplicacao<br>
	 * Rejeita as classe dos pacotes a seguir:<br>
	 * org.apache.catalina<br>
	 * org.jboss.web<br>
	 * org.apache.coyote<br>
	 * org.mortbay<br>
	 * com.ibm.servlet<br>
	 * com.ibm.ws<br>
	 * 
	 */
	public static ByteArrayOutputStream getCleanStack(Throwable e)
	{
		ByteArrayOutputStream baosTmp = new ByteArrayOutputStream();
		PrintWriter pwTmp = new PrintWriter(baosTmp);
		e.printStackTrace(pwTmp);
		pwTmp.flush();
		pwTmp.close();
		ByteArrayInputStream baisSaveStack = new ByteArrayInputStream(baosTmp.toByteArray());

		ByteArrayOutputStream baosSaveStack = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(baosSaveStack);		   
		 try
		 {
				InputStreamReader file = new InputStreamReader(baisSaveStack);	            
				BufferedReader bf = new BufferedReader(file);
				while (true)
				{
					String sLine = bf.readLine();			
					if (sLine == null)
						break;			
					if ((sLine.indexOf("org.jboss") >= 0) ||
					    (sLine.indexOf("org.apache.catalina") >= 0) ||					    
				   	    (sLine.indexOf("org.apache.coyote") >= 0) ||
					    (sLine.indexOf("org.apache.tomcat") >= 0) ||
					    (sLine.indexOf("sun.reflect") >= 0) ||
					    (sLine.indexOf("java.lang.reflect") >= 0) ||
					    (sLine.indexOf("javax.servlet") >= 0) ||
					    (sLine.indexOf("org.mortbay") >= 0) ||
					    (sLine.indexOf("com.ibm.servlet") >= 0) ||
					    (sLine.indexOf("com.ibm.ws") >= 0) ||
                        (sLine.indexOf("javax.swing") >= 0) ||
                        (sLine.indexOf("java.awt") >= 0) )
					{	
					}
					else
					{
					   pw.println(sLine);	
					}
	            	               
				}
		 }
		 catch (java.io.IOException e1)
		 {  
		 }	
		 pw.flush();
		 pw.close();
		 return baosSaveStack;
	}
    /** 
     * Verifica se a impressao de OUT esta bloqueada
     */
    public static boolean outAllowed()
    {
        String allow = System.getProperty("sysgen.out.allowed");
        if (allow != null)
        {
           if ((allow.equalsIgnoreCase("N"))  || 
              (allow.equalsIgnoreCase("No"))  ||
              (allow.equalsIgnoreCase("Nao")) ||
              (allow.equalsIgnoreCase("Não")) )
           {
               return false;
           }
        }
        return true; 
    }    
    /**
     * Executa a impressao de uma informacao em System.out precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void out(Object text)
    {
        if (outAllowed())
        {
            String sErrorLine = getErrorLine();
            if (text == null)
            {
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + "<Objeto nulo>");
            }
            else
            {            
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + text.toString());
            }
        }
    }    
    /**
     * Executa a impressao de uma informacao em System.out precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void out(String text)
    {
        if (outAllowed())
        {
            String sErrorLine = getErrorLine();
            if (text == null)
            {
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + "<Objeto nulo>");
            }
            else
            {            
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + text);
            }
        }
    }
    /**
     * Executa a impressao de uma informacao em System.out precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void out(StringBuffer text)
    {
        if (outAllowed())
        {
            String sErrorLine = getErrorLine();

            if (text == null)
            {
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + "<Objeto nulo>");
            }
            else
            {                        
                System.out.println("[" + now()  +"] " + "INDRA.OUT at "  + sErrorLine + " : " + text.toString());
            }
        }
    }	
    /**
     * Executa a impressao de uma informacao em System.err precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void err(String text)
    {
        String sErrorLine = getErrorLine();
        System.err.println("[" + now()  +"] " + "INDRA.ERR at "  + sErrorLine + " : " + text);
    }    
    /**
     * Executa a impressao de uma informacao em System.err precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void err(StringBuffer text)
    {
        String sErrorLine = getErrorLine();
        if (text == null)
        {
            System.err.println("[" + now()  +"] " + "INDRA.ERR at "  + sErrorLine + " : " + "<Objeto nulo>");
        }
        else
        {
            System.err.println("[" + now()  +"] " + "INDRA.ERR at "  + sErrorLine + " : " + text.toString());
        }
    }  
    /**
     * Executa a impressao de uma informacao em System.err precedido da classe/linha em que ocorreu a chamada.      * 
     */
    public static void err(Object text)
    {
        String sErrorLine = getErrorLine();
        if (text == null)
        {
            System.err.println("[" + now()  +"] " + "INDRA.ERR at "  + sErrorLine + " : " + "<Objeto nulo>");
        }
        else
        {
            System.err.println("[" + now()  +"] " + "INDRA.ERR at "  + sErrorLine + " : " + text.toString());
        }
    }     
    /**
     * Pega a linha em que ocorreu a chamda do pout ou perr
     */
    private static String getErrorLine()
    {
        Throwable e = null;
        try
        {
            throw new Exception("Indra - fake exception!!!!");
        }
        catch (Exception e2)
        {
            e = e2;
        }
        ByteArrayOutputStream baosTmp = new ByteArrayOutputStream();
        PrintWriter pwTmp = new PrintWriter(baosTmp);
        e.printStackTrace(pwTmp);
        pwTmp.flush();
        pwTmp.close();
        ByteArrayInputStream baisSaveStack = new ByteArrayInputStream(baosTmp.toByteArray());
        int countLine = 0;
        StringBuffer sErrorLine = new StringBuffer();
        try
         {
                InputStreamReader file = new InputStreamReader(baisSaveStack);              
                BufferedReader bf = new BufferedReader(file);
                while (true)
                {
                    String sLine = bf.readLine();       
                    countLine++;      
                    if (sLine == null)
                        break;  
                    if (countLine == 4)
                    {
                       if (sLine.indexOf("at ") > 0)
                       {
                           sLine = sLine.substring(sLine.indexOf("at ") + 3,sLine.length());
                       }
                       if (isPrintReduced())
                       {
                           if (sLine.indexOf("(") > 0)
                           {
                               sLine = sLine.substring(sLine.indexOf("("),sLine.length());
                           }                           
                       }
                       sErrorLine.append(sLine);   
                       // se nao for full stack soh mostra a linha da impressao
                       if (!isFullStack())
                       {
                           break;
                           
                       }
                    }
                    else if ((countLine > 4) && (countLine <= 999))
                    {
                        sErrorLine.append("\n" + sLine ); 
                    }
                                   
                }
         }
         catch (java.io.IOException e1)
         {  
         }
         if (isFullStack())
         {
             sErrorLine.append("\n");             
         }
         return sErrorLine.toString();
    }

    /**
     * Verifica o indicador de impressao reduzida em SgInfra.out e SgInfra.err 
     * @return boolean
     */
    public static boolean isPrintReduced()
    {
        return printReduced;
    }
    /**
     * Assinala o indicador de impressao reduzida utilizado em SgInfra.out e SgInfra.err 
     */
    public static void setPrintReduced(boolean printReduced)
    {
        InfraUtil.printReduced = printReduced;
    }


    /**
     * Retorna o indicador FullStack
     * @return boolean
     */
    public static boolean isFullStack()
    {
        return fullStack;
    }


    /**
     * Assinala o Indicador FullStack, indicando se imprime somente a linha de impressao(false) ou
     * todo o stack de chamada(true)
     */
    public static void setFullStack(boolean fullStack)
    {
        InfraUtil.fullStack = fullStack;
    }
    /**
    * Retorna a Data do sistema ignorando a hora minuto segundo.
    */
    private static String now()
    {
        java.util.Date dt = new Date();
        String ds = fmt.format(new Date());
        return ds;

    }    
}

