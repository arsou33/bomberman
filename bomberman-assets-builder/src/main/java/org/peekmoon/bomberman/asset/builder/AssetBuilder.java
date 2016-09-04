package org.peekmoon.bomberman.asset.builder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class AssetBuilder {
    
    Logger log = LoggerFactory.getLogger(AssetBuilder.class);

    public static void main(String[] args) {
        try {
            new AssetBuilder().build();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        
    }

    private void build() throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        log.info("Starting asset builder...");
        log.info("Parsing asset dae input file");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        XPathFactory xpf = XPathFactory.newInstance();
        XPath path = xpf.newXPath();
        //Document xml = builder.parse(getClass().getResourceAsStream("/cube.dae"));
        Document xml = builder.parse(new File("C:/Users/j.lelong/Downloads/buggy2.1.dae"));
        //Document xml = builder.parse(new File("C:/Users/j.lelong/Downloads/splash-pokedstudio.dae"));
        
        log.info("Extracting data from dae input file");
        //MeshReader meshReader = new MeshReader((Element) path.evaluate("//geometry[@name='Cube']/mesh", xml, XPathConstants.NODE));
        MeshReader meshReader = new MeshReader((Element) path.evaluate("//geometry[@name='Cylinder.007']/mesh", xml, XPathConstants.NODE));
        //MeshReader meshReader = new MeshReader((Element) path.evaluate("//geometry[@name='Mesh.006']/mesh", xml, XPathConstants.NODE));
        
        log.info("Writing mesh asset file");
        FileOutputStream fos = new FileOutputStream("C:/Users/j.lelong/Documents/Perso/dev/bomberman-project/bomberman/src/main/resources/mesh/cube.mesh");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(meshReader.getMesh());
        fos.close();
        log.info("Finished asset builder");
    }

}
