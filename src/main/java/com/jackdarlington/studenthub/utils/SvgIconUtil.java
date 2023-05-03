/*
 * Created by Jack Darlington | 2023
 */

package com.jackdarlington.studenthub.utils;

import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.Element;
import org.w3c.dom.svg.SVGElement;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_HEIGHT;
import static org.apache.batik.transcoder.SVGAbstractTranscoder.KEY_WIDTH;
import static org.apache.batik.transcoder.image.ImageTranscoder.KEY_BACKGROUND_COLOR;
import org.xml.sax.XMLReader;

/**
 * @author Jack Darlington
 * Student ID: 19082592
 * Date: 03/05/2023
 */

public class SvgIconUtil {
    public static BufferedImage loadSvg(String filePath, int width, int height) throws IOException, TranscoderException {
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        SVGDocument doc = factory.createSVGDocument(null, Files.newInputStream(Paths.get(filePath)));
        Element root = doc.getDocumentElement();
        root.setAttributeNS(null, "width", Integer.toString(width));
        root.setAttributeNS(null, "height", Integer.toString(height));
        SVGElement svgElement = (SVGElement)root;
        BufferedImage image = createBufferedImage(width, height);
        TranscodingHints hints = new TranscodingHints();
        hints.put(KEY_WIDTH, width);
        hints.put(KEY_HEIGHT, height);
        hints.put(KEY_BACKGROUND_COLOR, java.awt.Color.WHITE);
        PNGTranscoder transcoder = new PNGTranscoder();
        transcoder.setTranscodingHints(hints);
        TranscoderInput input = new TranscoderInput((XMLReader) svgElement);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        TranscoderOutput output = new TranscoderOutput(outputStream);
        transcoder.transcode(input, output);
        byte[] pngBytes = outputStream.toByteArray();
        InputStream inputStream = new ByteArrayInputStream(pngBytes);
        return ImageIO.read(inputStream);
    }

    private static BufferedImage createBufferedImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return image;
    }
}
