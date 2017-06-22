package com.hola.themetest;

import android.graphics.Color;
import android.graphics.RectF;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by zy on 17-6-22.
 */

public class ThemeParser {

    private String id;
    private Theme result;

    public ThemeParser(String id) {
        this.id = id;
    }

    public Theme getTheme() {
        if (result == null) {
            result = parse();
        }
        return result;
    }

    private Theme parse() {
        String themeFolder = Environment.getExternalStorageDirectory() + "/360launcher/theme/custom/" + id;
        File manifestFile = new File(themeFolder, "manifest.xml");
        Theme ret = new Theme();
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(manifestFile);
            Element root = document.getDocumentElement();
            NodeList childsNodes = root.getChildNodes();

            for (int j = 0; j < childsNodes.getLength(); j++) {
                Node node = childsNodes.item(j); // 判断是否为元素类型

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childNode = (Element) node;

                    if ("packageName".equals(childNode.getNodeName())) {
                        ret.packageName = getTextContent(childNode);
                    } else if ("versionName".equals(childNode.getNodeName())) {
                        ret.versionName = getTextContent(childNode);
                    } else if ("versionCode".equals(childNode.getNodeName())) {
                        ret.versionCode = Integer.parseInt(getTextContent(childNode));
                    } else if ("specification".equals(childNode.getNodeName())) {
                        ret.specification = getTextContent(childNode);
                    } else if ("features".equals(childNode.getNodeName())) {
                        ret.features = getSplitStrs(getTextContent(childNode));
                    } else if ("requiredFeatures".equals(childNode.getNodeName())) {
                        ret.requiredFeatures = getSplitStrs(getTextContent(childNode));
                    } else if ("encrypt".equals(childNode.getNodeName())) {
                        ret.encrypt = Integer.parseInt(getTextContent(childNode));
                    } else if ("requireValidation".equals(childNode.getNodeName())) {
                        ret.requireValidation = Boolean.parseBoolean(getTextContent(childNode));
                    } else if ("defaultDensity".equals(childNode.getNodeName())) {
                        ret.defaultDensity = Integer.parseInt(getTextContent(childNode));
                    } else if ("launcher".equals(childNode.getNodeName())) {
                        parseLauncherPart(ret, childNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ret;
    }

    private void parseLauncherPart(Theme launcherPart, Element root) {
        NodeList fonts = root.getElementsByTagName("font");

        for (int i = 0; i < fonts.getLength(); i++) {
            Element font = (Element) fonts.item(i);

            NodeList childsNodes = font.getChildNodes();

            for (int j = 0; j < childsNodes.getLength(); j++) {
                Node node = childsNodes.item(j); // 判断是否为元素类型

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childNode = (Element) node;

                    if ("color".equals(childNode.getNodeName())) {
                        launcherPart.fontColor = (int) Long.parseLong(getTextContent(childNode), 16);
                    } else if ("size".equals(childNode.getNodeName())) {
                        launcherPart.fontSize = Integer.parseInt(getTextContent(childNode));
                    } else if ("file".equals(childNode.getNodeName())) {
                        launcherPart.fontFile = getTextContent(childNode);
                    }else if ("style".equals(childNode.getNodeName())) {
                        launcherPart.iconStyle = getTextContent(childNode);
                    } else if ("line".equals(childNode.getNodeName())) {
                        launcherPart.strokeWidth = Integer.parseInt(getTextContent(childNode));
                    } else if ("color".equals(childNode.getNodeName())) {
                        launcherPart.strokeColor = Color.parseColor(getTextContent(childNode));
                    } else if ("bounds".equals(childNode.getNodeName())) {
                        try {
                            String[] bs = getTextContent(childNode).split(",");
                            float[] bf = new float[4];
                            for (int z = 0; z < 4; z++) {
                                bf[z] = Float.parseFloat(bs[z]);
                            }
                            RectF bounds = new RectF();
                            bounds.set(bf[0], bf[1], bf[0] + bf[2], bf[1] + bf[3]);
                            launcherPart.iconBounds = bounds;
                        } catch (Exception e) {
                            Log.e("ThemeTest", "failed to parse icon bounds");
                        }
                    }
                }
            }
        }

        NodeList icons = root.getElementsByTagName("icon");

        for (int i = 0; i < icons.getLength(); i++) {
            Element icon = (Element) icons.item(i);

            NodeList childsNodes = icon.getChildNodes();

            for (int j = 0; j < childsNodes.getLength(); j++) {
                Node node = childsNodes.item(j); // 判断是否为元素类型

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childNode = (Element) node;

                    if ("size".equals(childNode.getNodeName())) {
                        launcherPart.iconSize = Integer.parseInt(getTextContent(childNode));
                    }
                }
            }
        }

        NodeList spacings = root.getElementsByTagName("spacing");

        for (int i = 0; i < spacings.getLength(); i++) {
            Element spacing = (Element) spacings.item(i);

            NodeList childsNodes = spacing.getChildNodes();

            for (int j = 0; j < childsNodes.getLength(); j++) {
                Node node = childsNodes.item(j); // 判断是否为元素类型

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childNode = (Element) node;

                    if ("hPadding".equals(childNode.getNodeName())) {
                        launcherPart.hPadding = Integer.parseInt(getTextContent(childNode));
                    } else if ("hGap".equals(childNode.getNodeName())) {
                        launcherPart.hGap = Integer.parseInt(getTextContent(childNode));
                    } else if ("textSpacing".equals(childNode.getNodeName())) {
                        launcherPart.textSpacing = Integer.parseInt(getTextContent(childNode));
                    } else if ("vStartPadding".equals(childNode.getNodeName())) {
                        launcherPart.vStartPadding = Integer.parseInt(getTextContent(childNode));
                    } else if ("vEndPadding".equals(childNode.getNodeName())) {
                        launcherPart.vEndPadding = Integer.parseInt(getTextContent(childNode));
                    } else if ("dockPadding".equals(childNode.getNodeName())) {
                        launcherPart.dockPadding = Integer.parseInt(getTextContent(childNode));
                    } else if ("dockGap".equals(childNode.getNodeName())) {
                        launcherPart.dockGap = Integer.parseInt(getTextContent(childNode));
                    } else if ("folderGap".equals(childNode.getNodeName())) {
                        launcherPart.folderGap = Integer.parseInt(getTextContent(childNode));
                    }
                }
            }
        }
    }

    private String getTextContent(Element element) {
        try {
            return element.getTextContent();
        } catch (Throwable e) {
            return element.getFirstChild().getNodeValue();
        }
    }

    private List<String> getSplitStrs(String str) {
        List<String> ret = new ArrayList<String>();
        String[] splitStr = str.split(",");
        if (splitStr != null && splitStr.length > 0) {
            for (String string : splitStr) {
                if (!TextUtils.isEmpty(string)) {
                    ret.add(string.toLowerCase());
                }
            }
        }
        return ret;
    }
}
