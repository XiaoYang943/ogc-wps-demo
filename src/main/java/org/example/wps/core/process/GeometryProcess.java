package org.example.wps.core.process;

import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geotools.process.factory.StaticMethodsProcessFactory;
import org.geotools.text.Text;
import org.locationtech.jts.geom.Geometry;

public class GeometryProcess extends StaticMethodsProcessFactory<GeometryProcess> {
    private static final String PROCESS_TYPE = "Geometry";

    public GeometryProcess() {
        super(Text.text(PROCESS_TYPE), PROCESS_TYPE, GeometryProcess.class);
    }

    @DescribeProcess(title = PROCESS_TYPE + ":Area", description = "计算面积")
    @DescribeResult(description = "计算Geometry的面积")
    public static double calArea(@DescribeParameter(name = "Geometry", description = "需要计算面积的Geometry") Geometry geometry) {
        return geometry.getArea();
    }
}
