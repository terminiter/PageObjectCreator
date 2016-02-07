/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bussines;

import Data.values;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author Marco Antonio
 */
public class testWritter implements MyFile {

    String type;
    String name;
    String header;
    private String body;
    private String footer;
    private String author;
    private final SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    private ScriptManager file;
    File archivo;
    FileWriter escribir;
    String path;

    public testWritter(String titulo, String rootPath, String javapath, String propertiesPath, String author) {
        this.path = javapath;
        file = new ScriptManager(titulo, "java", rootPath, javapath, author);
        archivo = new File(javapath + File.separator + file.absoluteName());
        abrirArchivo();
        //write_header();

    }

    public final void abrirArchivo() {
        try {
            escribir = new FileWriter(archivo, true);
        } catch (IOException ex) {
            Logger.getLogger("error al abrir en archivo");
        }
    }

    public void escribirArchivo(String texto) {
        try {
            escribir.write(texto + values.br);

        } catch (IOException ex) {
            Logger.getLogger("error al escribir en archivo");
        }

    }

//    public final void write_header() {
//        try {
//            escribir.write(file.getHeader() + values.br);
//
//        } catch (IOException ex) {
//            Logger.getLogger("error al escribir cabecera en archivo: " + file.absoluteName());
//        }
//
//    }
    public void writeHeader() {
        this.header = "/*\n"
                + " * TEST    " + format.format(new Date()) + "\n"
                + " * Generated by JAT-GUI\n"
                + " * JAT-GUI author : Brayam Machicado Vidangos\n"
                + " */\n" + values.kwPackage + obtener_package(path) + " \n"
                + "import org.openqa.selenium.WebDriver;\n"
                + "import org.testng.annotations.Test;\n"
                + "import com.common.appWeb.appWebFactory;\n"
                + "import com.common.business.BrowserType;\n"
                + "import com.common.data.globals;"
                + iniciar_clase();

        this.body = "";
        this.footer = "";
        this.author = author;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getHeader() {
        return header;
    }

    @Override
    public void setHeader(String header) {
        this.header = header;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String getFooter() {
        return footer;
    }

    @Override
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @Override
    public String absoluteName() {
        return name + values.pto + type;
    }

    @Override
    public String build_line(String name_property, String target, String type, boolean key, boolean button, boolean select) {

//        String methods = getEnabledFunction(name_property) + values.br + getDisplayedFunction(name_property) + values.br;
//        if (button) {
//            methods = methods + getClickMethod(name_property) + values.br;
//        }
//        if (key) {
//            methods = methods + getPutMethod(name_property) + values.br;
//        }
//        return methods;
        return "";
    }

    private String obtener_package(String path) {
//        String packg = "";
//        int r = path.indexOf("src")+4;
//        packg = path.substring(r);
//        packg = packg.replace('\\', '.');
//
//        return " " + packg + ";"+values.br;
        path = path.replace('\\', '.');
        return path;
    }

    public String iniciar_clase() {
        return values.kwPublic + values.kwClass + name + values.lla + values.br;

    }

    public String cerrar_clase() {
        return values.br + " " + values.llc;
    }

    public String armarTest(String testName, String testCase, String author, String steps, String expectedResults) {
        String testBody;
        testBody = header+"\n@Test\n"
                + "	// This is TestNG annotation\n"
                + "/*\n"
                + "test name = " + testName + "\n"
                + "test case = " + testCase + "\n"
                + "Author = " + author + "\n"
                + "Steps:\n"
                + steps + "\n"
                + "Expected Results:\n"
                + expectedResults + "\n"
                + "*/\n"
                + "	public void " + testName + "()\n"
                + "\n"
                + "	{\n"
                + "		\n"
                + "		appWebFactory webDriver = new appWebFactory();\n"
                + "		WebDriver driver = appWebFactory.buildBrowser(BrowserType.Firefox);\n"
                + "		//here write your test"
                + "		driver.close();\n"
                + "		driver.quit();\n"
                + "	}"
                +cerrar_clase();
        return "";
    }

}