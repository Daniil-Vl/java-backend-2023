package edu.project4.transformation;

import edu.project4.transformation.nonlineartransformations.CrossTransformation;
import edu.project4.transformation.nonlineartransformations.DiskTransformation;
import edu.project4.transformation.nonlineartransformations.ExponentialTransformation;
import edu.project4.transformation.nonlineartransformations.HeartTransformation;
import edu.project4.transformation.nonlineartransformations.PolarTransformation;
import edu.project4.transformation.nonlineartransformations.SinusoidalTransformation;
import edu.project4.transformation.nonlineartransformations.SphericalTransformation;
import edu.project4.transformation.nonlineartransformations.SwirlTransformation;
import edu.project4.transformation.nonlineartransformations.TangentTransformation;

public class NonLinearTransformationsFactory {
    private NonLinearTransformationsFactory() {
    }

    public static Transformation createTransformation(NonLinearTransformationType type) {
        return switch (type) {
            case DISK -> new DiskTransformation();
            case CROSS -> new CrossTransformation();
            case EXPONENTIAL -> new ExponentialTransformation();
            case HEART -> new HeartTransformation();
            case POLAR -> new PolarTransformation();
            case SWIRL -> new SwirlTransformation();
            case SINUSOIDAL -> new SinusoidalTransformation();
            case SPHERICAL -> new SphericalTransformation();
            case TANGENT -> new TangentTransformation();
        };
    }
}
