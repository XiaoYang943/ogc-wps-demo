package org.example.wps.core.operation;

import org.example.wps.utils.ProcessFactoryUtil;
import org.geotools.api.feature.type.Name;
import org.geotools.process.ProcessFactory;

import java.util.List;

/**
 * 检索WPS服务元数据、WPS服务器上的可用Process
 */
public class GetCapabilities {
    public String generateCapabilitiesXML() {
        StringBuilder xml = new StringBuilder();
        xml.append("""
                    <wps:Capabilities xmlns:wps="http://www.opengis.net/wps/1.0.0"
                                      xmlns:ows="http://www.opengis.net/ows/1.1"
                                      xmlns:xlink="http://www.w3.org/1999/xlink"
                                      xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                      xsi:schemaLocation="http://www.opengis.net/wps/1.0.0
                                      http://schemas.opengis.net/wps/1.0.0/wpsGetCapabilities_response.xsd"
                                      service="WPS" version="1.0.0">
                    <ows:ServiceIdentification>
                        <ows:Title>GeoTools WPS</ows:Title>
                        <ows:ServiceType>WPS</ows:ServiceType>
                        <ows:ServiceTypeVersion>1.0.0</ows:ServiceTypeVersion>
                    </ows:ServiceIdentification>
                    <wps:ProcessOfferings>
                """);

//        Set<ProcessFactory> processFactories = Processors.getProcessFactories();  // 该方法会把gt源码中创建的process也加进来
        List<ProcessFactory> processFactories = ProcessFactoryUtil.loadLocalFactories();
        for (ProcessFactory factory : processFactories) {
            for (Name name : factory.getNames()) {
                String identifier = name.getURI();
                String title = factory.getTitle(name) != null ? factory.getTitle(name).toString() : identifier;
                String description = factory.getDescription(name) != null ? factory.getDescription(name).toString() : "";

                xml.append("<wps:Process>");
                xml.append("<ows:Identifier>").append(identifier).append("</ows:Identifier>");
                xml.append("<ows:Title>").append(title).append("</ows:Title>");
                xml.append("<ows:Abstract>").append(description).append("</ows:Abstract>");
                xml.append("</wps:Process>");
            }
        }

        xml.append("</wps:ProcessOfferings>");
        xml.append("</wps:Capabilities>");
        return xml.toString();
    }


}
